package com.pma.pix.api.controller;

import com.pma.pix.api.assembler.PixKeyDisassembler;
import com.pma.pix.api.assembler.PixKeyModelAssembler;
import com.pma.pix.api.exceptionhandler.ApiExceptionHandler;
import com.pma.pix.api.model.PixKeyAlteracaoModel;
import com.pma.pix.api.model.PixKeyIdModel;
import com.pma.pix.domain.exception.EntidadeNaoEncontradaException;
import com.pma.pix.domain.exception.NegocioException;
import com.pma.pix.domain.service.PixKeyService;
import com.pma.pix.utils.JsonMapper;
import com.pma.pix.utils.PerformRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyAlterarTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeyInputTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeysTemplate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = PixKeyController.class)
@ContextConfiguration(
    classes = {
      ApiExceptionHandler.class,
      ModelMapper.class,
      PixKeyModelAssembler.class,
      PixKeyDisassembler.class
    })
class PixKeyControllerTest {

  @Autowired MockMvc mockMvc;

  @MockBean PixKeyService pixKeyService;

  private final String PIX_KEY_BASE_URL = "/pixkey";

  @BeforeEach
  void setUp() {}

  @Test
  void shouldCreatePixKeyGivenValidRequest() throws Exception {
    when(pixKeyService.salvar(any())).thenReturn(getPixKeyTemplate());

    var mvcResult =
        PerformRequest.post(mockMvc, PIX_KEY_BASE_URL, getPixKeyInputTemplate())
            .andExpect(status().isOk())
            .andReturn();

    var pixKeyId =
        JsonMapper.asObject(mvcResult.getResponse().getContentAsString(), PixKeyIdModel.class);

    assertNotNull(pixKeyId.getId());
  }

  @Test
  void shouldReturnUnprocessableEntityGivenNegocioExceptionOnSalvar() throws Exception {
    when(pixKeyService.salvar(any())).thenThrow(new NegocioException(("Algum Erro de negocio")));

    var mvcResult =
        PerformRequest.post(mockMvc, PIX_KEY_BASE_URL, getPixKeyInputTemplate())
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void shouldReturnUnprocessableEntityGivenMissingMandatoryField() throws Exception {

    var mvcResult =
        PerformRequest.post(mockMvc, PIX_KEY_BASE_URL, getPixKeyInputTemplate().withConta(null))
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void shouldListPixKeysGivenValidRequest() throws Exception {
    when(pixKeyService.buscar(any())).thenReturn(getPixKeysTemplate());

    var mvcResult =
        PerformRequest.get(mockMvc, PIX_KEY_BASE_URL).andExpect(status().isOk()).andReturn();
  }

  @Test
  void shouldReturnUnprocessableEntityGivenNegocioExceptionOnListar() throws Exception {
    when(pixKeyService.buscar(any())).thenThrow(new NegocioException(("Algum Erro de negocio")));

    var mvcResult =
        PerformRequest.get(mockMvc, PIX_KEY_BASE_URL)
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void shouldReturnUnprocessableEntityGivenInvalidIdFilter() throws Exception {

    var queryParams = "?id=196fe21c-a89d-4e3b-bde4-a9aa1f9f87be&agencia=1234";
    var filterUrl = PIX_KEY_BASE_URL + queryParams;

    var mvcResult =
        PerformRequest.get(mockMvc, filterUrl)
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void shouldReturnUnprocessableEntityGivenInvalidDateFilter() throws Exception {

    var queryParams = "?dataAtivacao=12/06/2022&dataInativacao=11/05/2022";
    var filterUrl = PIX_KEY_BASE_URL + queryParams;

    var mvcResult =
        PerformRequest.get(mockMvc, filterUrl)
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void shouldNotFoundGivenEntidadeNaoEncontradaExceptionOnListar() throws Exception {
    when(pixKeyService.buscar(any()))
        .thenThrow(new EntidadeNaoEncontradaException(("Pix key nao encontrada")));

    var mvcResult =
        PerformRequest.get(mockMvc, PIX_KEY_BASE_URL).andExpect(status().isNotFound()).andReturn();
  }

  @Test
  void ShouldChangePicKeyGivenValidRequest() throws Exception {
    when(pixKeyService.alterar(any())).thenReturn(getPixKeyTemplate());

    var mvcResult =
        PerformRequest.put(mockMvc, PIX_KEY_BASE_URL, getPixKeyAlterarTemplate())
            .andExpect(status().isOk())
            .andReturn();

    var pixKey =
        JsonMapper.asObject(
            mvcResult.getResponse().getContentAsString(), PixKeyAlteracaoModel.class);

    assertNotNull(pixKey);
  }

  @Test
  void ShouldReturnUnprocessableEtityGivenMissingMandatoryField() throws Exception {
    var mvcResult =
        PerformRequest.put(mockMvc, PIX_KEY_BASE_URL, getPixKeyAlterarTemplate().withId(null))
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void ShouldReturnUnprocessableEtityGivenNegocioException() throws Exception {

    when(pixKeyService.alterar(any())).thenThrow(new NegocioException(("Algum Erro de negocio")));

    var mvcResult =
        PerformRequest.put(mockMvc, PIX_KEY_BASE_URL, getPixKeyAlterarTemplate().withId(null))
            .andExpect(status().isUnprocessableEntity())
            .andReturn();
  }

  @Test
  void ShouldReturnNotFoundGivenEntidadeNaoEncontradaException() throws Exception {

    when(pixKeyService.alterar(any()))
        .thenThrow(new EntidadeNaoEncontradaException(("Pix Key nao encontrada")));

    var mvcResult =
        PerformRequest.put(mockMvc, PIX_KEY_BASE_URL, getPixKeyAlterarTemplate())
            .andExpect(status().isNotFound())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestGivenEmptyBodyRequest() throws Exception {
    when(pixKeyService.salvar(any())).thenReturn(getPixKeyTemplate());

    var mvcResult =
        PerformRequest.post(mockMvc, PIX_KEY_BASE_URL)
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestGivenInvalidBody() throws Exception {
    when(pixKeyService.salvar(any())).thenReturn(getPixKeyTemplate());

    var body =
        "{\n"
            + "    \"tipoChave\": \"EMAIL\",\n"
            + "    \"chave\": \"p22a12@ema.com\",\n"
            + "    \"tipoConta\": \"CORRENTE\",\n"
            + "    \"agencia\": \"872s\",\n"
            + "    \"conta\": 838992,\n"
            + "    \"nomeCorrentista\": \"Rafael\",\n"
            + "    \"sobrenomeCorrentista\": \"Correa\",\n"
            + "    \"tipoPessoa\": \"F\"\n"
            + "}";

    var mvcResult =
        PerformRequest.post(mockMvc, PIX_KEY_BASE_URL, body)
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestGivenInvalidMediaType() throws Exception {
    when(pixKeyService.salvar(any())).thenReturn(getPixKeyTemplate());

    var mvcResult =
        PerformRequest.post(
                mockMvc, PIX_KEY_BASE_URL, getPixKeyTemplate(), MediaType.APPLICATION_PDF)
            .andExpect(status().isUnsupportedMediaType())
            .andReturn();
  }

  @Test
  void shouldReturnInternalErrorRequestGivenSomeError() throws Exception {
    when(pixKeyService.salvar(any())).thenThrow(new NullPointerException());

    var mvcResult =
        PerformRequest.post(mockMvc, PIX_KEY_BASE_URL, getPixKeyInputTemplate())
            .andExpect(status().isInternalServerError())
            .andReturn();
  }
}
