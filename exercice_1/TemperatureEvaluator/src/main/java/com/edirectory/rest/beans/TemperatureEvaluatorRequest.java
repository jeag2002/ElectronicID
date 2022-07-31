package com.edirectory.rest.beans;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/** TemperatureEvaluatorRequest. */
@Data
public class TemperatureEvaluatorRequest implements Serializable {
  /** ID. */
  private static final long serialVersionUID = 1L;
  /** readings. */
  @NotNull
  private Integer[] readings;
}
