/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.CommandGroup;
/*    */ import org.usfirst.frc.team3374.robot.RobotMap;
/*    */ 
/*    */ public class NonVisionCommandGroup extends CommandGroup
/*    */ {
/*    */   public NonVisionCommandGroup()
/*    */   {
/* 10 */     addSequential(new GearMoverCommand(1.0D, RobotMap.centergear));
/* 11 */     addSequential(new DriveForwardCommand(3.0D, 0.6D));
/*    */   }
/*    */ }

