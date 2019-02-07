/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;

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
  public static VictorSPX lift1;
  public static VictorSPX lift2;
  public static VictorSPX arm;
  public static VictorSPX wrist;
  public static VictorSPX intake;
  public static AnalogPotentiometer armPot;
  public static AnalogPotentiometer wristPot;
  public static Encoder liftEncoder;
  public static DigitalInput intakeSwitch;
  public static PigeonIMU gyro;

  public static void init(){
    lift1 = new VictorSPX(9);
    lift2 = new VictorSPX(1);
    arm = new VictorSPX(2);
    wrist = new VictorSPX(3);
    driveLeft1 = new VictorSPX(4);
    driveLeft2 = new VictorSPX(5);
    driveRight1 = new VictorSPX(6);
    driveRight2 = new VictorSPX(7);
    armPot = new AnalogPotentiometer(3, 3600,0);
    wristPot = new AnalogPotentiometer(4, 3600,0);
  
    liftEncoder = new Encoder(0,1,false,EncodingType.k4X);

    intake = new VictorSPX(0);
    intakeSwitch = new DigitalInput(3);
    //TODO Correct the port
    gyro = new PigeonIMU(8);
  }
}
