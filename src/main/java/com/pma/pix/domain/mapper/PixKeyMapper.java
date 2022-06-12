package com.pma.pix.domain.mapper;

import com.pma.pix.domain.model.PixKey;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

public class PixKeyMapper {

  private final ModelMapper modelMapper;

  public PixKeyMapper(@Qualifier("modelMapperForUpdates") ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public void merge(PixKey pixKeyAtualizado, PixKey pixKeySalvo) {
    modelMapper.map(pixKeyAtualizado, pixKeySalvo);
  }
}
