package com.edirectory.validator.impl;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.utils.EdirectoryUtils;
import com.edirectory.validator.TemperatureValidator;
import org.springframework.stereotype.Component;

/** TemperatureValidator. */
@Component
public class TemperatureValidatorImpl implements TemperatureValidator {

  @Override
  public void validate(TemperatureEvaluatorBean inputBean) {
    if (EdirectoryUtils.isNull(inputBean)) {
      throw new NullPointerException("inputBean is null");
    } else if (EdirectoryUtils.isNull(inputBean.getInputTemperatures())) {
      throw new NullPointerException("input temperatures list is null");
    }
  }

  @Override
  public void validate(TemperatureEvaluatorRequest request) {
    if (EdirectoryUtils.isNull(request)) {
      throw new NullPointerException("request object is null");  
    } else if (EdirectoryUtils.isNull(request.getReadings())) {
      throw new NullPointerException("readings list is null");
    }
  }

}
