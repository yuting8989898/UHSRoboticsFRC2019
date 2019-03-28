/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.cscore.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.*;
import frc.robot.commands.*;
import frc.robot.subsystems.pidcontroller.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends TimedRobot {
  /*
   * TODO:set the appropriate neutral mode of motors 
   * TODO:update smart dashboard at a slower rate
   * TODO:move things from command to subsystems
   * TODO:change OI from returning inputs to button.whenPressed;
   * TODO:reorganize Constant
   */
  // subsystem
  public static DriveSubsystem driveSubsystem;
  public static LiftSubsystem liftSubsystem;
  public static ArmSubsystem armSubsystem;
  public static WristSubsystem wristSubsystem;
  public static IntakeSubsystem intakeSubsystem;

  // command
  public static DriveCommand driveCommand;
  public static ArmCommand armCommand;
  public static WristCommand wristCommand;
  public static WristPID wristPID;

  public static DriverStation driverStation;

  @Override
  public void robotInit() {
    driverStation = DriverStation.getInstance();

    RobotMap.init();
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    liftSubsystem = new LiftSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    wristPID = new WristPID();

    armCommand = new ArmCommand();
    wristCommand = new WristCommand();
    OI.init();
    driveCommand = new DriveCommand();

    int width = 176;
    int height = 144;
    UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture();
    UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();

    camera0.setResolution(width, height);
    camera0.setFPS(20);
    camera1.setResolution(width, height);
    camera1.setFPS(20);
  }

  @Override
  public void disabledInit() {
    liftSubsystem.setCoast();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    generalInit();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    OI.check();
  }

  @Override
  public void teleopInit() {
    generalInit();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    OI.check();
  }

  @Override
  public void testInit() {
    generalInit();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
    OI.check();
  }

  private void generalInit(){
    liftSubsystem.setBrake();
    liftSubsystem.resetLiftEncoder();
    armSubsystem.rotate(0, false);
  }
}
