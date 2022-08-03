package com.edirectory.service.functions;

import com.edirectory.rest.beans.TemperatureEvaluatorResponse;
import com.edirectory.utils.EdirectoryUtils;
import org.springframework.stereotype.Component;

/** MaxDiffConversor. */
@Component
public class MaxDiffConversor {
  /** ZERO VALUE. */
  private static final Integer ZERO = 0;

  /**
   * MaxDiff conversor.

   * @param maxDiff maxConversion
   * @return conversion. 
   */
  public TemperatureEvaluatorResponse convert(Integer maxDiff) {
    TemperatureEvaluatorResponse response = new TemperatureEvaluatorResponse();
    if (EdirectoryUtils.isNull(maxDiff)) {
      response.setMaxDiff(ZERO);
    } else {
      response.setMaxDiff(maxDiff);
    }
    return response;
  }

}
