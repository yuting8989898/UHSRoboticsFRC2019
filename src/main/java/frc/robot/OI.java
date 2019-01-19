/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick driveOI;

  public static void init() {
    driveOI = new Joystick(0);
  }

  public static double getDriveX() {
    return driveOI.getRawAxis(0);
  }

  public static double getDriveY() {
    return -driveOI.getRawAxis(1);
  }

  public static boolean isSolenoidPressed() {
    System.out.println("SolenoidPressed: " + driveOI.getRawButton(0));
    return driveOI.getRawButton(0);
  }

  public static boolean isCompressorPressed() {
    return driveOI.getRawButton(1);
  }

  public static int getLift() {
    //all placeholder values
    int output = 0;
    boolean liftLow = driveOI.getRawButton(1);
    boolean liftMid = driveOI.getRawButton(2);
    boolean liftHigh = driveOI.getRawButton(3);
    boolean liftHatchToggle = driveOI.getRawButton(4);
    if((liftLow&&liftMid)||(liftLow&&liftHigh)||(liftMid&&liftHigh)){
      return -1;
    }

    if(liftLow)output = 1;
    if(liftMid)output = 3;
    if(liftHigh)output = 5;
    if(liftHatchToggle)output--;
    return output;
  }

  public static boolean getLiftMid() {
    // placeholder value
    return driveOI.getRawButton(2);
  }

  public static boolean getLiftBot() {
    // placeholder value
    return driveOI.getRawButton(3);
  }

  public static boolean getLiftHatchToggle() {
    // placeholder value
    return driveOI.getRawButton(4);
  }
}
