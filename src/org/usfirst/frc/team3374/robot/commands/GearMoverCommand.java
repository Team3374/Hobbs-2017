/*    */ package org.usfirst.frc.team3374.robot.commands;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.command.TimedCommand;
/*    */ import java.io.PrintStream;
/*    */ import org.usfirst.frc.team3374.robot.Robot;
/*    */ 
/*    */ public class GearMoverCommand extends TimedCommand
/*    */ {
/*  9 */   private int pos = -1;
/*    */   
/*    */   public GearMoverCommand(double timeout, int position) {
/* 12 */     super(timeout);
/* 13 */     this.pos = position;
/*    */   }
/*    */   
/*    */   protected void initialize() {
/* 17 */     System.out.println(getClass().toString() + "::init");
/*    */   }
/*    */   
/*    */   protected void execute() {
/* 21 */     Robot.oi.Target_Position = this.pos;
/*    */   }
/*    */   
/*    */   protected void end() {
/* 25 */     System.out.println(getClass().toString() + "::ending");
/*    */   }
/*    */ }

