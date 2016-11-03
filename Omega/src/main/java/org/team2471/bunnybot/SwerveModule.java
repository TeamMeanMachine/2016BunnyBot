package org.team2471.bunnybot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {
  private final CANTalon steerMotor;
  private final CANTalon driveMotor;
  private final AnalogInput steerEncoder;
  private final PIDController steerController;
  private final PIDSource steerSource = new PIDSource() {
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {

    }

    @Override
    public PIDSourceType getPIDSourceType() {
      return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
      SmartDashboard.putNumber("steer error", (steerEncoder.getVoltage()-2.6)/2.4*180);
      return -(steerEncoder.getVoltage()-2.6)/2.4*180;
    }
  };

  public SwerveModule(CANTalon driveMotor, CANTalon steerMotor, AnalogInput steerEncoder) {
    this.steerMotor = steerMotor;
    this.driveMotor = driveMotor;
    this.steerEncoder = steerEncoder;
    steerController = new PIDController ( 0.01, 0, 0.01, steerSource, steerMotor);
    steerController.enable();
    steerController.setInputRange(-180, 180);
    steerController.setContinuous();
    SmartDashboard.putData("Steer Controller", steerController);
  }
  public void drive(double steerPower, double drivePower ){
    steerController.setSetpoint(60*steerPower);
    driveMotor.set( drivePower);

  }
}
