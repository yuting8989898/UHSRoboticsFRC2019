/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constant;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Solenoid Command
 */
public class LiftCommand extends Command {
  public LiftCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.liftSubsystem);
    requires(Robot.liftPID);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.liftEncoder.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double current = RobotMap.liftEncoder.getDistance();
    //System.out.println("Encoder: " + current);
    // Finds the next target location for the lift to go
    switch (OI.getLift()) {
    /**
     * For automatically controlling the lift through PID.
     */
    case -2:
      // when the driver wants to make the lift go down
      if(!Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.enable();
      for (int i = Constant.liftLevels.length - 1; i >= 0; i--) {
        if (current > Constant.liftLevels[i]) {
          Robot.liftPID.setSetpoint(Constant.liftLevels[i]);
          break;
        }
      }
      break;
    case 2:
      // when the driver wants the lift go up
      if(!Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.enable();
      for (int i = 0; i <= Constant.liftLevels.length - 1; i++) {
        if (current < Constant.liftLevels[i]) {
          Robot.liftPID.setSetpoint(Constant.liftLevels[i]);
          break;
        }
      }
      break;
    /**
     * For manually controlling the lift.
     */
    case -1:
      if(Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.disable();
      Robot.liftSubsystem.operateLift(-1);
      break;
    case 1:
    if(Robot.liftPID.getPIDController().isEnabled())Robot.liftPID.disable();
      Robot.liftSubsystem.operateLift(1);
      break;
    }

    // disables the pid if it's on target
    if (Robot.liftPID.onTarget()&&Robot.liftPID.getPIDController().isEnabled())
      Robot.liftPID.disable();
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
