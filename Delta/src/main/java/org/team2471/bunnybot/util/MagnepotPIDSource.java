package org.team2471.bunnybot.util;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class MagnepotPIDSource implements PIDSource {

  private AnalogInput encoder;

  public MagnepotPIDSource(AnalogInput encoder) {
    this.encoder = encoder;
  }

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {

  }

  @Override
  public PIDSourceType getPIDSourceType() {
    return null;
  }

  @Override
  public double pidGet() {
    return (encoder.getVoltage()-2.5)/2.3*180;
  }
}
