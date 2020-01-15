# 说明文档

>Processing学习路线  

## 第三章

### 波  

```processing
float startAngle = 0.0;
float vAngle = 0.2;
void setup() {
    size(512, 512);
    background(255);
}
void draw() {
    background(255);
    float angle = startAngle;
    for (int x = 0; x <= width; x += 5) {
        float y = map(100 * sin(angle), -100, 100, height / 2 - 100, height / 2 + 100);
        ellipse(x, y, 5, 5);
        angle += vAngle;
    }
    startAngle += 0.2;
}
```

### 粒子系统

```processing
class Particle {
  PVector location;
  PVector velocity;
  PVector acceleration;
  float lifespan;
  float mass;
  Particle() {
    location = new PVector(0, 0);
    velocity = new PVector();
    acceleration = new PVector(random(0.1), random(0.1));
    lifespan = 255.0;
    mass = map(noise(random(100, 1500), random(100, 1500)), -1, 1, 1, 30);
  }
  void update() {
    velocity.add(acceleration);
    location.add(velocity);
    lifespan -= 2.0;
  }
  void display() {
    stroke(0, lifespan);
    fill(175, lifespan);
    ellipse(location.x, location.y, mass * 2, mass * 2);
  }
  void run() {
    // applyForce();
    update();
    checkEdges();
    display();
  }
  void checkEdges() {
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
  void applyForce(PVector force) {
    PVector f = force.get();
    f.div(mass);
    acceleration.add(f);
  }
  boolean isDead() {
    if (lifespan < 0) {
      return true;
    } else {
      return false;
    }
  }
}

int total = 10;
ArrayList < Particle > pList;
void setup() {
  size(512, 512);
  pList = new ArrayList < Particle > ();
}
void draw() {
  background(255);
  pList.add(new Particle());
  for (int i = pList.size() - 1; i >= 0; i--) {
    Particle p = pList.get(i);
    p.run();
    if (p.isDead()) {
      pList.remove(i);
    }
  }
}
```

### 震荡

```processing
class Oscillator{
  PVector angle;
  PVector velocity;
  PVector amplitude;
  Oscillator(){
    angle=new PVector();
    velocity=new PVector(random(-0.05, 0.05),random(-0.05, 0.05));
    amplitude=new PVector(random(width/2),random(height/2));
  }
  void Oscillate(){
    angle.add(velocity);
  }
  void display(){
    float x=sin(angle.x)*amplitude.x;
    float y=sin(angle.y)*amplitude.y;
    pushMatrix();
    translate(width/2,height/2);
    stroke(0);
    fill(175);
    line(0, 0, x, y);
    ellipse(x, y, 16, 16);
    popMatrix();
  }
}
Oscillator m;
void setup() {
  size(512, 512);
  background(255, 255, 255, 100);
  m=new Oscillator();
}
void draw() {
  background(255, 255, 255, 100);
  m.Oscillate();
  m.display();
}
```

### 钟摆

```processing
class Pendulum {
    float r;
    float angle;
    float aVeloctiy;
    float aAcceleration;
    float damping;
    PVector location;
    PVector origin;
    Pendulum(PVector origin_, float r_) {
        origin = origin_.get();
        location = new PVector();
        r = r_;
        angle = PI / 4;
        aVeloctiy = 0.0;
        damping = 0.995;
    }
    void go() {
        update();
        display();
    }
    void update() {
        float gravity = 0.4;
        aAcceleration = (-1 * gravity / r) * sin(angle);
        aVeloctiy += aAcceleration;
        angle += aVeloctiy;
        aVeloctiy *= damping;
    }
    void display() {
        location.set(r * sin(angle), r * cos(angle));
        location.add(origin);
        stroke(0);
        line(origin.x, origin.y, location.x, location.y);
        fill(175);
        ellipse(location.x, location.y, 16, 16);
    }
}
Pendulum p;
void setup() {
    size(512, 512);
    p = new Pendulum(new PVector(width / 2, 10), 125);
}
void draw() {
    background(255);
    p.go();
}
```
