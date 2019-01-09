/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static VictorSP driveLeft1;
  public static VictorSP driveLeft2;
  public static VictorSP driveRight1;
  public static VictorSP driveRight2;

  public static Solenoid solenoid;
  public static void init(){
    driveLeft1 = new VictorSP(0);
    driveLeft2 = new VictorSP(1);
    driveRight1 = new VictorSP(2);
    driveRight2 = new VictorSP(3);

    solenoid = new Solenoid(0);
  }
}
