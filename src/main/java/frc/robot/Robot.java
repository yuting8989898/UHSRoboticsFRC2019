/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.WristCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class Robot extends TimedRobot {
  //subsystem
  public static DriveSubsystem driveSubsystem;
  public static ArmSubsystem armSubsystem;
  public static WristSubsystem wristSubsystem;
  
  public static DriveCommand driveCommand;
  public static ArmCommand armCommand;
  public static WristCommand wristCommand;

  @Override
  public void robotInit() {
    RobotMap.init();
    OI.init();
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    
    driveCommand = new DriveCommand();
    armCommand = new ArmCommand();
    wristCommand = new WristCommand();
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
