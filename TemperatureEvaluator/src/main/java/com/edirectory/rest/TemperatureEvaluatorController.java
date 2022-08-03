package com.edirectory.rest;

import com.edirectory.rest.beans.TemperatureEvaluatorRequest;
import com.edirectory.rest.beans.TemperatureEvaluatorResponse;
import com.edirectory.service.TemperatureEvaluator;
import com.edirectory.service.functions.MaxDiffConversor;
import com.edirectory.service.functions.TemperatureEvaluatorRequestConversor;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** TemperatureEvaluatorController.class. */
@RestController
@RequestMapping("/v2/stats")
public class TemperatureEvaluatorController {
  /** requestConversor. */
  @Autowired
  private TemperatureEvaluatorRequestConversor requestConversor;
  /** responseConversor. */
  @Autowired
  private MaxDiffConversor responseConversor;
  /** temperatureService. */
  @Autowired
  private TemperatureEvaluator service;

  /**
   * computeReadings. 

   * @param request list of temperatures.
   * @return computeResults
   */
  @RequestMapping(value = "/compute", method = RequestMethod.POST)
  public TemperatureEvaluatorResponse computeReadings(
         @Valid @RequestBody TemperatureEvaluatorRequest request) {
    return responseConversor.convert(service.temperatureDiff(requestConversor.convert(request)));
  }

}