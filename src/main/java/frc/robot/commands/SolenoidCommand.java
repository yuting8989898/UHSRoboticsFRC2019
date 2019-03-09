/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SolenoidCommand extends Command {
  boolean solenoidAState, solenoidBState;
  public SolenoidCommand() {
    requires(Robot.solenoidSubsystem);
    solenoidAState = solenoidBState = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.compressor.setClosedLoopControl(true);
    Robot.solenoidSubsystem.stopSolenoidA();
    Robot.solenoidSubsystem.stopSolenoidB();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int input = OI.getSolenoid();
    if(input == 1){
        Robot.solenoidSubsystem.operateSolenoidA(!solenoidAState);
        solenoidAState=!solenoidAState;
      }else if(input == 2){
        Robot.solenoidSubsystem.operateSolenoidB(!solenoidBState);
        solenoidBState=!solenoidBState;
    }else if(input == -1){
      Robot.solenoidSubsystem.stopSolenoidA();
    }else if(input == -2){
      Robot.solenoidSubsystem.stopSolenoidB();
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
    RobotMap.compressor.setClosedLoopControl(false);
    Robot.solenoidSubsystem.stopSolenoidA();
    Robot.solenoidSubsystem.stopSolenoidB();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
