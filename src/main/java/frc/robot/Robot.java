/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
// import frc.robot.vision.GripFindCargo;

public class Robot extends TimedRobot {
  // smartDashboard things
  public static final boolean DEBUG = true;
  public static boolean updateSmartDashboard = false;

  /*
   * TODO:set the appropriate neutral mode of motors TODO:change OI from returning
   * inputs to button.whenPressed;
   */
  // subsystem
  public static DriveSubsystem driveSubsystem;
  public static LiftSubsystem liftSubsystem;
  public static ArmSubsystem armSubsystem;
  public static WristSubsystem wristSubsystem;
  public static IntakeSubsystem intakeSubsystem;

  // command
  public static ArmCommand armCommand;

  // others
  public static int loopCount, dashboardUpdatePeriod;
  public static double deltaTime;
  public static double lastTime;
  public static DriverStation driverStation;
  public static VisionThread visionThread;
  // public static CvSource output;

  @Override
  public void robotInit() {
    if (DEBUG)
      updateSmartDashboard = true;
    RobotMap.init();

    driverStation = DriverStation.getInstance();
    loopCount = 0;
    dashboardUpdatePeriod = Constant.dashboardUpdatePeriod;

    driveSubsystem = new DriveSubsystem();
    armSubsystem = new ArmSubsystem();
    wristSubsystem = new WristSubsystem();
    liftSubsystem = new LiftSubsystem();
    intakeSubsystem = new IntakeSubsystem();

    armCommand = new ArmCommand();

    OI.init();

    // Camera things
    int width = 176;
    int height = 144;
    UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture();
    // UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();
    Mat gripOutput = new Mat();
    camera0.setResolution(width, height);
    camera0.setFPS(20);
    // camera1.setResolution(width, height);
    // camera1.setFPS(20);
    // output = CameraServer.getInstance().putVideo("Grip", width, height);

    
    /*visionThread = new VisionThread(camera0, new GripFindCargo(), pipeline -> {
      KeyPoint[] blobsList = pipeline.findBlobsOutput().toArray();
      pipeline.cvErodeOutput().copyTo(gripOutput);
      if (blobsList.length != 0) {
        SmartDashboard.putNumber("Blobs Count", blobsList.length);
        for (int i = 0; i < blobsList.length; i++) {
          System.out.println("Blob #" + (i + 1) + "\nAngle: " + blobsList[i].angle + " Id: " + blobsList[i].class_id
              + " Octave: " + blobsList[i].octave + " pt: " + blobsList[i].pt.x + " " + blobsList[i].pt.y
              + " Response: " + blobsList[i].response + " Size: " + blobsList[i].size);
          Imgproc.circle(gripOutput, blobsList[i].pt, (int)blobsList[i].size/2, new Scalar(255, 0, 0));
        } 
      }
      output.putFrame(gripOutput);
    });
    visionThread.start();
    */
  }

  @Override
  public void robotPeriodic() {
    if (!DEBUG) {
      if (loopCount > dashboardUpdatePeriod) {
        updateSmartDashboard = true;
        loopCount = 0;
      } else {
        updateSmartDashboard = false;
        loopCount++;
      }
    }
    deltaTime = (System.currentTimeMillis() - lastTime) / 1000;
    lastTime = System.currentTimeMillis();
    SmartDashboard.putNumber("Delta Time", deltaTime);
  }

  private void generalInit() {
    liftSubsystem.setBrake();
    RobotMap.resetEncoders();
    armSubsystem.rotate(0, false);
  }

  private void generalPeriodic() {
    Scheduler.getInstance().run();
    OI.check();
  }

  //Periodics
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousPeriodic() {
    generalPeriodic();
  }

  @Override
  public void teleopPeriodic() {
    generalPeriodic();

  }

  @Override
  public void testPeriodic() {
    generalPeriodic();
  }
  
  //Inits
  @Override
  public void disabledInit() {
    liftSubsystem.setCoast();
  }

  @Override
  public void autonomousInit() {
    generalInit();
  }

  @Override
  public void teleopInit() {
    generalInit();
  }
  
  @Override
  public void testInit() {
    generalInit();
  }
}
