package com.pma.pix.api.controller;

import com.pma.pix.api.assembler.PixKeyDisassembler;
import com.pma.pix.api.assembler.PixKeyModelAssembler;
import com.pma.pix.api.model.PixKeyAlteracaoModel;
import com.pma.pix.api.model.PixKeyIdModel;
import com.pma.pix.api.model.PixKeyModel;
import com.pma.pix.api.model.input.PixKeyAlterarInput;
import com.pma.pix.api.model.input.PixKeyFilterInput;
import com.pma.pix.api.model.input.PixKeyInput;
import com.pma.pix.domain.service.PixKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pixkey")
@RequiredArgsConstructor
public class PixKeyController {

  private final PixKeyDisassembler pixKeyDisassembler;
  private final PixKeyModelAssembler pixKeyModelAssembler;
  private final PixKeyService pixKeyService;

  @PostMapping
  public PixKeyIdModel incluir(@RequestBody @Valid PixKeyInput pixKeyInput) {

    var pixKey = pixKeyDisassembler.toDomainObject(pixKeyInput);
    return pixKeyModelAssembler.toIdModel(pixKeyService.salvar(pixKey));
  }

  @GetMapping
  public List<PixKeyModel> listar(@Valid PixKeyFilterInput filter) {

    var pixKeyFilter = pixKeyDisassembler.toDomainObject(filter);

    return pixKeyModelAssembler.toModel(pixKeyService.findAll(pixKeyFilter));
  }

  @PutMapping
  public PixKeyAlteracaoModel alterar(@RequestBody @Valid PixKeyAlterarInput pixKeyAlterarInput) {

    var pixKey = pixKeyDisassembler.toDomainObject(pixKeyAlterarInput);
    return pixKeyModelAssembler.toAlteracaoModel(pixKeyService.alterar(pixKey));
  }
}
