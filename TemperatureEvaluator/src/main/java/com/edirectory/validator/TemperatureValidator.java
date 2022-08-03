package com.edirectory.validator;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.service.beans.TemperatureEvaluatorBean;

/** temperature validator. */
public interface TemperatureValidator {
  /**
   * Validate temperatureEvaluatorBean object.

   * @param inputBean inputBean.
   */
  public void validate(TemperatureEvaluatorBean inputBean);
  
  /**
   * Validate temperatureEvaluatorRequest object.

   * @param request inputBean
   */
  public void validate(TemperatureEvaluatorRequest request);
}
