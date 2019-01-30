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
    double x = mainOI.getRawAxis(3);
    return x > Constant.joystickDeadZone || x < -Constant.joystickDeadZone ? x : 0;
  }

  /**
 * Cartesian Y-Axis
 */
  public static double getDriveY(){
    double y = -mainOI.getRawAxis(1);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
  }
}
