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
  public static Joystick mainOI;

   /**
 * Initialize all the controller
 */
  public static void init(){
    mainOI = new Joystick(0);
  }

   /**
 * Cartesian X-Axis
 */
  public static double getDriveX(){
    double x = mainOI.getRawAxis(2);
    return x > Constant.joystickDeadZone || x < -Constant.joystickDeadZone ? x : 0;
  }

  /**
 * Cartesian Y-Axis
 */
  public static double getDriveY(){
    double y = -mainOI.getRawAxis(1);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
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
    boolean liftManualToggle = mainOI.getRawButton(3);
    if(mainOI.getRawButton(1))output--;
    if(mainOI.getRawButton(2))output++;
    if(mainOI.getRawButton(3))output*=2;
    return output;
  }
}
