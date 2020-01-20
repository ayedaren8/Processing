import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class main extends PApplet {

PVector pointA;
PVector pointB;
PVector pointC;
PVector pointD;
PVector pointE;
float theta;
float r;
public void setup() {
  
  background(255);
  r=250;
  float x_A=0.618f*r*cos(radians(-35));
  float y_A=0.414f*r*sin(radians(-35));
  float x_B=r*cos(radians(165));
  float y_B=r*sin(radians(165));
  float x_C=r*cos(radians(75));
  float y_C=r*sin(radians(75));
  pointA =new PVector(x_A,y_A);
  pointB =new PVector(x_B,y_B);
  pointC =new PVector(x_C,y_C);
  println(x_A,y_A);
 
}

public void mouseMoved() {
    PVector mouse = new PVector(mouseX,mouseY);
    PVector orign = new PVector(width/2,height/2);
    mouse=PVector.sub(mouse,orign);
    mouse.mag();
  if (mouse.mag()<r) {
    println(mouse.mag());
    this.pointA.x=mouse.x;
    this.pointA.y=mouse.y;
  }else{
    this.pointA.x=125;
    this.pointA.y=-60;
  }

}

public void draw() {
  translate(width/2, height/2);
  fill(0, 0, 255, 125);
  ellipse(0, 0, 2*r, 2*r);
  strokeWeight(5);
  stroke(255, 255, 255, 255); 
  PVector dirA_B=PVector.sub(pointB,pointA);
  float lenthA_B=dirA_B.mag();
  PVector dirA_C=PVector.sub(pointC,pointA);
  float lenthA_C=dirA_C.mag();
  float x_D=0.618f*lenthA_B*cos(dirA_B.heading2D());
  float y_D=0.618f*lenthA_B*sin(dirA_B.heading2D());
  float x_E=0.618f*lenthA_C*cos(dirA_C.heading2D());
  float y_E=0.618f*lenthA_C*sin(dirA_C.heading2D());
  pointD =new PVector(x_D,y_D);
  pointE =new PVector(x_E,y_E);
  line(pointA.x,pointA.y,pointB.x, pointB.y);
  line(pointA.x,pointA.y,pointC.x, pointC.y);
  line(pointA.x+pointD.x, pointA.y+pointD.y, pointA.x+pointE.x,pointA.y+pointE.y);  
}

  public void settings() {  size(960,960); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
