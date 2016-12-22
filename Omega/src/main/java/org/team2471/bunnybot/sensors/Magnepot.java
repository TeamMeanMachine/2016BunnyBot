package org.team2471.bunnybot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class Magnepot extends AnalogInput {

  boolean m_bReverse;

  public Magnepot(int channel) {
    super(channel);
  }

  public Magnepot(int channel, boolean bReverse) {
    super(channel);

    reverse(bReverse);
  }

  public void reverse(boolean bReverse) {
    m_bReverse = bReverse;
  }

  @Override
  public double pidGet() {
    return (m_bReverse ? -1.0 : 1.0) * (getAverageVoltage() - 2.5) / 2.3 * 180.0;
  }
}
