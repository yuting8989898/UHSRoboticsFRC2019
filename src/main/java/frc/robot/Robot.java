/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.commands.*;
import frc.robot.subsystems.pidcontroller.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends TimedRobot {
  //subsystem
  public static DriveSubsystem driveSubsystem;
  public static LiftSubsystem liftSubsystem;
  public static LiftPID liftPID;
  public static ArmSubsystem armSubsystem;
  public static WristSubsystem wristSubsystem;
  
  //command
  Command autoCommand;
  public static DriveCommand driveCommand;
  public static LiftCommand LiftCommand;
  public static ArmCommand armCommand;
  public static WristCommand wristCommand;
  

  @Override
  public void robotInit() {
    RobotMap.init();
    OI.init();
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    liftSubsystem = new LiftSubsystem();
    liftPID = new LiftPID();
    
    driveCommand = new DriveCommand();
    LiftCommand = new LiftCommand();
    armCommand = new ArmCommand();
    wristCommand = new WristCommand();

    CameraServer.getInstance().startAutomaticCapture();
    
    //m_chooser.setDefaultOption("Default Auto", driveCommand);
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
    RobotMap.liftEncoder.setDistancePerPulse(Constant.liftDistancePerPulse);

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    driveCommand.isAuto = true;
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    driveCommand.isAuto = false;
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit(){
  }
  
  @Override
  public void testPeriodic(){
  }
}
