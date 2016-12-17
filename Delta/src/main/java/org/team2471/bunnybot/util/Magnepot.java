package org.team2471.bunnybot.util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Magnepot extends AnalogInput {

  public Magnepot(int channel) {
    super(channel);
  }

  @Override
  public double pidGet() {
    return (getAverageVoltage() - 2.5) / 2.3 * 180;
  }
}
