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
    //all placeholder values
    // int output = 0;
    // boolean liftLow = driveOI.getRawButton(1);
    // boolean liftMid = driveOI.getRawButton(2);
    // boolean liftHigh = driveOI.getRawButton(3);
    // boolean liftHatchToggle = driveOI.getRawButton(4);
    // if((liftLow&&liftMid)||(liftLow&&liftHigh)||(liftMid&&liftHigh)){
    //   return -1;
    // }

    // if(liftLow)output = 1;
    // if(liftMid)output = 3;
    // if(liftHigh)output = 5;
    // if(liftHatchToggle)output--;
    // return output;
    boolean liftDown = mainOI.getRawButton(1);
    boolean liftUp = mainOI.getRawButton(2);
    //if both button pressed or non of the buttons pressed
    if((liftDown&&liftUp)||(!liftDown&&!liftUp))return 0;
    //if only lift down is pressed
    else if(liftDown)return -1;
    //if only lift up is pressed
    else return 1;
  }
}
