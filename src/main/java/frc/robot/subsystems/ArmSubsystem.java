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
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(Robot.armCommand);
    rotate(0,false);
  }


  /**
 * @param val - up is negative
 */
  public void rotate(double val,boolean isPosition){
    if(isPosition){
      RobotMap.arm.set(ControlMode.Position,val);
    }
    else{
      RobotMap.arm.set(ControlMode.PercentOutput,val);
    }
  }

  public double getAngle(){
    double sensorUnit = -RobotMap.arm.getSelectedSensorPosition();
    return Math.toRadians(sensorUnit*Constant.armAngleRatio + Constant.armOffset);
  }

  public double getArmHoldPower(){
    return Constant.armMaxHoldPower*Math.sin(getAngle());
  }
}