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
/*     */ 
/*     */ 
/*     */ public class AutoVisionStraightState
/*     */   extends Command
/*     */ {
/*     */   private int currentState;
/*     */   private Timer myTimer;
/*     */   private double pause1time;
/*     */   private double visionDriveTime;
/*     */   private boolean isThisCommandFinished;
/*  22 */   private double maxSpeed = 0.4D;
/*  23 */   private double minSpeed = 0.4D;
/*  24 */   private double currentSpeed = this.maxSpeed;
/*  25 */   private double angularError = 0.0D;
/*  26 */   private double cameraCenter = 160.0D;
/*     */   
/*  28 */   private double angularErrorScaleFactor = -0.01D;
/*  29 */   private boolean reachedTarget = false;
/*     */   
/*     */ 
/*  32 */   private double short_drive_power = 0.4D;
/*  33 */   private double short_drive_time = 2.0D;
/*     */   
/*     */ 
/*  36 */   private double short_backupdrive_power = 0.4D;
/*  37 */   private double short_backupdrive_time = 2.0D;
/*     */   
/*     */   public AutoVisionStraightState() {
/*  40 */     this.currentState = 1;
/*  41 */     this.myTimer = new Timer();
/*  42 */     this.pause1time = 1.0D;
/*  43 */     this.visionDriveTime = 10.0D;
/*  44 */     this.isThisCommandFinished = false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*  50 */     Robot.oi.Target_Position = RobotMap.centergear;
/*  51 */     Robot.oi.getGear().setPID(1.5D, 0.001D, 100.0D);
/*  52 */     this.myTimer.start();
/*  53 */     System.out.println("Entering State 1");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void execute()
/*     */   {
/*  59 */     switch (this.currentState)
/*     */     {
/*     */     case 1: 
/*  62 */       Robot.oi.Target_Position = RobotMap.centergear;
/*     */       
/*  64 */       if (Robot.oi.Target_Position == Robot.oi.getGear().getSetpoint()) {
/*  65 */         this.currentState = 2;
/*  66 */         this.myTimer.reset();
/*  67 */         System.out.println("Entering State 2");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 2: 
/*  72 */       if (this.myTimer.get() > this.pause1time) {
/*  73 */         this.currentState = 3;
/*  74 */         this.myTimer.reset();
/*  75 */         System.out.println("Entering State 3");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 3: 
/*  80 */       boolean isApproached = executeVisionDrive();
/*     */       
/*  82 */       if ((this.myTimer.get() > this.visionDriveTime) || (isApproached)) {
/*  83 */         this.currentState = 4;
/*  84 */         System.out.println("Entering State 4");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 4: 
/*  89 */       Robot.oi.Target_Position = RobotMap.topgear;
/*     */       
/*  91 */       if (Robot.oi.Target_Position == Robot.oi.getGear().getSetpoint()) {
/*  92 */         this.currentState = 5;
/*  93 */         this.myTimer.reset();
/*  94 */         System.out.println("Entering State 5");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 5: 
/*  99 */       Robot.oi.Drive().arcadeDrive(0.0D, -this.short_drive_power);
/* 100 */       if (this.myTimer.get() > this.short_drive_time) {
/* 101 */         this.currentState = 6;
/* 102 */         this.myTimer.reset();
/* 103 */         System.out.println("Entering State 6");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 6: 
/* 108 */       Robot.oi.Target_Position = RobotMap.placegear;
/*     */       
/* 110 */       if (Robot.oi.Target_Position == Robot.oi.getGear().getSetpoint()) {
/* 111 */         this.currentState = 7;
/* 112 */         this.myTimer.reset();
/* 113 */         System.out.println("Entering State 7");
/*     */       }
/*     */       
/*     */       break;
/*     */     case 7: 
/* 118 */       Robot.oi.Drive().arcadeDrive(0.0D, this.short_backupdrive_power);
/* 119 */       if (this.myTimer.get() > this.short_backupdrive_time) {
/* 120 */         this.currentState = 8;
/* 121 */         this.myTimer.reset();
/* 122 */         System.out.println("Entering End State");
/*     */       }
/*     */       break;
/*     */     case 8: 
/* 126 */       this.isThisCommandFinished = true;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean executeVisionDrive()
/*     */   {
/* 137 */     double[] liftPositionInfo = Robot.oi.getLiftPosition();
/*     */     
/* 139 */     if ((liftPositionInfo[0] < 0.0D) || (liftPositionInfo[1] < 0.0D)) {
/* 140 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 144 */     double currentRange = liftPositionInfo[1];
/*     */     
/*     */ 
/* 147 */     if (currentRange <= 80.0D)
/*     */     {
/* 149 */       double newSpeed = this.maxSpeed / 80.0D * currentRange;
/* 150 */       newSpeed = Math.max(newSpeed, this.minSpeed);
/* 151 */       newSpeed = Math.min(newSpeed, this.currentSpeed);
/* 152 */       this.currentSpeed = newSpeed;
/*     */     }
/* 154 */     this.angularError = (this.cameraCenter - liftPositionInfo[0]);
/*     */     
/* 156 */     double angleDrive = Math.min(0.5D, Math.max(this.angularError * this.angularErrorScaleFactor, -0.5D));
/*     */     
/*     */ 
/*     */ 
/* 160 */     Robot.oi.Drive().arcadeDrive(angleDrive, -this.currentSpeed);
/*     */     
/* 162 */     System.out.print("Range: " + Math.floor(currentRange));
/* 163 */     System.out.print(" Speed: " + Math.floor(this.currentSpeed * 100.0D));
/* 164 */     System.out.println(" Turn: " + Math.floor(this.angularError * this.angularErrorScaleFactor * 100.0D));
/*     */     
/* 166 */     if (currentRange < 15.0D) {
/* 167 */       Robot.oi.Drive().arcadeDrive(0.0D, 0.0D);
/* 168 */       System.out.println("End vision guided command");
/* 169 */       return true;
/*     */     }
/*     */     
/* 172 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void end()
/*     */   {
/* 178 */     Robot.oi.Drive().arcadeDrive(0.0D, 0.0D);
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
/* 189 */     return this.isThisCommandFinished;
/*     */   }
/*     */ }

