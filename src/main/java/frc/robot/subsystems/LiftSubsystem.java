/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(Robot.LiftCommand);
  }

  boolean enabled = true;

  public void operateLift(double speedPercentage) {
    if(!enabled)return;
    RobotMap.lift.set(ControlMode.PercentOutput, speedPercentage);
  }

  public void stopLift() {
    if(!enabled)return;
    System.out.println("stopping lift");
    RobotMap.lift.set(ControlMode.PercentOutput, 0);
  }

  public void enable(){
    enabled = true;
  }

  public void disable(){
    enabled = false;
  }

}
