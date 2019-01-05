/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Motor
  public static VictorSP leftDrive1;
  public static VictorSP leftDrive2;
  public static VictorSP rightDrive1;
  public static VictorSP rightDrive2;
  public static VictorSP liftMotor;
  public static VictorSP armMotor;
  //Analog Sensor
  public static Encoder leftEncoder;
  public static Encoder rightEncoder;
  public static Encoder liftEncoder;
  public static Encoder armEncoder;
  public static AnalogGyro gyro;
  //Digital Sensor
  public static DigitalInput liftUpLimit;
  public static DigitalInput liftDownLimit;

  public static void initRobotMap(){
    leftDrive1 = new VictorSP(0);
    leftDrive2 = new VictorSP(1);
    rightDrive1 = new VictorSP(2);
    rightDrive2 = new VictorSP(3);
    liftMotor = new VictorSP(4);
    armMotor = new VictorSP(5);

    leftEncoder = new Encoder(0,1,false,EncodingType.k4X);
    rightEncoder = new Encoder(2,3,false,EncodingType.k4X);
    liftEncoder = new Encoder (4,5,false,EncodingType.k4X);
    armEncoder = new Encoder(6,7,false,EncodingType.k4X);
    gyro = new AnalogGyro(6);

    liftUpLimit = new DigitalInput(0);
    liftDownLimit = new DigitalInput(1);
  }
}