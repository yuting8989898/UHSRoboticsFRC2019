/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constant;
import frc.robot.RobotMap;
import frc.robot.commands.WristManual;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristManual());
  }

  /**
 * A@param val - up is positive
 */
  public void rotate(double val){
    RobotMap.wrist.set(ControlMode.PercentOutput, val);
    SmartDashboard.putNumber("Wrist encoder", getWristEncoderValue());
  }

  public void stopWrist(){
    RobotMap.wrist.set(ControlMode.PercentOutput, 0);
  }

  public double getAngle(){
    double sensorUnit = RobotMap.wristEncoder.getRaw();
    return sensorUnit*Constant.wristAngleRatio + Constant.wristOffset;
  }

  public int angleToSensorUnit(double angle){
    return (int)((angle - Constant.wristOffset)/Constant.wristAngleRatio);
  }

  public double getWristEncoderValue(){
    return RobotMap.wristEncoder.getRaw();
  }
}
