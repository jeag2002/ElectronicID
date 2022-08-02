package com.edirectory.service.functions;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.validator.TemperatureValidator;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** TemperatureEvaluatorRequestConversor. */
@Component
public class TemperatureEvaluatorRequestConversor {
  /** Validator. */
  @Autowired 
  private TemperatureValidator validator;
  /**
   * Temperature Conversor.

   * @param request inputData
   * @return data for the service.
   */
  public TemperatureEvaluatorBean convert(TemperatureEvaluatorRequest request)  {
    validator.validate(request);
    TemperatureEvaluatorBean inputBean = new TemperatureEvaluatorBean();
    inputBean.setInputTemperatures(Arrays.asList(request.getReadings()));
    return inputBean;
  }
}
