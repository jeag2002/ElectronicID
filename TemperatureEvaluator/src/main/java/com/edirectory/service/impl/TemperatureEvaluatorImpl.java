package com.edirectory.service.impl;

import com.edirectory.service.TemperatureEvaluator;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.service.helper.TemperatureEvaluatorHelper;
import com.edirectory.validator.TemperatureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TemperatureEvaluator Implementation Logic. 
 */
@Service
public class TemperatureEvaluatorImpl implements TemperatureEvaluator {
  
  /** Validator. */
  @Autowired
  private TemperatureValidator validator;
  /** Helper. */
  @Autowired
  private TemperatureEvaluatorHelper helper;
  
  @Override
  public Integer temperatureDiff(TemperatureEvaluatorBean inputBean) {
    validator.validate(inputBean);
    return helper.processTemperature(inputBean.getInputTemperatures());
  }

}
