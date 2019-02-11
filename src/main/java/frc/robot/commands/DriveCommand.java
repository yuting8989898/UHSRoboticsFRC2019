/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Utils;

public class DriveCommand extends Command {

  public boolean isAuto;
  public boolean pidOn;

  public DriveCommand() {
    isAuto = true;
    pidOn = false;
    requires(Robot.driveSubsystem);
    requires(Robot.drivePID);
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
    // Constant speed in autonomous mode
    if (isAuto) {
      y = Utils.sign(y) * Constant.autoDriveSpeed;
      x = Utils.sign(x) * Constant.autoDriveSpeed;
    }
    if (OI.getDriveRefine()) {
      y *= Constant.driveRefinePerc;
      x *= Constant.driveRefinePerc;
    }
    if (!pidOn && x == 0) { // If wasn't using pid previously, but driving foward rn
      double[] ypr = new double[3];
      RobotMap.gyro.getYawPitchRoll(ypr);
      SmartDashboard.putNumber("gyro", ypr[0]);
      Robot.drivePID.setSetpoint(ypr[0]);
      Robot.driveSubsystem.drive(y, y);
      Robot.drivePID.enable();
      pidOn = true;
    } else if (pidOn && x != 0) { // if using pid previously, but want to turn rn
      Robot.drivePID.disable();
      pidOn = false;
    } else if (pidOn) { // if using pid and don't want to turn
      Robot.driveSubsystem.drive(y, y);
    } else { // if not using pid and want to turn rn
      double left, right;
      left = (y - x) / 2;
      right = (y + x) / 2;
      Robot.driveSubsystem.drive(right, left);
    }

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
