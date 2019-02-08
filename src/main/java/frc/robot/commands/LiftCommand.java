/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LiftCommand extends Command {
  int targetLevel = 0;
  int resetTimer = -1;
  boolean limitSwitchPressed = false;

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
    SmartDashboard.putNumber("Lift Height", RobotMap.liftEncoder.getDistance());
    SmartDashboard.putNumber("Lift Target Height", Constant.liftLevels[targetLevel]);
    SmartDashboard.putNumber("Lift Target Number", targetLevel);
    SmartDashboard.putBoolean("Limit Switch Pressed", RobotMap.liftResetSwitch.get());
    SmartDashboard.putNumber("Lift reset timer", resetTimer);
    // if timer is going
    if (resetTimer > 0)
      resetTimer--;
    if (resetTimer == Constant.liftResetTimer - 50){// should be manually tuned
      //resets pid "lastOutput"
      Robot.liftPID.resetLastOutput();
      RobotMap.liftEncoder.reset();// finally, resets the encoder after a 100ms delay for the motor to fully stop
    }
    if (resetTimer == 0) {
      // for recovering the lift system from the reseted state
      resetTimer = -1;
      // Robot.liftPID.enable();
      Robot.liftSubsystem.enable();
    }
    if (resetTimer == -1 && !RobotMap.liftResetSwitch.get()) {
      limitSwitchPressed = false;
    }
    // if timer is not going, the limit switch was released and is now pressed
    if (RobotMap.liftResetSwitch.get() && !limitSwitchPressed && resetTimer == -1) {
      // for starting the reset
      limitSwitchPressed = true;
      // starts timer
      resetTimer = Constant.liftResetTimer;
      // resets pid setpoint
      targetLevel = 0;
      Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
      // disables pid
      // Robot.liftPID.disable();
      // stops lift
      Robot.liftSubsystem.stopLift();
      Robot.liftSubsystem.disable();
    }
    // Finds the next target location for the lift to go
    if (resetTimer == -1) {
      switch (OI.getLift()) {
      case -1:
        // when the driver wants to make the lift go down
        if (targetLevel > 0)
          targetLevel--;
        Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
        break;
      case 1:
        // when the driver wants the lift go up
        if (targetLevel < Constant.liftLevels.length - 1)
          targetLevel++;
        Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
        break;
      case 100:
        if (!RobotMap.liftResetSwitch.get())
          Robot.liftPID.setSetpoint(-1000);
      }
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
