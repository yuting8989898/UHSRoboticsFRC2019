/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
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

  public static VictorSPX lift;
  public static Encoder liftEncoder;
  public static DigitalInput liftResetSwitch;

  public static TalonSRX arm;
  public static TalonSRX wrist;
  public static VictorSPX wrist1;
  public static Spark intake;
  public static AnalogPotentiometer armPot;
  public static AnalogPotentiometer wristPot;
  public static DigitalInput intakeSwitch;

  public static void init() {
    // the driving stuffs
    driveLeft1 = new VictorSPX(6);
    driveLeft2 = new VictorSPX(7);
    driveRight1 = new VictorSPX(9);
    driveRight2 = new VictorSPX(10);

    // the lift stuffs
    lift = new VictorSPX(8);
    liftEncoder = new Encoder(0, 1, false, EncodingType.k4X);

    // the arm stuffs
    arm = new TalonSRX(16);
    wrist1 = new VictorSPX(11);
    wrist = new TalonSRX(17);
    intake = new Spark(0);
    intakeSwitch = new DigitalInput(3);
  }
}
