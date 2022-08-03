package com.edirectory.test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edirectory.rest.TemperatureEvaluatorController;
import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.rest.beans.TemperatureEvaluatorResponse;
import com.edirectory.service.TemperatureEvaluator;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.service.functions.MaxDiffConversor;
import com.edirectory.service.functions.TemperatureEvaluatorRequestConversor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/** WebMockTemperatureEvaluatorControllerTest. */
@WebMvcTest(TemperatureEvaluatorController.class)
public class WebMockTemperatureEvaluatorControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private TemperatureEvaluator service;
  
  @MockBean
  private TemperatureEvaluatorRequestConversor requestConversor;
  
  @MockBean
  private MaxDiffConversor responseConversor;
  
  private TemperatureEvaluatorRequest request;
  
  private TemperatureEvaluatorBean requestBean;
  
  private TemperatureEvaluatorResponse responseBean;
  
  private ObjectMapper mapper;

  /** Load information. */
  @BeforeEach
  public void setUp() {
    request = new TemperatureEvaluatorRequest();
    request.setReadings(new Integer[] {1, 1, 1});
    List<Integer> inputTemperatures = new ArrayList<Integer>();
    inputTemperatures.add(1);
    inputTemperatures.add(1);
    inputTemperatures.add(1);
    requestBean = new TemperatureEvaluatorBean();
    requestBean.setInputTemperatures(inputTemperatures);
    responseBean = new TemperatureEvaluatorResponse();
    responseBean.setMaxDiff(0);
    mapper = new ObjectMapper();
  }
  
  @DisplayName("Temperature Evaluator Service with normal data")
  @Test
  public void temperatureEvaluatorSuccessfull() throws Exception {
    Mockito.when(requestConversor.convert(request)).thenReturn(requestBean);
    Mockito.when(service.temperatureDiff(requestBean)).thenReturn(0);
    Mockito.when(responseConversor.convert(0)).thenReturn(responseBean);
    
    String jsonRequest = mapper.writeValueAsString(request);

    this.mockMvc
        .perform(post("/v2/stats/compute")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.maxDiff").value("0"));
    Mockito.verify(requestConversor).convert(request);
    Mockito.verify(service).temperatureDiff(requestBean);
    Mockito.verify(responseConversor).convert(Mockito.anyInt());
  }
  
  
}
