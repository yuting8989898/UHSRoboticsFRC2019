/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LiftCommand extends Command {
  int targetLevel = 0;

  public LiftCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.liftSubsystem);
    requires(Robot.liftPID);
    targetLevel = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.liftEncoder.reset();
    Robot.liftPID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //targetLevel = (int)SmartDashboard.getNumber("Lift Target Number", 0);
    SmartDashboard.putNumber("Lift Height", RobotMap.liftEncoder.getDistance());
    SmartDashboard.putNumber("Lift Target Height", Constant.liftLevels[targetLevel]);
    SmartDashboard.putNumber("Lift Target Number", targetLevel);
    // Finds the next target location for the lift to go
    switch (OI.getLift()) {
    case -1:
      // when the driver wants to make the lift go down
      if(targetLevel>0)targetLevel--;
      Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
      break;
    case 1:
      // when the driver wants the lift go up
      if(targetLevel<Constant.liftLevels.length-1)targetLevel++;
      Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
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
