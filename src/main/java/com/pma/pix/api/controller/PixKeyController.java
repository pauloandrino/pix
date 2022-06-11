package com.pma.pix.api.controller;

import com.pma.pix.api.assembler.PixKeyInputDisassembler;
import com.pma.pix.api.assembler.PixKeyModelAssembler;
import com.pma.pix.api.model.PixKeyModel;
import com.pma.pix.api.model.input.PixKeyInput;
import com.pma.pix.domain.service.PixKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pixKey")
@RequiredArgsConstructor
public class PixKeyController {

  private final PixKeyInputDisassembler pixKeyInputDisassembler;
  private final PixKeyModelAssembler pixKeyModelAssembler;
  private final PixKeyService pixKeyService;

  @PostMapping
  public PixKeyModel incluir(@RequestBody @Valid PixKeyInput pixKeyInput) {

    var pixKey = pixKeyInputDisassembler.toDomainObject(pixKeyInput);
    var pixKeyModel = pixKeyModelAssembler.toModel(pixKeyService.salvar(pixKey));

    System.out.println("Incluir");

    return pixKeyModel;
  }

  public List<Object> listar() {
    return Collections.emptyList();
  }
}
