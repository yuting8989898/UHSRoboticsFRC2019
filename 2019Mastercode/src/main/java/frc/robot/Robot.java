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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TestACommand;
import frc.robot.commands.TestBCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LiftSubsystem;


public class Robot extends TimedRobot {
  public static OI m_oi;
  int i = 0;
  public static DriveSubsystem driveSubsystem;
  public static ArmSubsystem armSubsystem;
  public static LiftSubsystem liftSubsystem;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    double distancePerPulse = Math.PI*Constant.WHEEL_DIAMETER/Constant.ENCODER_FULL_ROTATION;
    RobotMap.leftEncoder.setDistancePerPulse(distancePerPulse);
    RobotMap.rightEncoder.setDistancePerPulse(distancePerPulse);
    RobotMap.liftEncoder.setDistancePerPulse(distancePerPulse);
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    liftSubsystem = new LiftSubsystem();
  }

  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void autonomousInit() {
  }
  
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    i++;
    SmartDashboard.putString("Time (ms): ", Integer.toString(i));
  }

  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}