package org.team2471.util;

public class BetterMath {
  public static double max(double firstNumber, double... numbers) {
    double max = firstNumber;
    for (double number : numbers) {
      if (number > max) {
        max = number;
      }
    }

    return max;
  }
}
