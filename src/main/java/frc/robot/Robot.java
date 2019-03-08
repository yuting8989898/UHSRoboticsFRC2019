/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

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
    cameraThread();
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    liftSubsystem = new LiftSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    solenoidSubsystem = new SolenoidSubsystem();
    liftPID = new LiftPID();
    
    driveCommand = new DriveCommand();
    liftCommand = new LiftCommand();
    armCommand = new ArmCommand();
    wristCommand = new WristCommand();
    intakeCommand = new IntakeCommand();
    solenoidCommand = new SolenoidCommand();

    liftLimitChooser = new SendableChooser<Boolean>();
    armLimitChooser = new SendableChooser<Boolean>();
    liftLimitChooser.setDefaultOption("Yes", true);
    armLimitChooser.setDefaultOption("Yes", true);
    liftLimitChooser.addOption("No", false);
    armLimitChooser.addOption("No", false);
    SmartDashboard.putData("Lift soft limit", liftLimitChooser);
    SmartDashboard.putData("Arm soft limit", armLimitChooser);
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

  public void cameraThread(){
    /*Ask me if you don't understand the description of these method (hover over them)
      I am just following this
      https://wpilib.screenstepslive.com/s/currentCS/m/vision/l/669166-using-the-cameraserver-on-the-roborio
      OpenCV is a very good library for vision processing it seems
    */
    new Thread(() -> {
      try{
        int width = 176;
        int height = 144;
        //int crossHair = 20;
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(width, height);
        camera.setFPS(40);
        /*CvSink cvSink = CameraServer.getInstance().getVideo();
        CvSource outputStream = CameraServer.getInstance().putVideo("Vision", width, height);
        
        //OpenCV matrix
        Mat source = new Mat();
        Mat output = new Mat();
        while(!Thread.interrupted()) {
          cvSink.grabFrameNoTimeout(source); //store image file in three 3-bit channels in BGR format
          //Imgproc.line(source,new Point(width/2-crossHair, length/2), new Point(width/2+crossHair,length/2), new Scalar(0,0,255));
          Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
          Imgproc.line(output,new Point(output.width()/2-crossHair, output.height()/2), new Point(output.width()/2+crossHair,output.height()/2), new Scalar(0,0,255),4);
          Imgproc.line(output,new Point(output.width()/2, output.height()/2-crossHair), new Point(output.width()/2,output.height()/2+crossHair), new Scalar(0,0,255),4);
          SmartDashboard.putNumber("Vision 1 width", output.width());
          SmartDashboard.putNumber("Vision 1 height", output.height());
          outputStream.putFrame(output);
        }*/
      }
      catch(Exception e){
        e.printStackTrace();
      }
  }).start();
  }
}
