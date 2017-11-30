 package org.usfirst.frc.team3374.robot;
 
 import edu.wpi.first.wpilibj.VictorSP;
 
 public class MultiSpeedController implements edu.wpi.first.wpilibj.SpeedController
 {
   private VictorSP[] speedControllers;
   private double speed;
   private boolean inverted;
   
   public MultiSpeedController(VictorSP... speedControllers) {
/* 12 */     this.speedControllers = speedControllers;
/* 13 */     set(0.0D);
   }
   
   public double get()
   {
/* 18 */     return this.speed;
   }
   
   public void set(double speed)
   {
/* 23 */     this.speed = speed;
     
/* 25 */     for (VictorSP speedController : this.speedControllers) {
/* 26 */       speedController.set(speed);
     }
   }
   
 
   public void pidWrite(double output)
   {
/* 33 */     set(output);
   }
   
   public void disable()
   {
/* 38 */     for (edu.wpi.first.wpilibj.SpeedController speedController : this.speedControllers) {
/* 39 */       speedController.disable();
     }
   }
   
   public void setInverted(boolean isInverted)
   {
/* 45 */     this.inverted = (!this.inverted);
   }
   
 
 
   public boolean getInverted()
   {
/* 52 */     return this.inverted;
   }
   
 
   public void stopMotor()
   {
/* 58 */     set(0.0D);
   }
 }

