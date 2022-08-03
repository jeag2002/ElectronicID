package com.edirectory.service.functions.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.service.functions.TemperatureEvaluatorRequestConversor;
import com.edirectory.validator.impl.TemperatureValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/** TemperatureEvaluatorRequestConversorTest. */
@ExtendWith(MockitoExtension.class)
public class TemperatureEvaluatorRequestConversorTest {

  @InjectMocks
  private TemperatureEvaluatorRequestConversor temperatureEvaluatorRequestConversor;
  
  @Spy
  private TemperatureValidatorImpl temperatureValidator;
  
  private TemperatureEvaluatorRequest reqValid;
  private TemperatureEvaluatorRequest reqNullArray;
  private TemperatureEvaluatorRequest reqNull;
  private TemperatureEvaluatorRequest reqEmptyArray;
  
  /** Process setup. */
  @BeforeEach
  public void setUp() {
    
    temperatureValidator = new TemperatureValidatorImpl();
    
    reqValid = new TemperatureEvaluatorRequest();
    reqValid.setReadings(new Integer[]{1, 2, 3, 4});
    
    reqNullArray = new TemperatureEvaluatorRequest();
    reqNullArray.setReadings(null);
    
    reqNull = null;
    
    reqEmptyArray = new TemperatureEvaluatorRequest();
    reqEmptyArray.setReadings(new Integer[] {});
  }
  
  @DisplayName("Conversion with Normal Data")
  @Test
  public void conversorWithNormalData() {
    TemperatureEvaluatorBean responseBean = temperatureEvaluatorRequestConversor.convert(reqValid);
    assertThat(responseBean).isNotNull();
    assertThat(responseBean.getInputTemperatures()).isNotNull();
    assertThat(responseBean.getInputTemperatures().size()).isEqualTo(4);
  }
  
  @DisplayName("Conversion with Empty Data")
  @Test
  public void conversorWithEmptyData() {
    TemperatureEvaluatorBean responseBean = temperatureEvaluatorRequestConversor
        .convert(reqEmptyArray);
    assertThat(responseBean).isNotNull();
    assertThat(responseBean.getInputTemperatures()).isNotNull();
    assertThat(responseBean.getInputTemperatures().size()).isEqualTo(0);
  }
  
  @DisplayName("Conversion with Null Array")
  @Test
  public void conversorWithNullArray() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      temperatureEvaluatorRequestConversor.convert(reqNullArray);
    });
    
    String expectedMessage = "readings list is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @DisplayName("Conversion with Null Object")
  @Test
  public void conversorWithNullObject() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      temperatureEvaluatorRequestConversor.convert(reqNull);
    });
    
    String expectedMessage = "request object is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  

}
