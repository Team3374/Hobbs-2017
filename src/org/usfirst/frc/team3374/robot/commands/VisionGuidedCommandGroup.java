/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.CommandGroup;
/*    */ import org.usfirst.frc.team3374.robot.RobotMap;
/*    */ 
/*    */ public class VisionGuidedCommandGroup extends CommandGroup
/*    */ {
/*    */   public VisionGuidedCommandGroup()
/*    */   {
/* 10 */     addSequential(new GearMoverCommand(1.5D, RobotMap.centergear));
/* 11 */     addSequential(new VisionGuidedCommand(10.0D));
/* 12 */     addSequential(new GearMoverCommand(1.0D, RobotMap.topgear));
/* 13 */     addSequential(new DriveForwardCommand(0.4D, 0.6D));
/* 14 */     addSequential(new GearWhackCommand(1.0D, RobotMap.placegear));
/*    */     
/* 16 */     addSequential(new GearMoverCommand(0.5D, RobotMap.topgear));
/* 17 */     addSequential(new DriveForwardCommand(0.75D, -0.4D));
/*    */   }
/*    */ }

