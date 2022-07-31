package com.edirectory.service.impl;

import com.edirectory.service.TemperatureEvaluator;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.service.helper.TemperatureEvaluatorHelper;
import com.edirectory.utils.EdirectoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TemperatureEvaluator Implementation Logic. 
 */
@Service
public class TemperatureEvaluatorImpl implements TemperatureEvaluator {
  
  /** Helper. */
  @Autowired
  private TemperatureEvaluatorHelper helper;
  
  @Override
  public Integer temperatureDiff(TemperatureEvaluatorBean inputBean) {
    if (EdirectoryUtils.isNull(inputBean)) {
      throw new NullPointerException("inputBean is null");
    } else if (EdirectoryUtils.isNull(inputBean.getInputTemperatures())) {
      throw new NullPointerException("input temperatures list is null");
    } else {
      return helper.processTemperature(inputBean.getInputTemperatures());
    }
  }

}
