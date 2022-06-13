package com.pma.pix.domain.service;

import com.pma.pix.domain.exception.EntidadeDuplicadaException;
import com.pma.pix.domain.exception.EntidadeNaoEncontradaException;
import com.pma.pix.domain.mapper.PixKeyMapper;
import com.pma.pix.domain.repository.PixKeyRepository;
import com.pma.pix.domain.validator.PixKeyValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static com.pma.pix.templates.PixKeyTemplate.getEmptyPixKeysTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeyFilterTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeysTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PixKeyServiceTest {

  @Mock private PixKeyRepository pixKeyRepository;
  @Mock private PixKeyValidator pixKeyValidator;
  @Mock private PixKeyMapper pixKeyMapper;
  @InjectMocks private PixKeyService pixKeyService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSavePixKeyGivenValidParameter() {
    doNothing().when(pixKeyValidator).validate(any());
    when(pixKeyRepository.save(any())).thenReturn(getPixKeyTemplate());

    pixKeyService.salvar(getPixKeyTemplate());

    verify(pixKeyRepository, times(1)).save(any());
    verify(pixKeyValidator, times(1)).validate(any());
  }

  @Test
  void shouldThrowGivenEntidadeDuplicadaExceptionGivenDuplicateEntity() {
    doNothing().when(pixKeyValidator).validate(any());
    when(pixKeyRepository.save(any()))
        .thenThrow(
            new DataIntegrityViolationException(
                "uk_chave", new SQLIntegrityConstraintViolationException()));

    Assertions.assertThrows(
        EntidadeDuplicadaException.class, () -> pixKeyService.salvar(getPixKeyTemplate()));

    verify(pixKeyRepository, times(1)).save(any());
    verify(pixKeyValidator, times(1)).validate(any());
  }

  @Test
  void shouldThrowRunTimeExceptionGivenError() {
    doNothing().when(pixKeyValidator).validate(any());
    when(pixKeyRepository.save(any()))
        .thenThrow(new DataIntegrityViolationException("Some SQL Error"));

    Assertions.assertThrows(
        RuntimeException.class, () -> pixKeyService.salvar(getPixKeyTemplate()));

    verify(pixKeyRepository, times(1)).save(any());
    verify(pixKeyValidator, times(1)).validate(any());
  }

  @Test
  void shouldFindPixKeysGivenFilter() {
    when(pixKeyRepository.findAll(any(Specification.class))).thenReturn(getPixKeysTemplate());

    pixKeyService.buscar(getPixKeyFilterTemplate());

    verify(pixKeyRepository, times(1)).findAll(any(Specification.class));
  }

  @Test
  void shouldThrowEntidadeNaoEncontradaGivenFilterThatNotReturnAnyRecord() {
    when(pixKeyRepository.findAll(any(Specification.class))).thenReturn(getEmptyPixKeysTemplate());

    Assertions.assertThrows(
        EntidadeNaoEncontradaException.class,
        () -> pixKeyService.buscar(getPixKeyFilterTemplate()));

    verify(pixKeyRepository, times(1)).findAll(any(Specification.class));
  }

  @Test
  void ShouldChangePixKeyGivenValidParameters() {
    when(pixKeyRepository.findById(any())).thenReturn(Optional.ofNullable(getPixKeyTemplate()));
    doNothing().when(pixKeyMapper).merge(any(), any());

    pixKeyService.alterar(getPixKeyTemplate());
  }

  @Test
  void ShouldThrowEntidadeNaoEncontradaExceptionGivenPixKeyNaoEncontrada() {
    when(pixKeyRepository.findById(any())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        EntidadeNaoEncontradaException.class, () -> pixKeyService.alterar(getPixKeyTemplate()));
  }

  @Test
  void buscarOuFalhar() {}
}
