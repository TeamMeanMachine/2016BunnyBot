package org.team2471.bunnybot.subsystems;

import org.team2471.bunnybot.HardwareMap;
import org.team2471.bunnybot.defaultcommands.ShooterDefaultCommand;
import org.team2471.util.DummySubsystem;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
//  private final CANTalon panMotor = HardwareMap.ShooterMap.panMotor;
//  private final SpeedController shootMotor = HardwareMap.ShooterMap.shootMotor;
//  private final AnalogGyro shooterGyro = HardwareMap.ShooterMap.shooterGyro;
//  private final Servo tiltMotor = HardwareMap.ShooterMap.tiltMotor;
//  private final DigitalInput ammoSensor = HardwareMap.ShooterMap.ammoSensor;
//  private final Solenoid flashLight = HardwareMap.ShooterMap.flashLight;

  private final Talon panMotor = new Talon(3);
  private final SpeedController shootMotor = new Talon(5);
  private final AnalogGyro shooterGyro = new AnalogGyro(0);
  private final Servo tiltMotor = new Servo(9);
  private final DigitalInput ammoSensor = new DigitalInput(9);
  private final Solenoid flashLight = new Solenoid(0);

  public final Subsystem trigger = new DummySubsystem();




  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new ShooterDefaultCommand());
  }


   /**
   * use boolean to make sure that the shooter motor has finished its shooting
   * iteration and has completely returned to standby position ready for next shot
   *
   * @return a boolean that check the completion as stated above
   */
  public boolean isReady() {
    return ammoSensor.get();
  }

  /**
   * Runs the shooting motors.
   *
   * It is expected that the turret continues firing in full auto until
   * shooting is disabled.
   */
  public void enableShooting() {
    //double shooterPower = isReady() ? 0.5 : 0;
    double shooterPower = 0.5;
    shootMotor.set(shooterPower);
  }

  /**
   * Disables the shooting motors.
   */
  public void disableShooting() {
    shootMotor.set(0);
  }

  /**
   * Sets the power of the shooter
   *
   * @param power power to be set
   */
  public void setPan(double power) {
    panMotor.set(power);
  }


  public void setTilt(double angle){
    tiltMotor.setAngle(angle);
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
