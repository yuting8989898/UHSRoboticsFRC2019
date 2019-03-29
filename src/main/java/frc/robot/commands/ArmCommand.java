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
  long startTime, endTime;
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
    startTime = System.nanoTime();
    SmartDashboard.putNumber("Arm Rotation", RobotMap.arm.getSelectedSensorPosition());
    in = OI.getArm();
    SmartDashboard.putNumber("Arm Angle",Math.toDegrees(Robot.armSubsystem.getAngle()));
    if(in == 0 && manualMode){
      Robot.armSubsystem.rotate(Robot.armSubsystem.getArmHoldPower(), false);
      return;
    }
    if(in >= 2){ //Using pid
      if(manualMode){
        manualMode = false;
        RobotMap.arm.configPeakOutputReverse(-0.75, Constant.kTimeoutMs);
      }
       double target = -Robot.armSubsystem.angleToSensorUnit(Math.toRadians(Constant.armLevels[(int)in-2]));
       SmartDashboard.putNumber("Arm Target ", Constant.armLevels[(int)in-2]);
       SmartDashboard.putString("Target", Constant.inputLevels[(int)in-2]);
       Robot.armSubsystem.rotate(target, true);
    }
    else if (in != 0) { 
      if(!manualMode) {
        manualMode = true;
        RobotMap.arm.configPeakOutputReverse(-1, Constant.kTimeoutMs);
      }
      in += Robot.armSubsystem.getArmHoldPower();
      Robot.armSubsystem.rotate(in, false);
    }
    
    endTime = System.nanoTime();
    SmartDashboard.putNumber("delta time", (endTime - startTime)/100000000);
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