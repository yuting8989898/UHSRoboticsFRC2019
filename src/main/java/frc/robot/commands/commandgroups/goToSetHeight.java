package frc.robot.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constant;
import frc.robot.Robot;
import frc.robot.commands.ArmToAngle;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.WristToAngle;

public class goToSetHeight extends CommandGroup {
	public goToSetHeight(int level) {
		SmartDashboard.putString("Target", Constant.inputLevels[level]);
		// addParallel(new ArmToAngle(-Robot.armSubsystem.angleToSensorUnit(Math.toRadians(Constant.armLevels[level]))));
		addParallel(new LiftToHeight(Constant.liftLevels[level]));
		addParallel(new WristToAngle(Constant.wristLevels[level]));
	}
}