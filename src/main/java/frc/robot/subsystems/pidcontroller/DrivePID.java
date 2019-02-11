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
public class DrivePID extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  private double power;
  private double[] ypr;
  public DrivePID() {
    //TODO: tune PID
    super("DrivePID", 0.05, 0, 0.1);
    ypr = new double[3];
    power = 0;
    setOutputRange(-0.5,0.5);
    setAbsoluteTolerance(5);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    RobotMap.gyro.getYawPitchRoll(ypr);
    return ypr[0];
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveSubsystem.setPIDTurnFactor(output);
  }
}
