package frc.robot.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constant;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.WristToAngle;

public class goToSetHeight extends CommandGroup {
    public  goToSetHeight(int level) {
    	// addParallel(new ArmToAngle());
    	addParallel(new LiftToHeight(Constant.liftLevels[level]));
		addParallel(new WristToAngle(Constant.wristLevels[level]));
		SmartDashboard.putString("Target", Constant.inputLevels[level]);	
		SmartDashboard.putNumber("Arm Target ", Constant.armLevels[level]);
		SmartDashboard.putNumber("Lift Target", Constant.liftLevels[level]);
		SmartDashboard.putNumber("Wrist Target", Constant.wristLevels[level]);

    }
}