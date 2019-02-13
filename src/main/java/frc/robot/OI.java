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

  /**
   * <pre>
   * -1 = use PID to go to a lower level
   *  0 = No action
   *  1 = use PID to go to a Higher level
   * 100= use PID to go to -1000(as low as possible) to reset the lift
   * </pre>
   */
  public static int getLift() {
    // TODO: change liftCommand to match these output values
    int output = 0;
    if (mainOI.getRawButtonPressed(1))output =  0; //hatch loading station
    if (mainOI.getPOV()==90 )output = 1; //cargo loading station
    if (mainOI.getRawButtonPressed(2))output =  2; //hatch level 1
    if (mainOI.getPOV()==180)output = 3; //cargo level 1
    if (mainOI.getRawButtonPressed(3))output =  4; //hatch level 2
    if (mainOI.getPOV()==270)output = 5; //cargo level 2
    if (mainOI.getRawButtonPressed(4))output =  6; //hatch level 3
    if (mainOI.getPOV()==0  )output = 7; //cargo level 3
    if (mainOI.getRawButtonPressed(13))
      output = 100;
    SmartDashboard.putNumber("Lift Controller Value", output);
    return output;
  }
}
