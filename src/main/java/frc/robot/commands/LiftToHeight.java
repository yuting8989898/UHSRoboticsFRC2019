/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constant;
import frc.robot.Robot;

public class LiftToHeight extends Command {
  double setpoint, kp, ki, kd, error, integral, derivative, previous_error, dt, output, lastOutput;

  public LiftToHeight(double targetHeight) {
    requires(Robot.liftSubsystem);
    setpoint = targetHeight;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    kp = Constant.liftkp;
    ki = Constant.liftki;
    kd = Constant.liftkd;
    dt = Constant.dt;
    integral = lastOutput = 0;
    previous_error = error = setpoint - Robot.liftSubsystem.getLiftEncoderValue();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    error = setpoint - Robot.liftSubsystem.getLiftEncoderValue();
    integral = integral + error/dt;
    derivative = (error - previous_error) / (1000/dt);
    output = kp * error + ki * integral + kd * derivative;
    if (output > 1)
      output = 1;
    if (output < -1)
      output = -1;
    
    output = lastOutput + (output - lastOutput)/Constant.liftSmoothingFactor;
    if(output<Constant.liftSmoothingDeadZone&&output>-Constant.liftSmoothingDeadZone)output=0;
    lastOutput = output;
    previous_error = error;
    if(Robot.updateSmartDashboard){
      SmartDashboard.putNumber("Lift encoder", Robot.liftSubsystem.getLiftEncoderValue());
      SmartDashboard.putNumber("Lift output", output);
      SmartDashboard.putNumber("Lift error", error);
      SmartDashboard.putNumber("Lift derivative", derivative);
      SmartDashboard.putNumber("Lift integral", integral);
    }

    // output = lastOutput + (output - lastOutput)/Constant.liftSmoothingFactor;
    // if(output<Constant.liftSmoothingDeadZone&&output>-Constant.liftSmoothingDeadZone)output=0;
    // Robot.liftSubsystem.operateLift(output);
    // lastOutput = output;

    Robot.liftSubsystem.operateLift(output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (setpoint - Robot.liftSubsystem.getLiftEncoderValue() < Constant.liftTolerance
        && setpoint - Robot.liftSubsystem.getLiftEncoderValue() > -Constant.liftTolerance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.liftSubsystem.stopLift();
    Scheduler.getInstance().add(new LiftManual());
  }

  @Override
  protected void interrupted(){
    end();
  }
}
