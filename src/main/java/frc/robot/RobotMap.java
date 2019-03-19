/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static VictorSPX driveLeft1;
  public static VictorSPX driveLeft2;
  public static VictorSPX driveRight1;
  public static VictorSPX driveRight2;

  public static VictorSPX lift;
  public static Encoder liftEncoder;

  public static TalonSRX arm;
  public static TalonSRX wrist;
  public static VictorSPX wrist1;
  public static Encoder wristEncoder;
  public static Spark intake;

  public static void init() {
    // the driving stuffs
    driveLeft1 = new VictorSPX(6);
    driveLeft2 = new VictorSPX(7);
    driveRight1 = new VictorSPX(9);
    driveRight2 = new VictorSPX(10);
    //This should fix acceleration (Not decceleration)
    driveLeft1.configOpenloopRamp(Constant.driveRampRate, Constant.kTimeoutMs);
    driveLeft2.configOpenloopRamp(Constant.driveRampRate, Constant.kTimeoutMs);
    driveRight1.configOpenloopRamp(Constant.driveRampRate, Constant.kTimeoutMs);
    driveRight2.configOpenloopRamp(Constant.driveRampRate, Constant.kTimeoutMs);
    
    // the lift stuffs
    lift = new VictorSPX(8);
    liftEncoder = new Encoder(0, 1, false, EncodingType.k4X);

    // the arm stuffs
    arm = new TalonSRX(16);
    configArm();
    wrist = new TalonSRX(17);
    wrist1 = new VictorSPX(11);
    wrist1.follow(wrist);
    wrist.configOpenloopRamp(Constant.driveRampRate, Constant.kTimeoutMs);
    liftEncoder = new Encoder(2, 3, false, EncodingType.k4X);
    intake = new Spark(0);
  }

  private static void configArm(){
    arm.configOpenloopRamp(Constant.armRampRate, Constant.kTimeoutMs);
    /* Ensure sensor velocity is positive when output is positive */
    arm.setSensorPhase(true);
    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    arm.setSelectedSensorPosition(0);
		arm.configNominalOutputForward(0, Constant.kTimeoutMs);
		arm.configNominalOutputReverse(0, Constant.kTimeoutMs);
    arm.configAllowableClosedloopError(0,0, Constant.kTimeoutMs);
    arm.configPeakOutputForward(0, Constant.kTimeoutMs);

    arm.config_kP(0, 4.0,Constant.kTimeoutMs);
    arm.config_kI(0, 0.001,Constant.kTimeoutMs);
    arm.config_kD(0, 0.0005,Constant.kTimeoutMs);
  }
}
