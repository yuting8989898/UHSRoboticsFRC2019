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

public class ArmCommand extends Command {
  private boolean manualMode;
  double in;

  public ArmCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    manualMode = true;
    requires(Robot.armSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.arm.getSensorCollection().setQuadraturePosition(0, Constant.kTimeoutMs);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    in = OI.getArm();
    if (in == 0 && manualMode) {
      Robot.armSubsystem.rotate(Robot.armSubsystem.getArmHoldPower(), false);
      return;
    }
    if (in >= 2) { // Using pid
      if (manualMode) {
        manualMode = false;
        RobotMap.arm.configPeakOutputReverse(-0.75, Constant.kTimeoutMs);
      }
      double target = -Robot.armSubsystem.angleToSensorUnit(Math.toRadians(Constant.armLevels[(int) in - 2]));
      Robot.armSubsystem.rotate(target, true);
    } else if (in != 0) {
      if (!manualMode) {
        manualMode = true;
        RobotMap.arm.configPeakOutputReverse(-1, Constant.kTimeoutMs);
      }
      in += Robot.armSubsystem.getArmHoldPower();
      Robot.armSubsystem.rotate(in, false);
    }

    if (Robot.updateSmartDashboard) {
      SmartDashboard.putNumber("Arm Rotation", RobotMap.arm.getSelectedSensorPosition());
      SmartDashboard.putNumber("Arm Angle", Math.toDegrees(Robot.armSubsystem.getAngle()));
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

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}