/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pidcontroller;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WristPID extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  public WristPID() {
    // Intert a subsystem name and PID values here
    super("WristPID", 0.28, 0.0001, 0.9);
    setSetpoint(0);
    setOutputRange(-1, 1);
    setAbsoluteTolerance(50);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    return RobotMap.wristEncoder.getRaw();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.wristSubsystem.rotate(output);
  }
}
