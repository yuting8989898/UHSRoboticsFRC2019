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

public class WristCommand extends Command {
  boolean manualMode;
  public WristCommand() {
    requires(Robot.wristSubsystem);
    manualMode = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.wristEncoder.reset();
    Robot.wristPID.disable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double in = 0.2*OI.getWrist();
    //TODO Wrist PID
    SmartDashboard.putNumber("Wrist rot", RobotMap.wristEncoder.get());
    SmartDashboard.putNumber("Wrist angle", Math.toDegrees(Robot.wristSubsystem.getAngle()));
    /*if(in >= 2){ //Using pid
      if(manualMode){
        manualMode = false;
        RobotMap.arm.configPeakOutputReverse(-0.6, Constant.kTimeoutMs);
      }
       double target = -Robot.armSubsystem.angleToSensorUnit(Math.toRadians(Constant.armLevels[(int)in-1]));
       SmartDashboard.putNumber("Arm Target ", Constant.armLevels[(int)in-2]);
       SmartDashboard.putString("Target", Constant.inputLevels[(int)in-2]);
       Robot.armSubsystem.rotate(target, true);
    }
    if(in != 0){
      if(!manualMode){
        manualMode = true;
        Robot.wristPID.disable();
      }
      Robot.wristSubsystem.rotate(in);
    }*/
    /*if(Robot.wristPID.getPIDController().isEnabled()){
      Robot.wristPID.setSetpoint(Robot.wristSubsystem.angleToSensorUnit(Robot.armSubsystem.getAngle()));
    }*/
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
