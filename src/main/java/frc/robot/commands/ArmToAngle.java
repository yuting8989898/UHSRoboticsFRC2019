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
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmToAngle extends Command {
  double setpoint;

  public ArmToAngle(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.armSubsystem);
    this.setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.arm.configPeakOutputReverse(-0.75, Constant.kTimeoutMs);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.armSubsystem.rotate(setpoint, true);
    if (Robot.updateSmartDashboard) {
      SmartDashboard.putNumber("Arm Rotation", RobotMap.arm.getSelectedSensorPosition());
      SmartDashboard.putNumber("Arm Angle", Math.toDegrees(Robot.armSubsystem.getAngle()));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return OI.getArm()!=0;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.armSubsystem.rotate(Robot.armSubsystem.getArmHoldPower(), false);
    Scheduler.getInstance().add(new ArmManual());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}