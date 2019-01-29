/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(Robot.driveCommand);
  }

  public void drive(double x, double y){
    RobotMap.driveLeft1.set(ControlMode.PercentOutput,x+y);
    RobotMap.driveLeft2.set(ControlMode.PercentOutput,x+y);
    RobotMap.driveRight1.set(ControlMode.PercentOutput,x-y);
    RobotMap.driveRight2.set(ControlMode.PercentOutput,x-y);
  }
}
