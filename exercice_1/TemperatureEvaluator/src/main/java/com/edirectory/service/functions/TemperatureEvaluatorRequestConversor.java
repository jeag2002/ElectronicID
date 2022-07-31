package com.edirectory.service.functions;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.service.beans.TemperatureEvaluatorBean;
import com.edirectory.utils.EdirectoryUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** TemperatureEvaluatorRequestConversor. */
@Component
public class TemperatureEvaluatorRequestConversor {
  /**
   * Temperature Conversor.

   * @param request inputData
   * @return data for the service.
   */
  public TemperatureEvaluatorBean convert(TemperatureEvaluatorRequest request)  {
    if (EdirectoryUtils.isNull(request)) {
      throw new NullPointerException("request object is null");  
    } else if (EdirectoryUtils.isNull(request.getReadings())) {
      throw new NullPointerException("readings list is null");
    } else {
      TemperatureEvaluatorBean inputBean = new TemperatureEvaluatorBean();
      inputBean.setInputTemperatures(Arrays.asList(request.getReadings()));
      return inputBean;
    }
  }
}
