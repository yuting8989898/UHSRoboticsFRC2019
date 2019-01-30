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
    //System.out.println("SolenoidPressed: " + driveOI.getRawButton(0));
    return driveOI.getRawButton(1);
  }

  public static boolean isCompressorPressed() {
    return driveOI.getRawButton(1);
  }

  /**
   * <pre>
   * -2 = use PID to go to a lower level
   * -1 = manually going down
   *  0 = No action
   *  1 = manually going up
   *  2 = use PID to go to a Higher level
   * </pre>
   */
  public static int getLift() {
    //all button numbers are placeholders
    int output = 0;



    if(driveOI.getRawButton(2))output--;
    if(driveOI.getRawButton(4))output++;
    if(!driveOI.getRawButton(5)){
      if(driveOI.getRawButtonReleased(2))output=-2;
      if(driveOI.getRawButtonReleased(4))output=2;
    }
    
    
    if(output==2||output==-2)System.out.println("\n\n");
    System.out.println("Output for lift is:    " + output+"");
    if(output==2||output==-2)System.out.println("\n\n");
    return output;
  }
}
