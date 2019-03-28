/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

  public void drive(double right, double left){
    RobotMap.driveLeft1.set(ControlMode.PercentOutput,left);
    RobotMap.driveLeft2.set(ControlMode.PercentOutput,left);
    RobotMap.driveRight1.set(ControlMode.PercentOutput,-right);
    RobotMap.driveRight2.set(ControlMode.PercentOutput,-right);
  }
  public void setCoast(){
    RobotMap.driveLeft1.setNeutralMode(NeutralMode.Coast);
  }
}
