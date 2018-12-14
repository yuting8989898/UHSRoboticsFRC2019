/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  private XboxController controller;

  public OI() {
    controller = new XboxController(0);
  }

  public double getDriveX() {
    return controller.getX(Hand.kRight);
  }

  public double getDriveY() {
    return controller.getY(Hand.kLeft);
  }
  public boolean getLiftHigh(){
    
    return controller.getYButton();
  }
  public boolean getLiftLow(){
    return controller.getAButton();
  }
  public boolean getLiftMid(){
    return controller.getXButton();
  }
  public boolean getStopLift(){
    return controller.getTriggerAxis(Hand.kLeft)!=0;
  }
}