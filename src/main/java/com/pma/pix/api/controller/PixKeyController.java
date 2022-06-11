package com.pma.pix.api.controller;

import com.pma.pix.api.model.input.PixKeyInput;
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

  @PostMapping
  public void incluir(@RequestBody @Valid PixKeyInput pixKeyInput) {

    System.out.println("Incluir");
  }

  public List<Object> listar() {
    return Collections.emptyList();
  }
}
