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