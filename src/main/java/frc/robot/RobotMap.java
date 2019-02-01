/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static VictorSPX driveLeft1;
  public static VictorSPX driveLeft2;
  public static VictorSPX driveRight1;
  public static VictorSPX driveRight2;

  public static VictorSPX arm;
  public static VictorSPX wrist;
  public static AnalogPotentiometer armPot;
  public static AnalogPotentiometer wristPot;

  public static void init(){
    driveLeft1 = new VictorSPX(4);
    driveLeft2 = new VictorSPX(5);
    driveRight1 = new VictorSPX(6);
    driveRight2 = new VictorSPX(7);

    arm = new VictorSPX(2);
    wrist = new VictorSPX(3);
    
    armPot = new AnalogPotentiometer(3, 3600,0);
    wristPot = new AnalogPotentiometer(4, 3600,0);

  }
}
