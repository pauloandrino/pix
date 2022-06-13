package com.pma.pix.domain.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static com.pma.pix.templates.PixKeyTemplate.getPixKeyTemplate;
import static org.mockito.Mockito.doNothing;

class PixKeyMapperTest {

  @Mock private ModelMapper modelMapper;

  @InjectMocks private PixKeyMapper pixKeyMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void merge() {
    doNothing().when(modelMapper).map(getPixKeyTemplate(), getPixKeyTemplate());
    pixKeyMapper.merge(getPixKeyTemplate(), getPixKeyTemplate());
  }
}
