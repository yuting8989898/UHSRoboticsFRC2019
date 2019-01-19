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
    //requires(Robot.LiftSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.liftEncoder.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int input = OI.getLift();
    if(input!=0){
      double current = RobotMap.liftEncoder.getDistance();
      if(input == -1){
        //when the driver wants to make the lift go down
        for(int i = Constant.liftLevels.length-1; i >= 0; i--){
          //find the next lift level to go 
          if(current>Constant.liftLevels[i]){
            Robot.liftPID.setSetpoint(Constant.liftLevels[i]);
            break;
          }
        }
      }else{
        //when the driver wants the lift go up
        for(int i = 0; i <= Constant.liftLevels.length-1; i++){
          //find the next lift level to go
          if(current<Constant.liftLevels[i]){
            Robot.liftPID.setSetpoint(Constant.liftLevels[i]);
            break;
          }
        }
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
