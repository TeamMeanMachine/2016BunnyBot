package org.team2471.bunnybot;

import org.team2471.frc.lib.vector.Vector2;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {
  private final SpeedController steerMotor;
  private final SpeedController driveMotor;
  private final AnalogInput steerEncoder;
  private final PIDController steerController;
  private final Vector2 position;
  private double offset = 0;
  private final PIDSource steerSource = new PIDSource() {
    @Override
    public PIDSourceType getPIDSourceType() {
      return PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public double pidGet() {
      return -((steerEncoder.getVoltage() - offset) - 2.5) / 2.3 * 180;
    }
  };
  private double power = 0;

  public SwerveModule(SpeedController driveMotor, SpeedController steerMotor, AnalogInput steerEncoder, Vector2 position, double offset) {
    this.steerMotor = steerMotor;
    this.driveMotor = driveMotor;
    this.steerEncoder = steerEncoder;
    this.position = position;
    this.offset = offset;
    steerController = new PIDController(0.02, 0, 0.01, steerSource, steerMotor);
    steerController.enable();
    steerController.setInputRange(-180, 180);
    steerController.setContinuous();
    SmartDashboard.putData("Steer Controller", steerController);
  }

  public void drive(double drivePower, double steerAngle) {
    setAngle(steerAngle);
    driveMotor.set(drivePower);
  }

  public double getPower(double throttle, double steering) {
    Vector2 forwardVector = new Vector2(0, throttle);
    Vector2 delta = Vector2.subtract(position, Robot.driveTrain.getPivot());
    Vector2 turnVector = Vector2.normalize(Vector2.perpendicular(delta));

    turnVector = Vector2.multiply(turnVector, steering);
    Vector2 sumVector = Vector2.add(forwardVector, turnVector);
    power = Vector2.length(sumVector);
    double angle = Math.toDegrees(Vector2.angle(sumVector));
    if (Math.abs(angle) > 90) {
      sumVector = Vector2.multiply(sumVector, -1);
      angle = Math.toDegrees(Vector2.angle(sumVector));
      power = -power;
    }

    setAngle(angle);
    return power;
  }

  public void setFactor(double factor) {
    driveMotor.set(-power * factor);
  }

  public double getOffset() {
    return offset;
  }

  public void setOffset(double offset) {
    this.offset = offset;
  }

  private void setAngle(double angle) {
    steerController.setSetpoint(angle + offset);
  }
}
