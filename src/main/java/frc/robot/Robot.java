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
    cameraThread();
    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    liftSubsystem = new LiftSubsystem();
    liftPID = new LiftPID();
    
    driveCommand = new DriveCommand();
    LiftCommand = new LiftCommand();
    armCommand = new ArmCommand();
    wristCommand = new WristCommand();
    
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

  public void cameraThread(){
    /*Ask me if you don't understand the description of these method (hover over them)
      I am just following this
      https://wpilib.screenstepslive.com/s/currentCS/m/vision/l/669166-using-the-cameraserver-on-the-roborio
      OpenCV is a very good library for vision processing it seems
    */
    new Thread(() -> {
    try{
      int width = 256;
      int length = 144;
      int crossHair = 20;
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(width, length);
      camera.setFPS(40);
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Vision", width, length);
      
      //OpenCV matrix
      Mat source = new Mat();
      while(!Thread.interrupted()) {
        cvSink.grabFrameNoTimeout(source); //store image file in three 3-bit channels in BGR format
        //Imgproc.line(source,new Point(width/2-crossHair, length/2), new Point(width/2+crossHair,length/2), new Scalar(0,0,255));
        Imgproc.line(source,new Point(width/2-crossHair, length/2), new Point(width/2+crossHair,length/2), new Scalar(0,0,255),5);
        Imgproc.line(source,new Point(width/2, length/2-crossHair), new Point(width/2,length/2+crossHair), new Scalar(0,0,255),5);
        outputStream.putFrame(source);
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    
  }).start();
  }
}
