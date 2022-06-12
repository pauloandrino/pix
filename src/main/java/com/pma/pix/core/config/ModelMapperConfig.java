package com.pma.pix.core.config;

import com.pma.pix.domain.model.PixKey;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfig {

  @Bean
  @Primary
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public ModelMapper modelMapperForUpdates() {
    var modelMapper = new ModelMapper();

    modelMapper
        .typeMap(PixKey.class, PixKey.class)
        .addMappings(
            map -> {
              map.skip(PixKey::setId);
              map.skip(PixKey::setTipoChave);
              map.skip(PixKey::setChave);
              map.skip(PixKey::setDataAtivacao);
            });

    return modelMapper;
  }
}
