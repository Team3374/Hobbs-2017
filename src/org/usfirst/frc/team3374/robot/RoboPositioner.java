/*    */ package org.usfirst.frc.team3374.robot;
/*    */ 
/*    */ import edu.wpi.first.wpilibj.BuiltInAccelerometer;
/*    */ import edu.wpi.first.wpilibj.PIDSource;
/*    */ import edu.wpi.first.wpilibj.PIDSourceType;
/*    */ 
/*    */ public class RoboPositioner extends BuiltInAccelerometer implements PIDSource
/*    */ {
/*  9 */   PIDSourceType p = null;
/* 10 */   double accum = 0.0D;
/*    */   
/*    */   public RoboPositioner() {
/* 13 */     this.accum = 0.0D;
/*    */   }
/*    */   
/*    */   public void setPIDSourceType(PIDSourceType pidSource)
/*    */   {
/* 18 */     this.p = pidSource;
/*    */   }
/*    */   
/*    */ 
/*    */   public PIDSourceType getPIDSourceType()
/*    */   {
/* 24 */     return this.p;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double pidGet()
/*    */   {
/* 31 */     return this.accum;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 35 */     this.accum = 0.0D;
/*    */   }
/*    */ }

