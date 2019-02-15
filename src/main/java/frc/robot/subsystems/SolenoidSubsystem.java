/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class SolenoidSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(Robot.solenoidCommand);
  }

  // true = extend, false = retract
  public void operateSolenoidA(boolean direction) {
    if (direction) {
      RobotMap.solenoid1.set(DoubleSolenoid.Value.kForward);
      RobotMap.solenoid2.set(DoubleSolenoid.Value.kForward);
    } else {
      RobotMap.solenoid1.set(DoubleSolenoid.Value.kReverse);
      RobotMap.solenoid2.set(DoubleSolenoid.Value.kReverse);
    }
  }
  
  // true = extend, false = retract
  public void operateSolenoidB(boolean direction) {
    if (direction) {
      RobotMap.solenoid3.set(DoubleSolenoid.Value.kForward);
    } else {
      RobotMap.solenoid3.set(DoubleSolenoid.Value.kForward);
    }
  }
  
  public void stopSolenoids() {
    RobotMap.solenoid1.set(DoubleSolenoid.Value.kOff);
    RobotMap.solenoid2.set(DoubleSolenoid.Value.kOff);
    RobotMap.solenoid3.set(DoubleSolenoid.Value.kOff);
  }

}
