/*     */ package org.usfirst.frc.team3374.robot;

/*     */
/*     */ import edu.wpi.first.wpilibj.vision.VisionPipeline;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opencv.core.Core;
/*     */ import org.opencv.core.CvType;
/*     */ import org.opencv.core.Mat;
/*     */ import org.opencv.core.MatOfInt;
/*     */ import org.opencv.core.MatOfPoint;
/*     */ import org.opencv.core.MatOfPoint2f;
/*     */ import org.opencv.core.Rect;
/*     */ import org.opencv.core.Scalar;
/*     */ import org.opencv.core.Size;
/*     */ import org.opencv.imgproc.Imgproc;

/*     */
/*     */ public class ContoursReport
/*     */   implements VisionPipeline
/*     */ {
/*  20 */   private Mat hslThresholdOutput = new Mat();
/*  21 */   private ArrayList<MatOfPoint> findContoursOutput = new ArrayList();
/*  22 */   private ArrayList<MatOfPoint> filterContoursOutput = new ArrayList();
/*     */   
/*     */   static {
/*  25 */     System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void process1(Mat source0)
/*     */   {
/*  37 */     Mat hslThresholdInput = source0;
/*  38 */     double[] hslThresholdHue = { 71.2230215827338D, 92.45733788395904D };
/*  39 */     double[] hslThresholdSaturation = { 142.17625899280574D, 255.0D };
/*  40 */     double[] hslThresholdLuminance = { 126.12410071942446D, 187.55119453924914D };
/*  41 */     hslThreshold(hslThresholdInput, hslThresholdHue, hslThresholdSaturation, hslThresholdLuminance, this.hslThresholdOutput);
/*     */     
/*     */ 
/*  44 */     Mat findContoursInput = this.hslThresholdOutput;
/*  45 */     boolean findContoursExternalOnly = false;
/*  46 */     findContours(findContoursInput, findContoursExternalOnly, this.findContoursOutput);
/*     */     
/*     */ 
/*  49 */     ArrayList<MatOfPoint> filterContoursContours = this.findContoursOutput;
/*  50 */     double filterContoursMinArea = 12.0D;
/*  51 */     double filterContoursMinPerimeter = 0.0D;
/*  52 */     double filterContoursMinWidth = 0.0D;
/*  53 */     double filterContoursMaxWidth = 1000.0D;
/*  54 */     double filterContoursMinHeight = 0.0D;
/*  55 */     double filterContoursMaxHeight = 1000.0D;
/*  56 */     double[] filterContoursSolidity = { 0.0D, 100.0D };
/*  57 */     double filterContoursMaxVertices = 1000000.0D;
/*  58 */     double filterContoursMinVertices = 0.0D;
/*  59 */     double filterContoursMinRatio = 0.0D;
/*  60 */     double filterContoursMaxRatio = 1000.0D;
/*  61 */     filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, this.filterContoursOutput);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Mat hslThresholdOutput()
/*     */   {
/*  70 */     return this.hslThresholdOutput;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<MatOfPoint> findContoursOutput()
/*     */   {
/*  78 */     return this.findContoursOutput;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<MatOfPoint> filterContoursOutput()
/*     */   {
/*  86 */     return this.filterContoursOutput;
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
/*     */   private void hslThreshold(Mat input, double[] hue, double[] sat, double[] lum, Mat out)
/*     */   {
/* 101 */     Imgproc.cvtColor(input, out, 52);
/* 102 */     Core.inRange(out, new Scalar(hue[0], lum[0], sat[0]), new Scalar(hue[1], lum[1], sat[1]), out);
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
/*     */   private void findContours(Mat input, boolean externalOnly, List<MatOfPoint> contours)
/*     */   {
/* 115 */     Mat hierarchy = new Mat();
/* 116 */     contours.clear();
///*     */     int mode;
/* 118 */     int mode; if (externalOnly) {
/* 119 */       mode = 0;
/*     */     }
/*     */     else {
/* 122 */       mode = 1;
/*     */     }
/* 124 */     int method = 2;
/* 125 */     Imgproc.findContours(input, contours, hierarchy, mode, method);
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
/*     */   private void filterContours(List<MatOfPoint> inputContours, double minArea, double minPerimeter, double minWidth, double maxWidth, double minHeight, double maxHeight, double[] solidity, double maxVertexCount, double minVertexCount, double minRatio, double maxRatio, List<MatOfPoint> output)
/*     */   {
/* 149 */     MatOfInt hull = new MatOfInt();
/* 150 */     output.clear();
/*     */     
/* 152 */     for (int i = 0; i < inputContours.size(); i++) {
/* 153 */       MatOfPoint contour = (MatOfPoint)inputContours.get(i);
/* 154 */       Rect bb = Imgproc.boundingRect(contour);
/* 155 */       if ((bb.width >= minWidth) && (bb.width <= maxWidth) && 
/* 156 */         (bb.height >= minHeight) && (bb.height <= maxHeight)) {
/* 157 */         double area = Imgproc.contourArea(contour);
/* 158 */         if ((area >= minArea) && 
/* 159 */           (Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) >= minPerimeter)) {
/* 160 */           Imgproc.convexHull(contour, hull);
/* 161 */           MatOfPoint mopHull = new MatOfPoint();
/* 162 */           mopHull.create((int)hull.size().height, 1, CvType.CV_32SC2);
/* 163 */           for (int j = 0; j < hull.size().height; j++) {
/* 164 */             int index = (int)hull.get(j, 0)[0];
/* 165 */             double[] point = { contour.get(index, 0)[0], contour.get(index, 0)[1] };
/* 166 */             mopHull.put(j, 0, point);
/*     */           }
/* 168 */           double solid = 100.0D * area / Imgproc.contourArea(mopHull);
/* 169 */           if ((solid >= solidity[0]) && (solid <= solidity[1]) && 
/* 170 */             (contour.rows() >= minVertexCount) && (contour.rows() <= maxVertexCount)) {
/* 171 */             double ratio = bb.width / bb.height;
/* 172 */             if ((ratio >= minRatio) && (ratio <= maxRatio)) {
/* 173 */               output.add(contour);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void process(Mat source) {}
/*     */ }

