package com.edirectory.service.functions.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.edirectory.rest.beans.TemperatureEvaluatorResponse;
import com.edirectory.service.functions.MaxDiffConversor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



/** MaxDiffConversorTest. */
public class MaxDiffConversorTest {

  private MaxDiffConversor conversor;
  
  /** Process setup. */
  @BeforeEach
  public void setUp() {
    conversor = new MaxDiffConversor();
  }
  
  @DisplayName("Conversion with Expected Data")
  @Test
  public void conversorWithNormalData() {
    TemperatureEvaluatorResponse response = conversor.convert(0);
    assertThat(response).isNotNull();
    assertThat(response.getMaxDiff()).isEqualTo(0);
    response = conversor.convert(2);
    assertThat(response).isNotNull();
    assertThat(response.getMaxDiff()).isEqualTo(2);
    response = conversor.convert(-1);
    assertThat(response).isNotNull();
    assertThat(response.getMaxDiff()).isEqualTo(-1);
  }
  
  @DisplayName("Conversion with Null Data")
  @Test
  public void conversorWithNullData() {
    TemperatureEvaluatorResponse response = conversor.convert(null);
    assertThat(response).isNotNull();
    assertThat(response.getMaxDiff()).isEqualTo(0);
  }
  

}
