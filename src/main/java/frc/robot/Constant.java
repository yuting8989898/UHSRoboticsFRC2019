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
    public static double joystickDeadZone = 0.035;
    public static double autoDriveSpeed = 0.5;
    public static double turnFactor = 50;

    public static double intakeSpeed = 0.4;

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
    public static double[] liftLevels = { 0, 400, 500, 1000, 5000, 7500, 9000, 10000, 14500 }; //TODO tune these values
    public static int liftResetTimer = 100;
    public static double liftPIDPercentTolerance = 0.1; // Need testing
    public static int liftSmoothingFactor = 7; //Larger number = More smoothing
    public static double liftSmoothingDeadZone = 0.1; //Too complicated to explain, just dont touch it.

}
