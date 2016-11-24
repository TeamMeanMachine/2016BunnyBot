package org.team2471.bunnybot.subsystems;

import org.team2471.bunnybot.HardwareMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  private final CANTalon panMotor = HardwareMap.ShooterMap.panMotor;
  private final SpeedController shootMotor = HardwareMap.ShooterMap.shootMotor;
  private final AnalogGyro shooterGyro = HardwareMap.ShooterMap.shooterGyro;
  private final Servo tiltMotor = HardwareMap.ShooterMap.tiltMotor;
  private final AnalogInput ammoSensor = HardwareMap.ShooterMap.ammoSensor;
  private final Solenoid flashLight = HardwareMap.ShooterMap.flashLight;

  public final Subsystem trigger = new Subsystem() {
    @Override
    protected void initDefaultCommand() {
    }

    /**
     * Use the magnet sensor to check to make sure that the shooter motor has completed
     * a shooting iteration and is return back to standby position for next shot
     *
     * @return boolean which indicates whether the motor is back to standby
     */
    public boolean shooterMotorReady() {
      return ammoSensor.isAccumulatorChannel();
    }

    /**
     * Runs the shooting motors.
     *
     * It is expected that the turret continues firing in full auto until
     * shooting is disabled.
     */
    public void enable() {
      double shootPower = shooterMotorReady() ? 0.5 : 0;
      shootMotor.set(shootPower);
    }

    /**
     * Disables the shooting motors.
     */
    public void disableShooting() {
      shootMotor.set(-0.5);
    }
  };

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
    if (shooterGyroAngle > 0) {
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

  public void zeroAngle() {
    shooterGyro.reset();
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
