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
  private int targetLevel = 0;
  public boolean liftLimit;

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
    SmartDashboard.putNumber("Lift Target Height", Constant.liftLevels[targetLevel]);
    SmartDashboard.putNumber("Lift encoder", RobotMap.liftEncoder.getRaw());
    SmartDashboard.putNumber("lift voltage", RobotMap.lift.getMotorOutputVoltage());
    SmartDashboard.putNumber("lift output%", RobotMap.lift.getMotorOutputPercent());
    double input = OI.getLift();
    SmartDashboard.putNumber("Lift Controller", input);
    if (input > 1) {
      // using the pid to move the lift
      if (!Robot.liftPID.getPIDController().isEnabled()){
        Robot.liftPID.enable(); // enables the pid if pid not enabled
      }
      targetLevel = ((int) input) - 1;
      Robot.liftPID.setSetpoint(Constant.liftLevels[targetLevel]);
    } else if(input !=0) {
      //for manually controlling the lift
      if (Robot.liftPID.getPIDController().isEnabled()){
        Robot.liftPID.disable(); // disables the pid if pid enabled  
      }
      if(liftLimit){
        if(RobotMap.liftEncoder.get() <= -100 &&input < 0){
            input = 0;
          }
          if(RobotMap.liftEncoder.get() >= Constant.liftMaxHeight && input > 0){
            input = 0;
          }
      }
      Robot.liftSubsystem.operateLift(input);
    }
    if(input == 0 && !Robot.liftPID.getPIDController().isEnabled()){
      Robot.liftSubsystem.operateLift(input);
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
