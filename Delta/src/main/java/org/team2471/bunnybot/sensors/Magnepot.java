package org.team2471.bunnybot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class Magnepot extends AnalogInput {

  public Magnepot(int channel) {
    super(channel);
  }

  @Override
  public double pidGet() {
    return (getAverageVoltage() - 2.5) / 2.3 * 180;
  }
}
