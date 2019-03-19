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
    public static double joystickDeadZone = 0.037;

    public static double driveRampRate = 2.5; //Neutral to full throttle in sec
    public static double armRampRate = 1;
    public static double wristRampRate = 0.5;
    
    public static double driveSmoothingFactor = 5;
    public static double turnFactor = 50;

    public static double liftPIDPercentTolerance = 0.1; // Need testing
    public static int liftSmoothingFactor = 7; //Larger number = More smoothing
    public static double liftSmoothingDeadZone = 0.1; //Too complicated to explain, just dont touch it.
    public static double intakeSpeed = 0.4;
    public static double liftHoldPower = 0.01;
    public static double armMaxHoldPower = -0.34;

    /*   sensorUnit*gearRatio/unitPerDeg   */
    public static double armAngleRatio = (15.0/24.0)/(4096.0/360.0);
    public static double armOffset = 10;

    public static int liftMaxHeight = 15500;
    public static int armMaxHeight = 2300;
    public static int kTimeoutMs = 30;
    /**
     * <pre>
     * [0]: default starting position
     * [1]: hatch loading station
     * [2]: cargo loading station
     * [3]: hatch level 1   
     * [4]: cargo level 1
     * [5]: hatch level 2
     * [6]: cargo level 2
     * [7]: hath level 3
     * [8]: cargo level 3
     * </pre>
     */
    public static double[] liftLevels = { 0, 400, 500, 1000, 5000, 7500, 0, 10000, 14500 };
    public static double[] armLevels = { 0, 200, 400, 700, 1000, 1500, 2000 };
}
