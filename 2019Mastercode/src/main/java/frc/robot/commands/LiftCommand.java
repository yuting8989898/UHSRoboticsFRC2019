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

public class LiftCommand extends Command {
  public static double power = 0;
  public static int setPoint = 0;
  public static Encoder liftEncoder = RobotMap.liftEncoder;
  public LiftCommand(double _power, int _setPoint) {
    power = _power;
    setPoint = _setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    liftEncoder.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.liftSubsystem.powerLift(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (liftEncoder.getDistance()>= setPoint){
      return true;
    } else{
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
