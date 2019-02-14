/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick mainOI;

  /**
   * Initialize all the controller
   */
  public static void init() {
    mainOI = new Joystick(0);
  }

  /**
   * Cartesian X-Axis
   */
  public static double getDriveX() {
    double x = mainOI.getRawAxis(2);
    return x > Constant.joystickDeadZone || x < -Constant.joystickDeadZone ? x : 0;
  }

  /**
   * Cartesian Y-Axis
   */
  public static double getDriveY() {
    double y = -mainOI.getRawAxis(1);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
  }

  public static double getArm() {
    double y = mainOI.getRawAxis(3);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
  }

  public static double getWrist() {
    double y = -mainOI.getRawAxis(4);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
  }

  public static int getLift() {
    int output = 0;
    if (mainOI.getRawButtonPressed(1))output =  1; //hatch loading station
    if (mainOI.getPOV()==90 )output = 2; //cargo loading station
    if (mainOI.getRawButtonPressed(2))output =  3; //hatch level 1
    if (mainOI.getPOV()==180)output = 4; //cargo level 1
    if (mainOI.getRawButtonPressed(3))output =  5; //hatch level 2
    if (mainOI.getPOV()==270)output = 6; //cargo level 2
    if (mainOI.getRawButtonPressed(4))output =  7; //hatch level 3
    if (mainOI.getPOV()==0  )output = 8; //cargo level 3
    if (mainOI.getRawButtonPressed(13))
      output = 100;
    SmartDashboard.putNumber("Lift Controller Value", output);
    return output;
  }
}
