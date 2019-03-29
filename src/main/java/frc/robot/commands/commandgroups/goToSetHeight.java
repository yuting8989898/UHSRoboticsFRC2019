package frc.robot.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constant;
import frc.robot.commands.LiftToHeight;
import frc.robot.commands.WristToAngle;

public class goToSetHeight extends CommandGroup {
    public  goToSetHeight(int level) {
    	// addParallel(new ArmToAngle());
    	addParallel(new LiftToHeight(Constant.liftLevels[level]));
    	addParallel(new WristToAngle(Constant.wristLevels[level]));
    }
}