package com.edirectory.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.edirectory.rest.TemperatureEvaluatorController;
import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.rest.beans.TemperatureEvaluatorResponse;
import com.edirectory.service.TemperatureEvaluator;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.service.functions.MaxDiffConversor;
import com.edirectory.service.functions.TemperatureEvaluatorRequestConversor;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/** TemperatureEvaluatorController. */
@ExtendWith(MockitoExtension.class)
public class TemperatureEvaluatorControllerTest {

  @InjectMocks
  private TemperatureEvaluatorController temperatureEvaluatorController;
  @Mock
  private TemperatureEvaluatorRequestConversor requestConversor;
  @Mock
  private MaxDiffConversor responseConversor;
  @Mock
  private TemperatureEvaluator service;
  
  private TemperatureEvaluatorRequest request;
  
  private TemperatureEvaluatorBean requestBean;
  
  private TemperatureEvaluatorResponse responseBean;
  
  
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
  }
  
  
  @DisplayName("Temperature Evaluator Controller process")
  @Test
  public void temperatureEvaluatorController() {
    Mockito.when(requestConversor.convert(request)).thenReturn(requestBean);
    Mockito.when(service.temperatureDiff(requestBean)).thenReturn(0);
    Mockito.when(responseConversor.convert(0)).thenReturn(responseBean);
    TemperatureEvaluatorResponse responseController = temperatureEvaluatorController
          .computeReadings(request);
    assertThat(responseController.getMaxDiff()).isEqualTo(0);
  }



}
