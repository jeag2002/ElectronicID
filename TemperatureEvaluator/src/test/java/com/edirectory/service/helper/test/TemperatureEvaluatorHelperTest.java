package com.edirectory.service.helper.test;


import static org.assertj.core.api.Assertions.assertThat;

import com.edirectory.service.helper.TemperatureEvaluatorHelper;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * TEST temperatureEvaluatorHelper. 
 */
public class TemperatureEvaluatorHelperTest {
  
  private TemperatureEvaluatorHelper helper;

  @BeforeEach
  public void setup() {
    helper = new TemperatureEvaluatorHelper();
  }
  
  @DisplayName("Temperature Evaluator With Null Data")
  @Test
  public void temperatureEvaluatorWithNullData() {
    ArrayList<Integer> inputData = null;
    Integer value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(0);
  }
  
  @DisplayName("Temperature Evaluator With No Data")
  @Test
  public void temperatureEvaluatorWithEmptyData() {
    ArrayList<Integer> inputData = new ArrayList<Integer>();
    Integer value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(0);
  }
  
  @DisplayName("Temperature Evaluator With Negative Data")
  @Test
  public void temperatureEvaluatorWithNegativeData() {
    ArrayList<Integer> inputData = new ArrayList<Integer>(Arrays.asList(-1, -1, -1));
    Integer value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(0);
  }
  
  @DisplayName("Temperature Evaluator With Mix Positive & Negative Data")
  @Test
  public void temperatureEvaluatorWithPositiveNegativeData() {
    ArrayList<Integer> inputData = new ArrayList<Integer>(Arrays.asList(1, -2, -4, 4, 2, 3));
    Integer value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(3);
  }
  
  
  @DisplayName("Temperature Evaluator With Expected Data")
  @Test
  public void temperatureEvaluatorWithExpectedData() {
    ArrayList<Integer> inputData = new ArrayList<Integer>(Arrays.asList(1, 1, 1));
    Integer value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(0);

    inputData = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(2);

    inputData = new ArrayList<Integer>(Arrays.asList(3, 2, 1));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(0);

    inputData = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(3);

    inputData = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 4));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(3);

    inputData = new ArrayList<Integer>(Arrays.asList(2, 3, 10, 6, 4, 8, 1));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(8);

    inputData = new ArrayList<Integer>(Arrays.asList(7, 9, 5, 6, 3, 2));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(2);

    inputData = new ArrayList<Integer>(Arrays.asList(1, 2, 90, 10, 110));
    value = helper.processTemperature(inputData);
    assertThat(value).isEqualTo(109);
  }


}
