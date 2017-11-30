/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.CommandGroup;
/*    */ import org.usfirst.frc.team3374.robot.RobotMap;
/*    */ 
/*    */ public class VisionGuidedWithTurnCommandGroup
/*    */   extends CommandGroup
/*    */ {
/*    */   public VisionGuidedWithTurnCommandGroup(boolean leftTurn)
/*    */   {
/* 11 */     addSequential(new GearMoverCommand(1.5D, RobotMap.centergear));
/* 12 */     addSequential(new DriveForwardCommand(1.6D, 0.6D));
/* 13 */     addSequential(new TurnCommand(0.8D, 0.6D, leftTurn));
/*    */     
/* 15 */     addSequential(new VisionGuidedCommand(10.0D));
/*    */     
/* 17 */     addSequential(new GearMoverCommand(1.5D, RobotMap.topgear));
/* 18 */     addSequential(new DriveForwardCommand(0.75D, 0.6D));
/* 19 */     addSequential(new GearWhackCommand(1.0D, RobotMap.placegear));
/* 20 */     addSequential(new GearMoverCommand(0.5D, RobotMap.topgear));
/* 21 */     addSequential(new DriveForwardCommand(0.4D, -0.4D));
/*    */   }
/*    */ }

