package com.edirectory.validator.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.validator.impl.TemperatureValidatorImpl;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** TemperatureValidatorTest. */
public class TemperatureValidatorTest {

  private TemperatureValidatorImpl validator;
  
  private TemperatureEvaluatorBean testBeanNull;
  
  private TemperatureEvaluatorBean testBeanArrayNull;
  
  private TemperatureEvaluatorBean testBeanOk;
  
  private TemperatureEvaluatorRequest testRequestNull;
  
  private TemperatureEvaluatorRequest testRequestArrayNull;
  
  private TemperatureEvaluatorRequest testRequestOk;
  
  /** setup. */
  @BeforeEach
  public void setUp() {
    validator = new TemperatureValidatorImpl();
    
    testBeanNull = null;
    testRequestNull = null;
    
    testBeanArrayNull = new TemperatureEvaluatorBean();
    testBeanArrayNull.setInputTemperatures(null);
    
    testRequestArrayNull = new TemperatureEvaluatorRequest();
    testRequestArrayNull.setReadings(null);
    
  }
  
  @DisplayName("Temperature Bean Null")
  @Test
  public void temperatureBeanNull() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      validator.validate(testBeanNull);
    });
    String expectedMessage = "inputBean is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @DisplayName("Temperature Request Null")
  @Test
  public void temperatureRequestNull() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      validator.validate(testRequestNull);
    });
    String expectedMessage = "request object is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @DisplayName("Temperature Bean List Null")
  @Test
  public void temperatureBeanListNull() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      validator.validate(testBeanArrayNull);
    });
    String expectedMessage = "input temperatures list is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @DisplayName("Temperature Request List Null")
  @Test
  public void temperatureRequestListNull() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      validator.validate(testRequestArrayNull);
    });
    String expectedMessage = "readings list is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
}
