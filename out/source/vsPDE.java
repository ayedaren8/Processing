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

public class vsPDE extends PApplet {

class Particle {
  PVector location;
  PVector velocity;
  PVector acceleration;
  float lifespan;
  float mass;
  Particle() {
    location = new PVector(0, 0);
    velocity = new PVector();
    acceleration = new PVector(random(0.1f), random(0.1f));
    lifespan = 255.0f;
    mass = map(noise(random(100, 1500), random(100, 1500)), -1, 1, 1, 30);
  }
  public void update() {
    velocity.add(acceleration);
    location.add(velocity);
    lifespan -= 2.0f;
  }
  public void display() {
    stroke(0, lifespan);
    fill(175, lifespan);
    ellipse(location.x, location.y, mass*2, mass*2);
  }
  public void run() {
    // applyForce();
    update();
    checkEdges();
    display();
  }
  public void checkEdges() {
    if (location.x > width) {
      location.x = 0;
    } else if (location.x < 0) {
      location.x = width;
    }
    if (location.y > height) {
      location.y = 0;
    } else if (location.y < 0) {
      location.y = height;
    }
  }
  public void applyForce(PVector force) {
    PVector f = force.get();
    f.div(mass);
    acceleration.add(f);
  }
  public boolean isDead() {
    if (lifespan < 0) {
      return true;
    } else {
      return false;
    }
  }
}
int total = 10;
// Particle[] pList=new Particle[total];  
ArrayList < Particle > pList;
public void setup() {
  
  pList = new ArrayList < Particle > ();
}
public void draw() {
  background(255);
  pList.add(new Particle());
  for (int i=pList.size()-1;i>=0;i--) {
    Particle p=pList.get(i);
    p.run();
    if (p.isDead()) {
        pList.remove(i);
    }
  }
}
  public void settings() {  size(512, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "vsPDE" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
