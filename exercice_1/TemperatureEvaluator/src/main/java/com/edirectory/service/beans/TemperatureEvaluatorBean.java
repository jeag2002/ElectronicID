package com.edirectory.service.beans;

import java.util.List;
import lombok.Data;

/** Temperature EvaluatorData. */
@Data
public class TemperatureEvaluatorBean {
  /** list of input temperatures. */
  List<Integer> inputTemperatures;
}
