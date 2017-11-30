/*     */ package org.usfirst.frc.team3374.robot.commands;
/*     */ 
/*     */ import com.ctre.CANTalon;
/*     */ import edu.wpi.first.wpilibj.RobotDrive;
/*     */ import edu.wpi.first.wpilibj.Timer;
/*     */ import edu.wpi.first.wpilibj.command.Command;
/*     */ import java.io.PrintStream;
/*     */ import org.usfirst.frc.team3374.robot.OI;
/*     */ import org.usfirst.frc.team3374.robot.Robot;
/*     */ import org.usfirst.frc.team3374.robot.RobotMap;
/*     */ 
/*     */ public class AutoDriveForwardState extends Command
/*     */ {
/*     */   private int currentState;
/*     */   private Timer myTimer;
/*     */   private double pause1time;
/*     */   private double driveTime;
/*     */   private boolean isThisCommandFinished;
/*     */   
/*     */   public AutoDriveForwardState()
/*     */   {
/*  22 */     this.currentState = 1;
/*  23 */     this.myTimer = new Timer();
/*  24 */     this.pause1time = 1.0D;
/*  25 */     this.driveTime = 5.0D;
/*  26 */     this.isThisCommandFinished = false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*  32 */     Robot.oi.Target_Position = RobotMap.centergear;
/*  33 */     Robot.oi.getGear().setPID(1.5D, 0.001D, 100.0D);
/*  34 */     this.myTimer.start();
/*  35 */     System.out.println("Entering State 1");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void execute()
/*     */   {
/*  41 */     switch (this.currentState)
/*     */     {
/*     */     case 1: 
/*  44 */       Robot.oi.Target_Position = RobotMap.centergear;
/*     */       
/*  46 */       if (Robot.oi.Target_Position == Robot.oi.getGear().getSetpoint()) {
/*  47 */         this.currentState = 2;
/*  48 */         this.myTimer.reset();
/*  49 */         System.out.println("Entering State 2");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 2: 
/*  54 */       if (this.myTimer.get() > this.pause1time) {
/*  55 */         this.currentState = 3;
/*  56 */         this.myTimer.reset();
/*  57 */         System.out.println("Entering State 3");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 3: 
/*  62 */       Robot.oi.Drive().arcadeDrive(0.0D, -0.6D);
/*  63 */       if (this.myTimer.get() > this.driveTime) {
/*  64 */         this.currentState = 4;
/*  65 */         System.out.println("Entering State 4");
/*     */       }
/*     */       break;
/*     */     case 4: 
/*  69 */       this.isThisCommandFinished = true;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void end()
/*     */   {
/* 105 */     Robot.oi.Drive().arcadeDrive(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void interrupted() {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean isFinished()
/*     */   {
/* 116 */     return this.isThisCommandFinished;
/*     */   }
/*     */ }
