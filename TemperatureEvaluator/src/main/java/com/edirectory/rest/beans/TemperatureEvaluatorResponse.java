package com.edirectory.rest.beans;

import java.io.Serializable;
import lombok.Data;

/** EvaluatorDataResponse. */
@Data
public class TemperatureEvaluatorResponse implements Serializable {
  /** ID. */
  private static final long serialVersionUID = 1L;
  /** maxDiff. */
  private Integer maxDiff;

}
