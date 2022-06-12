package com.pma.pix.api.controller;

import com.pma.pix.api.assembler.PixKeyDisassembler;
import com.pma.pix.api.assembler.PixKeyModelAssembler;
import com.pma.pix.api.exceptionhandler.ApiExceptionHandler;
import com.pma.pix.domain.service.PixKeyService;
import com.pma.pix.utils.PerformRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyInputTemplate;
import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration()
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
  }

  @Test
  void listar() throws Exception {}

  @Test
  void alterar() {}
}
