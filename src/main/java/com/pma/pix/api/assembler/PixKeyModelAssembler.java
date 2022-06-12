package com.pma.pix.api.assembler;

import com.pma.pix.api.model.PixKeyAlteracaoModel;
import com.pma.pix.api.model.PixKeyIdModel;
import com.pma.pix.api.model.PixKeyModel;
import com.pma.pix.domain.model.PixKey;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PixKeyModelAssembler {

  private final ModelMapper modelMapper;

  public PixKeyIdModel toIdModel(PixKey pixKey) {
    return modelMapper.map(pixKey, PixKeyIdModel.class);
  }

  public PixKeyModel toModel(PixKey pixKey) {
    return modelMapper.map(pixKey, PixKeyModel.class);
  }

  public PixKeyAlteracaoModel toAlteracaoModel(PixKey pixKey) {
    return modelMapper.map(pixKey, PixKeyAlteracaoModel.class);
  }

  public List<PixKeyModel> toModel(List<PixKey> pixKeys) {
    return pixKeys.parallelStream().map(this::toModel).collect(Collectors.toList());
  }
}
