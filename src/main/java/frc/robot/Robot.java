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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.pidcontroller.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  //subsystem
  public static DriveSubsystem driveSubsystem;
  public static LiftSubsystem liftSubsystem;
  public static LiftPID liftPID;
  public static ArmSubsystem armSubsystem;
  public static WristSubsystem wristSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  public static SolenoidSubsystem solenoidSubsystem;

  //command
  public static DriveCommand driveCommand;
  public static LiftCommand liftCommand;
  public static ArmCommand armCommand;
  public static WristCommand wristCommand;
  public static IntakeCommand intakeCommand;
  public static SolenoidCommand solenoidCommand;

  public static SendableChooser<Boolean> liftLimitChooser;
  public static SendableChooser<Boolean> armLimitChooser;
  @Override
  public void robotInit() {
    RobotMap.init();
    OI.init();
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    liftSubsystem = new LiftSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    //solenoidSubsystem = new SolenoidSubsystem();
    liftPID = new LiftPID();
    
    driveCommand = new DriveCommand();
    liftCommand = new LiftCommand();
    armCommand = new ArmCommand();
    wristCommand = new WristCommand();
    intakeCommand = new IntakeCommand();
    //solenoidCommand = new SolenoidCommand();

    liftLimitChooser = new SendableChooser<Boolean>();
    armLimitChooser = new SendableChooser<Boolean>();
    liftLimitChooser.setDefaultOption("Yes", true);
    armLimitChooser.setDefaultOption("Yes", true);
    liftLimitChooser.addOption("No", false);
    armLimitChooser.addOption("No", false);
    SmartDashboard.putData("Lift soft limit", liftLimitChooser);
    SmartDashboard.putData("Arm soft limit", armLimitChooser);

    int width = 176;
    int height = 144;
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(width, height);
    camera.setFPS(40);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    driveCommand.isAuto = true;
    liftCommand.liftLimit = liftLimitChooser.getSelected();
    armCommand.armLimit = armLimitChooser.getSelected();
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
    Scheduler.getInstance().run();
  }
}
