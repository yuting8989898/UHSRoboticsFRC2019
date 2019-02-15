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
  
  private static int testFlag;
  private static int testFlagPID;
  //subsystem
  public static DriveSubsystem driveSubsystem;
  public static LiftSubsystem liftSubsystem;
  public static LiftPID liftPID;
  public static ArmSubsystem armSubsystem;
  public static WristSubsystem wristSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  
  //command
  Command autoCommand;
  public static DriveCommand driveCommand;
  public static LiftCommand LiftCommand;
  public static ArmCommand armCommand;
  public static WristCommand wristCommand;
  

  public static IntakeCommand intakeCommand;
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
    liftPID = new LiftPID();
    
    driveCommand = new DriveCommand();
    LiftCommand = new LiftCommand();
    armCommand = new ArmCommand();
    wristCommand = new WristCommand();
    intakeCommand = new IntakeCommand();
    
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
    testFlag = 0;
    testFlagPID = 0;
    RobotMap.liftEncoder.reset();
  }
  
  @Override
  public void testPeriodic(){
    SmartDashboard.putNumber("Lift Height", RobotMap.liftEncoder.getRaw()*Constant.liftDistancePerPulse);
    SmartDashboard.putBoolean("Limit Switch Pressed", RobotMap.liftResetSwitch.get());
    SmartDashboard.putNumber("Lift encoder raw value", RobotMap.liftEncoder.getRaw());
    System.out.println(RobotMap.liftEncoder.getRaw());
    double arm = -OI.subOI.getRawAxis(1);
    arm = arm > Constant.joystickDeadZone || arm < -Constant.joystickDeadZone ? arm : 0;
    if(arm != 0){
      testFlag = 0;
      if(testFlagPID == 0){
        liftPID.disable();
        testFlagPID++;
      }
      liftSubsystem.operateLift(arm);
    }
    else if(testFlag == 0){
      testFlagPID = 0;
      testFlag++;
      liftSubsystem.operateLift(0);
      liftPID.setSetpoint(RobotMap.liftEncoder.getDistance()); //set it to the current height
      liftPID.enable();
    }
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
      Mat output = new Mat();
      while(!Thread.interrupted()) {
        cvSink.grabFrameNoTimeout(source); //store image file in three 3-bit channels in BGR format
        //Imgproc.line(source,new Point(width/2-crossHair, length/2), new Point(width/2+crossHair,length/2), new Scalar(0,0,255));
        Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
        Imgproc.line(output,new Point(width/2-crossHair, length/2), new Point(width/2+crossHair,length/2), new Scalar(0,0,255),5);
        Imgproc.line(output,new Point(width/2, length/2-crossHair), new Point(width/2,length/2+crossHair), new Scalar(0,0,255),5);
        
        outputStream.putFrame(output);
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    
  }).start();
  }
}
