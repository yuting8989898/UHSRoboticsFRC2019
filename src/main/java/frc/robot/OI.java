/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.commandgroups.goToSetHeight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick mainOI;
  public static Joystick subOI;
  public static JoystickButton cargoIntake; // L1
  public static JoystickButton cargoShoot; // R1
  public static JoystickButton hatchLoadingStation; // Left
  public static JoystickButton hatchLevel1; // Down
  public static JoystickButton hatchLevel2; // Right
  public static JoystickButton hatchLevel3; // Up
  public static JoystickButton groundPickup; // PS button
  private static int lastPOV = -1;

  public static void init() {
    mainOI = new Joystick(Constant.mainOI);
    subOI = new Joystick(Constant.subOI);
  }

  public static void check() {
    if (subOI.getRawButton(Constant.DualShock4.left))
      new goToSetHeight(Constant.hatchLoading).start();
    else if (subOI.getPOV() == 90)
      new goToSetHeight(Constant.cargoPickup).start();
    else if (subOI.getRawButton(Constant.DualShock4.down))
      new goToSetHeight(Constant.hatch1).start();
    else if (subOI.getPOV() == 180)
      new goToSetHeight(Constant.cargo1).start();
    else if (subOI.getRawButton(Constant.DualShock4.right))
      new goToSetHeight(Constant.hatch2).start();
    else if (subOI.getPOV() == 270)
      new goToSetHeight(Constant.cargo2).start();
    else if (subOI.getRawButton(Constant.DualShock4.up))
      new goToSetHeight(Constant.hatch3).start();
    else if (subOI.getPOV() == 0)
      new goToSetHeight(Constant.cargo3).start();
    else if (subOI.getRawButton(Constant.DualShock4.ps))
      new goToSetHeight(Constant.ground).start();
  }

  /**
   * Cartesian X-Axis
   */
  public static double getDriveX() {
    return correctJoystick(-mainOI.getRawAxis(Constant.DualShock4.rightYAxis));
  }

  /**
   * Cartesian Y-Axis
   */
  public static double getDriveY() {
    return correctJoystick(-mainOI.getRawAxis(Constant.DualShock4.leftYAxis));
  }

  // TODO: make sure there's no "null pointer exception", and the button ids are
  // correct
  public static boolean getDriveLeft() {
    return mainOI.getRawButton(Constant.DualShock4.r1);
  }

  public static boolean getDriveRight() {
    return mainOI.getRawButton(Constant.DualShock4.l1);
  }

  public static double getArm() { // negative is going up
    return correctJoystick(0.85 * subOI.getRawAxis(Constant.DualShock4.rightYAxis));
  }

  public static double getWrist() {
    return 0.3 * (correctJoystick(subOI.getRawAxis(Constant.DualShock4.l2Axis))
        - correctJoystick(subOI.getRawAxis(Constant.DualShock4.r2Axis)));
  }

  public static double getLift() {
    return correctJoystick(-subOI.getRawAxis(Constant.DualShock4.leftYAxis)); // Left joystick
  }

  // cargo stuufs
  public static boolean getIntakePressed() {
    return subOI.getRawButton(Constant.DualShock4.l1);
  }

  public static boolean getRevIntakePressed() {
    return subOI.getRawButton(Constant.DualShock4.r1);
  }

  // general things
  public static double correctJoystick(double input) {
    return input > Constant.joystickDeadZone || input < -Constant.joystickDeadZone ? input : 0;
  }
}
