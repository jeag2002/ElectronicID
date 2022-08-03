package com.edirectory.service.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.service.helper.TemperatureEvaluatorHelper;
import com.edirectory.service.impl.TemperatureEvaluatorImpl;
import com.edirectory.validator.impl.TemperatureValidatorImpl;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/** TemperatureEvaluatorImplTest. */
@ExtendWith(MockitoExtension.class)
public class TemperatureEvaluatorImplTest {

  @InjectMocks
  private TemperatureEvaluatorImpl temperatureEvaluatorImpl;
  
  @Spy
  private TemperatureValidatorImpl validator;
  
  @Mock
  private TemperatureEvaluatorHelper helper;
  
  /**
   * setUp Variables.
   */
  @BeforeEach
  public void setUp() {
    validator = new TemperatureValidatorImpl();
  }

  
  @DisplayName("Temperature Evaluator Service with normal data")
  @Test
  public void temperatureEvaluatorWithNullData() {
    ArrayList<Integer> inputData = new ArrayList<Integer>(Arrays.asList(1, 1, 1));
    TemperatureEvaluatorBean ibean = new TemperatureEvaluatorBean();
    ibean.setInputTemperatures(inputData);
    Mockito.when(helper.processTemperature(Mockito.anyList())).thenReturn(0);
    Integer results = temperatureEvaluatorImpl.temperatureDiff(ibean);
    assertThat(results).isEqualTo(0);
    Mockito.verify(helper).processTemperature(Mockito.anyList());
  }
    
  @DisplayName("Temperature Evaluator Service with null List")
  @Test
  public void temperatureEvaluatorWithNullList() {
    ArrayList<Integer> inputData = null;
    TemperatureEvaluatorBean ibean = new TemperatureEvaluatorBean();
    ibean.setInputTemperatures(inputData);    
    Exception exception = assertThrows(NullPointerException.class, () -> {
      temperatureEvaluatorImpl.temperatureDiff(ibean);
    });

    String expectedMessage = "input temperatures list is null";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
      
  }
    
  @DisplayName("Temperature Evaluator Service with null Object")
  @Test
  public void temperatureEvaluatorWithNullObject() {
    TemperatureEvaluatorBean ibean = null;
    Exception exception = assertThrows(NullPointerException.class, () -> {
      temperatureEvaluatorImpl.temperatureDiff(ibean);
    });
    String expectedMessage = "inputBean is null";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));

  }

    
    


}
