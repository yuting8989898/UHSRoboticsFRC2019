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

public class ArmCommand extends Command {
  private int startControl;
  private double lastOutput;
  public boolean armLimit;
  public ArmCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    startControl = 0;
    lastOutput = 0;
    requires(Robot.armSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.arm.getSensorCollection().setQuadraturePosition(0, Constant.kTimeoutMs);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Arm Rotation", RobotMap.arm.getSelectedSensorPosition());
    SmartDashboard.putNumber("arm output",RobotMap.arm.getMotorOutputPercent());
    double in = OI.getArm();
    SmartDashboard.putNumber("arm controller", in);
    SmartDashboard.putNumber("arm amp", RobotMap.arm.getMotorOutputVoltage());
    if(in >= 2){ //Using pid
      if(startControl == 1){
        startControl = 0;
        RobotMap.arm.configPeakOutputReverse(-0.6, Constant.kTimeoutMs);
      }
      if(in != lastOutput){ //prevent keep setting the target
        lastOutput = in;
        double target = -Constant.armLevels[(int)in-2];
        SmartDashboard.putNumber("Arm Target ", target);
        Robot.armSubsystem.rotate(target, true);
      }
    }
    else { 
      if(startControl == 0) {
        startControl = 1;
        lastOutput = 0;
        RobotMap.arm.configPeakOutputReverse(-1, Constant.kTimeoutMs);
      }
      if(armLimit){
        if(RobotMap.arm.getSelectedSensorPosition() <= 50 && in > 0){
          in = 0;
          }
          if(RobotMap.arm.getSelectedSensorPosition() >= Constant.armMaxHeight && in < 0){
            in = 0;
          }
      }
      Robot.armSubsystem.rotate(in, false);
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