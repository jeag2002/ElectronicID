package com.edirectory.service;

import com.edirectory.service.beans.TemperatureEvaluatorBean;

/** Temperature evaluator interface. */
public interface TemperatureEvaluator {
  /**
   * TemperatureDiff evaluator.

   * @param inputBean inputData
   * @return max value
   */
  public Integer temperatureDiff(TemperatureEvaluatorBean inputBean);

}
