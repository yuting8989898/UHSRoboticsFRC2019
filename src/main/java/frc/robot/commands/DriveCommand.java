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

public class DriveCommand extends Command {

  double right, left;
  double lastright, lastleft;
  double leftdif, rightdif;

  public boolean isAuto;
  public DriveCommand() {
    isAuto = true;
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    lastleft = 0;
    lastright = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double x = OI.getDriveX();
    double y = OI.getDriveY();
    //TODO: make sure this works
    if(x>0) x=Math.sqrt(x);
    else x=-Math.sqrt(-x);
    if(y>0) y=Math.sqrt(y);
    else y=-Math.sqrt(-y);
    //TODO: make sure this actually works
    if(y>=0){
      left = y+x;
      right = y-x;
    }else{
      left = y-x;
      right = y+x;
    }
    // leftdif = left-lastleft;
    // if(Math.abs(left)<Math.abs(lastleft))leftdif=leftdif*2;
    // rightdif = right-lastright;
    // if(Math.abs(right)<Math.abs(lastright))leftdif=leftdif*2;
    //some sty acceleration/smoothing thing
    // if(left==0&&math.abs(lastleft))
    // if(right==0)lastright=0.05;
    // left = lastleft + (left - lastleft)/Constant.driveSmoothingFactor;
    // right = lastright + (right - lastright)/Constant.driveSmoothingFactor;
    // lastleft = left;
    // lastright = right;
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
