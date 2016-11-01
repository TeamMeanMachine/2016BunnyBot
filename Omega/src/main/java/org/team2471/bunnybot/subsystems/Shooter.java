package org.team2471.bunnybot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2471.bunnybot.HardwareMap;

public class Shooter extends Subsystem {
    private final CANTalon panMotor = HardwareMap.ShooterMap.panMotor;
    private final SpeedController shootMotor = HardwareMap.ShooterMap.shootMotor;
    private final AnalogGyro shooterGyro =  HardwareMap.ShooterMap.shooterGyro;
    private final Servo tiltMotor = HardwareMap.ShooterMap.tiltMotor;
    private final AnalogInput ammoSensor = HardwareMap.ShooterMap.ammoSensor;
    private final Solenoid flashLight = HardwareMap.ShooterMap.flashLight;

    private final PIDController panController = new PIDController(0, 0, 0, new PIDSource() {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public double pidGet() {
            return getAngle();
        }
    }, panMotor);

    public Shooter() {
        panController.enable();
    }

  @Override
  protected void initDefaultCommand() {

  }

  /**
   * @return the current angle of the shooter
   */
  public double getAngle() {
    double shooterGyroAngle = shooterGyro.getAngle();
    if(shooterGyroAngle > 0 ) {
       shooterGyroAngle += 360;
    }
    return shooterGyroAngle % 360;
  }

  /**
   * Sets the target angle of the shooter
   *
   * @param angle angle to be set
   */
  public void setAngle(double angle) {
      panController.setSetpoint(angle);
  }

  /**
   * Runs the shooting motors.
   *
   * It is expected that the turret continues firing in full auto until
   * shooting is disabled.
   */
  public void enableShooting() {
      shootMotor.set(0.5);
  }

  /**
   * Disables the shooting motors.
   */
  public void disableShooting() {
    shootMotor.set(-0.5);
  }

  /**
   * Turns on the flashlight.
   */
  public void enableFlashlight() {
    flashLight.set(true);
  }

  /**
   * Turns off the flashlight.
   */
  public void disableFlashlight() {
      flashLight.set(false);
  }
}
