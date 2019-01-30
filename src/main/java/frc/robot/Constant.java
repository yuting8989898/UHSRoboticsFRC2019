/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constant {
    public static double joystickDeadZone = 0.03;
    public static double autoDriveSpeed = 0.5;
    public static double turnFactor = 50;
    public static double[] liftLevels = { 500, 1000, 1500, 2000, 2500, 3000 }; // prob wrong
    public static double liftUpLimit = 5000;
    public static double liftDownLimit = 0;
    public static double liftDistancePerPulse = Math.PI * 2 / 360; // prob wrong
    public static double liftPIDTolerance = 50; // prob wrong
}
