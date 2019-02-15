/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveCommand extends Command {

  public boolean isAuto;
  public DriveCommand() {
    isAuto = true;
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double x = OI.getDriveX();
    double y = OI.getDriveY();
    double left, right;
    //Constant speed in autonomous mode
    if(isAuto){
      if(y > 0){
        x = Constant.autoDriveSpeed;
      }
      else if(y < 0){
        x = -Constant.autoDriveSpeed;
      }
      if(x > 0){
        x = Constant.autoDriveSpeed;
      }
      else if(x < 0){
        x = -Constant.autoDriveSpeed;
      }
    }
    // SmartDashboard.putNumber("x", x);
    // SmartDashboard.putNumber("y", y);
    left = (y+x)/2;
    right = (y-x)/2;
    // SmartDashboard.putNumber("left", left);
    // SmartDashboard.putNumber("right", right);
    Robot.driveSubsystem.drive(right, left);
    
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
}
