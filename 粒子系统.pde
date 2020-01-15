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