/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constant;

public class WristToAngle extends Command {
  double setpoint, kp, ki, kd, error, integral, derivative, previous_error, dt, output;

  public WristToAngle(double targetAngle) {
    requires(Robot.wristSubsystem);
    setpoint = -Robot.wristSubsystem.angleToSensorUnit(Math.toRadians(targetAngle));
		SmartDashboard.putNumber("Wrist Target", targetAngle);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.wristEncoder.reset();
    kp = Constant.wristkp;
    ki = Constant.wristki;
    kd = Constant.wristkd;
    dt = Constant.dt;
    integral = 0;
    previous_error = error = setpoint - Robot.wristSubsystem.getWristEncoderValue();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    error = setpoint - Robot.wristSubsystem.getWristEncoderValue();
    integral = integral + error / dt;
    derivative = (error - previous_error) / (1000 / dt);
    output = kp * error + ki * integral + kd * derivative;
    if (output > 1)
    output = 1;
    if (output < -1)
    output = -1;
    previous_error = error;
    if(Robot.updateSmartDashboard){
      // SmartDashboard.putNumber("Wrist angle", Math.toDegrees(Robot.wristSubsystem.getAngle()));
      // SmartDashboard.putNumber("Wrist output", output);
      // SmartDashboard.putNumber("Wrist error", error);
      // SmartDashboard.putNumber("Wrist derivative", derivative);
      // SmartDashboard.putNumber("Wrist integral", integral);
    }
    Robot.wristSubsystem.rotate(output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //TODO: try lowering the wrist tolerance
    return (setpoint - Robot.wristSubsystem.getWristEncoderValue() < Constant.wristTolerance
        && setpoint - Robot.wristSubsystem.getWristEncoderValue() > -Constant.wristTolerance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.wristSubsystem.stopWrist();
    Scheduler.getInstance().add(new WristManual());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
