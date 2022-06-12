package com.pma.pix.utils;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class PerformRequest {

  public static ResultActions post(MockMvc mockMvc, String apiUrl, Object object) throws Exception {

    return mockMvc
        .perform(
            MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonMapper.asJsonString(object)))
        .andDo(print());
  }
}
