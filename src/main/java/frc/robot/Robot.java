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
import frc.robot.commands.DriveCommand;
import frc.robot.commands.SolenoidCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.SolenoidSubsystem;

public class Robot extends TimedRobot {
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  //subsystem
  public static SolenoidSubsystem solenoidSubsystem;
  public static DriveSubsystem driveSubsystem;
  

  //command
  Command autoCommand;
  public static DriveCommand driveCommand;
  public static SolenoidCommand solenoidCommand;

  @Override
  public void robotInit() {
    RobotMap.init();
    OI.init();
    driveSubsystem = new DriveSubsystem();
    driveCommand = new DriveCommand();
    solenoidSubsystem = new SolenoidSubsystem();
    solenoidCommand = new SolenoidCommand();
    //m_chooser.setDefaultOption("Default Auto", driveCommand);
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    //autoCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   autoCommand.start();
    // }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (autoCommand != null) {
      autoCommand.cancel();
    }
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
    //Start the compressor
    //RobotMap.compressor.setClosedLoopControl(true);
  }
  
  @Override
  public void testPeriodic(){
  }
}
