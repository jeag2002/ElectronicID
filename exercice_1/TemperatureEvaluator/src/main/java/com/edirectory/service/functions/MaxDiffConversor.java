package com.edirectory.service.functions;

import com.edirectory.rest.beans.TemperatureEvaluatorResponse;
import org.springframework.stereotype.Component;

/** MaxDiffConversor. */
@Component
public class MaxDiffConversor {
  /**
   * MaxDiff conversor.

   * @param maxDiff maxConversion
   * @return conversion. 
   */
  public TemperatureEvaluatorResponse convert(Integer maxDiff) {
    TemperatureEvaluatorResponse response = new TemperatureEvaluatorResponse();
    response.setMaxDiff(maxDiff);
    return response;
  }

}
