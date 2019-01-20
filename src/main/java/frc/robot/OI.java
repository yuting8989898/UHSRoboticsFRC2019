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
    /**
     * -2 = manually going down
     * -1 = use PID to go to a lower level
     *  0 = No action
     *  1 = use PID to go to a Higher level
     *  2 = manually going up
     */
    //all button numbers are placeholders
    int output = 0;
    boolean liftManualToggle = driveOI.getRawButton(3);
    if(driveOI.getRawButton(1))output--;
    if(driveOI.getRawButton(2))output++;
    if(driveOI.getRawButton(3))output*=2;
    return output;
  }
}
