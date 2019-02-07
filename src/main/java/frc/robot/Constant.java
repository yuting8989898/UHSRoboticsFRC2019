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
    public static int liftResetTimer = 100;
    public static double[] liftLevels = { 0, 50, 150, 250, 350, 450, 550 }; // Need testing
    public static double liftDistancePerPulse = Math.PI * 2 / 360; // Need testing
    public static double liftPIDPercentTolerance = 0.05; // Need testing
    public static int liftSmoothingFactor = 7; //Larger number = More smoothing
    public static int liftSmoothingDeadZone = 10; //Too complicated to explain, just dont touch it.
}
