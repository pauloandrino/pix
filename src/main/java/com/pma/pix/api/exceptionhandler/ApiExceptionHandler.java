package com.pma.pix.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.pma.pix.domain.exception.EntidadeNaoEncontradaException;
import com.pma.pix.domain.exception.NegocioException;
import com.pma.pix.domain.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String MSG_ERRO_GENERICA_USUARIO_FINAL =
      "Ocorreu um erro interno inesperado no sistema. "
          + "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

  @Autowired private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return ResponseEntity.status(status).headers(headers).build();
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    var httpStatus =
        HttpStatus
            .UNPROCESSABLE_ENTITY; // Sobrescrevendo de 400 para 422, conforme descrito no case

    return handleValidationInternal(ex, ex.getBindingResult(), headers, httpStatus, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
    String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

    log.error(ex.getMessage(), ex);

    Problem problem =
        createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    var httpStatus =
        HttpStatus
            .UNPROCESSABLE_ENTITY; // Sobrescrevendo de 400 para 422, conforme descrito no case

    return handleValidationInternal(ex, ex.getBindingResult(), headers, httpStatus, request);
  }

  private ResponseEntity<Object> handleValidationInternal(
      Exception ex,
      BindingResult bindingResult,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ProblemType problemType = ProblemType.DADOS_INVALIDOS;

    String detail =
        String.format(
            "Um ou mais campos estão inválidos. Faça o preenchimento correto e "
                + "tente novamente");

    List<Problem.Object> problemObjects =
        bindingResult.getAllErrors().stream()
            .map(
                objectError -> {
                  String message =
                      messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                  String name = objectError.getObjectName();

                  if (objectError instanceof FieldError) {
                    name = ((FieldError) objectError).getField();
                  }

                  return Problem.Object.builder().name(name).userMessage(message).build();
                })
            .collect(Collectors.toList());

    Problem problem =
        createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .objects(problemObjects)
            .build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    Throwable rootCause = ExceptionUtils.getRootCause(ex);

    if (rootCause instanceof InvalidFormatException) {
      return handleInvalidFormatException(
          (InvalidFormatException) rootCause, headers, status, request);
    }

    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

    Problem problem =
        createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  private ResponseEntity<Object> handleInvalidFormatException(
      InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    String path = joinPath(ex.getPath());

    String detail =
        String.format(
            "A propriedade '%s' recebeu o valor '%s', "
                + "que é de um tipo inválido. Coorrija e informe um valor compatível com o tipo %s.",
            path, ex.getValue(), ex.getTargetType().getSimpleName());

    Problem problem =
        createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private String joinPath(List<JsonMappingException.Reference> references) {
    return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
  }

  @ExceptionHandler({ValidationException.class})
  public ResponseEntity<?> handleValidationException(ValidationException ex, WebRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    String detail = ex.getMessage();
    ProblemType problemType = ProblemType.DADOS_INVALIDOS;

    Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    String detail = ex.getMessage();
    ProblemType problemType = ProblemType.ERRO_NEGOCIO;

    Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleNegocioException(
      EntidadeNaoEncontradaException ex, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    String detail = ex.getMessage();
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

    Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

    if (body == null) {
      body =
          Problem.builder()
              .timestamp(OffsetDateTime.now())
              .title(status.getReasonPhrase())
              .status(status.value())
              .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
              .build();
    } else if (body instanceof String) {
      body =
          Problem.builder()
              .timestamp(OffsetDateTime.now())
              .title((String) body)
              .status(status.value())
              .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
              .build();
    }

    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private Problem.ProblemBuilder createProblemBuilder(
      HttpStatus status, ProblemType problemType, String detail) {

    return Problem.builder()
        .status(status.value())
        .type(problemType.getUri())
        .title(problemType.getTitle())
        .detail(detail)
        .timestamp(OffsetDateTime.now());
  }
}
