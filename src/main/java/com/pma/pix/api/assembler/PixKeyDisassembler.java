package com.pma.pix.api.assembler;

import com.pma.pix.api.model.input.PixKeyFilterInput;
import com.pma.pix.api.model.input.PixKeyInput;
import com.pma.pix.domain.model.PixKey;
import com.pma.pix.domain.model.PixKeyFilter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PixKeyDisassembler {

  private final ModelMapper modelMapper;

  public PixKey toDomainObject(PixKeyInput pixKeyInput) {
    return modelMapper.map(pixKeyInput, PixKey.class);
  }

  public PixKeyFilter toDomainObject(PixKeyFilterInput filter) {
    return modelMapper.map(filter, PixKeyFilter.class);
  }
}
