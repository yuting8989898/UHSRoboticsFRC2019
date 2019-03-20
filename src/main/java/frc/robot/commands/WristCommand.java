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
    double in = OI.getWrist();
    //TODO Wrist PID
    SmartDashboard.putNumber("Wrist rot", RobotMap.wristEncoder.getRaw());
    SmartDashboard.putNumber("Wrist angle", Math.toDegrees(Robot.wristSubsystem.getAngle()));
    SmartDashboard.putBoolean("wrist pid", Robot.wristPID.getPIDController().isEnabled());
    if(in == 0 && !Robot.wristPID.getPIDController().isEnabled()){
      Robot.wristSubsystem.rotate(0);
      return;
    }
    if(Robot.wristPID.onTarget() && Robot.wristPID.getPIDController().isEnabled()){
      Robot.wristPID.disable();
      return;
    }
    if(in >= 2){ //Using pid
      if (!Robot.wristPID.getPIDController().isEnabled()){
        Robot.wristPID.enable(); // enables the pid if pid not enabled
      }
       double target = Robot.wristSubsystem.angleToSensorUnit(Math.toRadians(Constant.armLevels[(int)in-2]));
       SmartDashboard.putNumber("Wrist Target ", target);
       Robot.wristPID.setSetpoint(target);
    }
    else if(in != 0){
      if(Robot.wristPID.getPIDController().isEnabled()){
        Robot.wristPID.disable();
      }
      Robot.wristSubsystem.rotate(in);
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
