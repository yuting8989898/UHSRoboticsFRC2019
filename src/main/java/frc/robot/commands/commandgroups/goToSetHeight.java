package frc.robot.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constant;
import frc.robot.commands.LiftToHeight;

public class goToSetHeight extends CommandGroup {
    public  goToSetHeight(int level) {
    	// addParallel(new ArmToAngle());
    	addParallel(new LiftToHeight(Constant.liftLevels[level]));
    	// addParallel(new WristToAngle(0.25));
    }
}