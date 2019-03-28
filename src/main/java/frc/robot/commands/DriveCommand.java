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

  double right, left, x, y, lastY;
  boolean leftTank, rightTank;

  public DriveCommand() {
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    right = left = x = y = lastY = 0;
    leftTank = rightTank = false;
    Robot.driveSubsystem.setCoast();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    leftTank = OI.getDriveLeft();
    rightTank = OI.getDriveRight();

    if (leftTank || rightTank) {
      if (leftTank) {
        left = 0.4;
      }
      if (rightTank) {
        right = 0.4;
      }
      if (right == 0)
        right = -0.4;
      if (left == 0)
        left = -0.4;

    } else {
      double x = OI.getDriveX();
      double y = OI.getDriveY();

      // if (y > 0)
      //   y = Math.pow(x, 0.7);
      // else
      //   y = -Math.pow(-y, 0.7);

      // limiting the change rate of y
      // if (y - lastY > Constant.driveYChangelimit)
      //   y = lastY + Constant.driveYChangelimit;
      // if (y - lastY < -Constant.driveYChangelimit)
      //   y = lastY - Constant.driveYChangelimit;
      // lastY = y;

      // limiting the maximum turnning speed
      // if (x > Constant.maxTurningSpeed)
      //   x = Constant.maxTurningSpeed;
      // else if (x < -Constant.maxTurningSpeed)
      //   x = -Constant.maxTurningSpeed;


      
      // left = y + x;
      // right = y - x;
      // if (left > 1) {
      //   right -= left - 1;
      //   left = 1;
      // } else if (left < -1) {
      //   right -= left + 1;
      //   left = -1;
      // }
      // if (right > 1) {
      //   left -= right - 1;
      //   right = 1;
      // } else if (right < -1) {
      //   left -= right + 1;
      //   right = -1;
      // }
      left=y;
      right=x;

    }

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
