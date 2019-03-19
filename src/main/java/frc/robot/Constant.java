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

    public static double driveRampRate = 1; //Neutral to full throttle in sec
    public static double armRampRate = 1;
    public static double wristRampRate = 0.5;
    
    public static double driveSmoothingFactor = 5;
    public static double turnFactor = 50;

    public static double liftPIDPercentTolerance = 0.1; // Need testing
    public static int liftSmoothingFactor = 7; //Larger number = More smoothing
    public static double liftSmoothingDeadZone = 0.1; //Too complicated to explain, just dont touch it.
    public static double intakeSpeed = 0.4;
    public static double liftHoldPower = 0.00;
    public static double armMaxHoldPower = -0.34;

    /*   sensorUnit*gearRatio/unitPerRad
                    (15.0/24.0)/(4096.0/2PI)     */
    public static double armAngleRatio = (15.0*2*Math.PI/(24.0*4096.0));
    public static double wristAngleRatio = 2*Math.PI/4096;
       /*   10 Degree    */
    public static double armOffset = 10.0*Math.PI/180.0;
    public static double wristOffset = 0.0;

    public static int liftMaxHeight = 15500;
    public static int armMaxHeight = 2300;
    public static int kTimeoutMs = 30;
    /**
     * <pre>
     * [0]: default starting position
     * [1]: hatch loading station
     * [2]: cargo pickup
     * [3]: hatch level 1   
     * [4]: cargo level 1
     * [5]: hatch level 2
     * [6]: cargo level 2
     * [7]: hath level 3
     * [8]: cargo level 3
     * </pre>
     */
    public static String[] inputLevels = {"hatch loading","cargo pickup","hatch 1","cargo 1", "hatch2","cargo2","hatch3","cargo3"};
    public static double[] liftLevels = { 0, 0, 0, 0, 0, 0, 10000, 14500 };
    public static double[] armLevels = { 25, 32,32, 75, 90, 128, 130, 130 };
}
