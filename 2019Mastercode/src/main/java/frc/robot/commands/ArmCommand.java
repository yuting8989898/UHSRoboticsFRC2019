/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmCommand extends Command {

  public static double power = 0;
  public static int setpoint = 0;
  public static Encoder ArmEncoder = RobotMap.armEncoder;

  public ArmCommand(double _power, int _setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    power = _power;
    setpoint = _setpoint;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    ArmEncoder.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.armSubsystem.powerArm(power);
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(ArmEncoder.getRaw() == setpoint){
      Robot.armSubsystem.stopMotor();
      return true;
    }else{
        return false;

    }
    
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