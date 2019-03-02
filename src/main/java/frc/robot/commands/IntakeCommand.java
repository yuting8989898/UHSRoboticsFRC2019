/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IntakeCommand extends Command {

  private double curSpeed;
  private double increment;
  public IntakeCommand() {
    requires(Robot.intakeSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    curSpeed = 0;
    increment = Constant.intakeSpeed/10;
  }
  

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(OI.getIntakePressed()){
      if(curSpeed < Constant.intakeSpeed){
        curSpeed += increment; //accelerate in 100ms (1s)
      }
      else{
        curSpeed = Constant.intakeSpeed;
      }
    }
    else if(OI.getRevIntakePressed()){
      
        curSpeed = -3*Constant.intakeSpeed;
    }
    else{ //nothing pressed
      if(curSpeed > 0) {
        curSpeed -= increment; //deccelerate in 100ms (1s)
      }
      else if(curSpeed < 0){
        curSpeed += increment;
      }
      if (Math.abs(curSpeed) < increment) { //tolerance
        curSpeed = 0;
      }
    }
    Robot.intakeSubsystem.set(curSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
