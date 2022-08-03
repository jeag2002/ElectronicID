package com.edirectory.service.helper;

import ch.qos.logback.classic.Logger;
import com.edirectory.utils.EdirectoryUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** TemperatureEvaluatorHelper. */
@Component
@Slf4j
public class TemperatureEvaluatorHelper {

  /** ZERO value. */
  private static final Integer ZERO = 0;
  /** ONE value. */
  private static final Integer ONE = 1;
  
  /**
   * Process inputTemperatures.

   * @param inputTemperatures list of input temperatures.
   * @return maxDistance
   */
  public Integer processTemperature(List<Integer> inputTemperatures) {
    if (EdirectoryUtils.isNull(inputTemperatures)) {
      return ZERO;
    } else if (inputTemperatures.size() <= ONE) {
      return ZERO;
    } else {
      inputTemperatures = filterNegativesAndZeroValues(inputTemperatures);
      if (inputTemperatures.size() <= ONE) {
        return ZERO;  
      } else {
        return processListTemperature(inputTemperatures);
      }
    }
  }
  
  /**
   * Filter negatives values.

   * @param inputTemperatures list with positives/negatives values
   * @return list with no negatives values
   */
  
  private List<Integer> filterNegativesAndZeroValues(List<Integer> inputTemperatures) {
    return inputTemperatures.stream().filter(e -> (e > 0)).collect(Collectors.toList());
  }
  
  /**
   * Process ListTemperatures.

   * @param inputTemperatures list of input temperatures
   * @return maxDistance
   */
  private Integer processListTemperature(List<Integer> inputTemperatures)  {
    int diff = inputTemperatures.get(ONE) - inputTemperatures.get(ZERO);
    int curSum = diff;
    int maxSum = curSum;
    
    for (int i = ONE; i < inputTemperatures.size() - ONE; i++) {
      diff = inputTemperatures.get(i + ONE) - inputTemperatures.get(i);
      if (curSum > ZERO) {
        curSum += diff;
      } else {
        curSum = diff;
      }
    
      if (curSum > maxSum) {
        maxSum = curSum;
      }
    }
   
    if (maxSum < 0) {
      maxSum = 0;
    }
    return maxSum;
  }
}

