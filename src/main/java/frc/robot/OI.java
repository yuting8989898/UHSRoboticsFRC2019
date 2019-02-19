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
    return correctJoystick(mainOI.getRawAxis(2)); //Left joystick
  }

  /**
   * Cartesian Y-Axis
   */
  public static double getDriveY() {
    return correctJoystick(-mainOI.getRawAxis(1)); //Right joystick
  }

  public static double getArm() {
    return correctJoystick(subOI.getRawAxis(5)); //Right joystick
  }

  public static double getWrist() {
    double output = correctJoystick(mainOI.getRawAxis(3)); //downward (left trigger) 
    return (output - correctJoystick(mainOI.getRawAxis(4)))*0.2; //upward (right trigger)

  }

  public static double getLift() {
    double output = correctJoystick(-subOI.getRawAxis(1)); //Left joystick
    if(output == 0){
      //-1~1 is reserved for the joystick
    if (subOI.getRawButton(1))  output = 2; //hatch loading station (left btn)
    if (subOI.getPOV()==270 )           output = 3; //cargo loading station(left pov)
    if (subOI.getRawButton(2))  output = 4; //hatch level 1 (down btn)
    if (subOI.getPOV()==180)           output = 5; //cargo level 1 (down pov)
    if (subOI.getRawButton(3))  output = 6; //hatch level 2 (right btn)
    if (subOI.getPOV()==90)           output = 7; //cargo level 2 (right pov)
    if (subOI.getRawButton(4))  output = 8; //hatch level 3 (up btn)
    // if (subOI.getPOV()==0)             output = 9; //cargo level 3 (up pov)
    }
    SmartDashboard.putNumber("Lift Controller Value", output);
    return output;
  }

  public static boolean getIntakePressed(){
    return mainOI.getRawButton(5); //Back Left btn
  }
  
  public static boolean getRevIntakePressed(){
    return mainOI.getRawButton(6); //back right btn
  }

  public static double correctJoystick(double input){
    return input > Constant.joystickDeadZone || input < -Constant.joystickDeadZone ? input : 0;
  }
  /**
   * 1 for toggeling solenoidA
   * 2 for toggeling solenoidB
   */
  public static int getSolenoid(){
    //TODO correct button id
  if(mainOI.getRawButton(1))return 1;
  if(mainOI.getRawButton(2))return 2;
  return 0;
  }
}
