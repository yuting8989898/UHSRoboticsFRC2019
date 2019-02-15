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
    RobotMap.liftEncoder.setDistancePerPulse(Constant.liftDistancePerPulse);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Lift Height", RobotMap.liftEncoder.getDistance());
    SmartDashboard.putNumber("Lift Target Height", Constant.liftLevels[targetLevel]);
    SmartDashboard.putNumber("Lift Target Number", targetLevel);
    SmartDashboard.putBoolean("Limit Switch Pressed", RobotMap.liftResetSwitch.get());
    SmartDashboard.putNumber("Lift reset timer", resetTimer);
    SmartDashboard.putNumber("Lift encoder raw value", RobotMap.liftEncoder.getRaw());
    // for making the timer work
    if (resetTimer > 0) resetTimer--;

    // if timer is not going, the limit switch was released and is now pressed
    if (RobotMap.liftResetSwitch.get() && !limitSwitchPressed && resetTimer == -1) {
      // for starting the reset
      limitSwitchPressed = true;
      // starts timer
      resetTimer = Constant.liftResetTimer;
      //enables the pid if pid not enabled
      if(!Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.enable(); 
      // resets pid setpoint
      targetLevel = 0;
      Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
      // stops lift
      Robot.liftSubsystem.stopLift();
      // disables lift
      Robot.liftSubsystem.disable();
    }

    if (resetTimer == Constant.liftResetTimer - 50) {// should be manually tuned
      // resets pid "lastOutput" value
      Robot.liftPID.resetLastOutput();
      RobotMap.liftEncoder.reset();// finally, resets the encoder after a 1s delay for the motor to fully stop (hopefully)
    }
    
    if (resetTimer == 0) {
      // for recovering the lift system from the reseted state, only runs once when the time finishes
      resetTimer = -1;
      Robot.liftSubsystem.enable();
    }

    if (resetTimer == -1 && !RobotMap.liftResetSwitch.get()) {
      limitSwitchPressed = false;
    }
    
    if (resetTimer == -1) {
      double input = OI.getLift();
      if (input==100) {
        //reseting lift my making it go down, a lot
        if (!RobotMap.liftResetSwitch.get()){
          if(!Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.enable(); //enables the pid if pid not enabled
          Robot.liftPID.setSetpoint(-1000);
        }
      } else if(input>1){
        //using the pid to move the lift
        if(!Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.enable(); //enables the pid if pid not enabled
        targetLevel = ((int)input)-1; 
        Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
      } else{
        //manually controlling the lift
        if(Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.disable(); //disables the pid if pid enabled
        Robot.liftSubsystem.operateLift(input);
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
