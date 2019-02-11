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
  double turnBias = 0;
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(Robot.driveCommand);
  }

  public void drive(double right, double left){
    RobotMap.driveLeft1.set(ControlMode.PercentOutput,left*(1+turnBias));
    RobotMap.driveLeft2.set(ControlMode.PercentOutput,left*(1+turnBias));
    RobotMap.driveRight1.set(ControlMode.PercentOutput,right*(1-turnBias));
    RobotMap.driveRight2.set(ControlMode.PercentOutput,right*(1-turnBias));
  }

  public void setPIDTurnFactor(double turnBias){
    this.turnBias = turnBias;
  }
}
