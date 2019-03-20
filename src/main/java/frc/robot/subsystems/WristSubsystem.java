/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constant;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(Robot.wristCommand);
  }

  /**
 * A@param val - up is positive
 */
  public void rotate(double val){
    RobotMap.wrist.set(ControlMode.PercentOutput, val);
  }

  public double getAngle(){
    double sensorUnit = RobotMap.wristEncoder.getRaw();
    return sensorUnit*Constant.wristAngleRatio + Constant.wristOffset;
  }

  public int angleToSensorUnit(double angle){
    return (int)((angle - Constant.wristOffset)/Constant.wristAngleRatio);
  }
}
