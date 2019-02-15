/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constant;

/**
 * Add your docs here.
 */
public class LiftPID extends PIDSubsystem {

  double lastOutput = 0;

  public LiftPID() {
    // Intert a subsystem name and PID values here
    super("LiftPID", 0.05, 0, 0.1);
    // getPIDController().setContinuous(true);
    setOutputRange(-1, 1);
    setSetpoint(0);
    setPercentTolerance(Constant.liftPIDPercentTolerance);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return RobotMap.liftEncoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    SmartDashboard.putNumber("Lift PID raw Output", output);
    output = lastOutput + (output - lastOutput)/Constant.liftSmoothingFactor;
    if(output<Constant.liftSmoothingDeadZone&&output>-Constant.liftSmoothingDeadZone)output=0;
    Robot.liftSubsystem.operateLift(output);
    SmartDashboard.putNumber("Lift PID Setpoint", getSetpoint());
    SmartDashboard.putNumber("Lift Speed", output);
    SmartDashboard.putNumber("Lift Actuall Speed", RobotMap.liftEncoder.getRate());
    lastOutput = output;
  }

  @Override
  public void disable() {
    super.disable();
    Robot.liftSubsystem.stopLift();
  }

  public void resetLastOutput(){
    lastOutput = 0;
  }
}
