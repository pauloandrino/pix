package com.pma.pix.api.assembler;

import com.pma.pix.api.model.PixKeyModel;
import com.pma.pix.domain.domain.PixKey;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PixKeyModelAssembler {

  private final ModelMapper modelMapper;

  public PixKeyModel toModel(PixKey pixKey) {
    return modelMapper.map(pixKey, PixKeyModel.class);
  }
}
