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
  public static Joystick subOI;

  /**
   * Initialize all the controller
   */
  public static void init() {
    mainOI = new Joystick(0);
    subOI = new Joystick(1);
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

  //TODO map all the buttons for subOI correctly
  public static double getArm() {
    double y = subOI.getRawAxis(3);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
  }

  public static double getWrist() {
    double y = -subOI.getRawAxis(4);
    return y > Constant.joystickDeadZone || y < -Constant.joystickDeadZone ? y : 0;
  }

  public static double getLift() {
    double output = -subOI.getRawAxis(1);
    output = output > Constant.joystickDeadZone || output < -Constant.joystickDeadZone ? output : 0;
    if(output == 0){
      //-1~1 is reserved for the joystick
    if (subOI.getRawButton(1))  output = 2; //hatch loading station
    if (subOI.getPOV()==90 )           output = 3; //cargo loading station
    if (subOI.getRawButton(2))  output = 4; //hatch level 1
    if (subOI.getPOV()==180)           output = 5; //cargo level 1
    if (subOI.getRawButton(3))  output = 6; //hatch level 2
    if (subOI.getPOV()==270)           output = 7; //cargo level 2
    if (subOI.getRawButton(4))  output = 8; //hatch level 3
    if (subOI.getPOV()==0)             output = 9; //cargo level 3
    if (subOI.getRawButton(13)) output = 100;
    }
    SmartDashboard.putNumber("Lift Controller Value", output);
    return output;
  }

  public static boolean getIntakePressed(){
    return mainOI.getRawButton(4);
  }
  
  public static boolean getRevIntakePressed(){
    return mainOI.getRawButton(2);
  }
}
