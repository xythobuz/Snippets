#include <GLUT/glut.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

clock_t timestamp(void);
void calculate(void);
void fillBuffer(unsigned char r, unsigned char g, unsigned char b);
void display(void);
void keyDown(unsigned char key, int x, int y);
void reshape(int width, int height);
void idle(void);

#define MULTIPLIER 2
#define VirtualToRealStart(x) (x * MULTIPLIER)
#define VirtualToRealEnd(x) ((x * MULTIPLIER) + (MULTIPLIER - 1))

#define R 0
#define G 1
#define B 2
#define WIDTH 300
#define HEIGHT 300

unsigned char buffer[WIDTH][HEIGHT][3];

// calc intervall in msecs
#define INTERVALL 5000

clock_t lastTime;

clock_t timestamp(void) {
	return clock() / (CLOCKS_PER_SEC / 1000);
}

void calculate(void) {
	printf("Calc...\n");
}

void fillBuffer(unsigned char r, unsigned char g, unsigned char b) {
	int x, y;
	for (x = 0; x < WIDTH; x++) {
		for (y = 0; y < HEIGHT; y++) {
			buffer[x][y][R] = r;
			buffer[x][y][G] = g;
			buffer[x][y][B] = b;
		}
	}
}

// Draw into window
void display(void) {
	int x, y;
	printf("Display...\n");
	glClearColor(255, 255, 255, 0);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	for (x = 0; x < WIDTH; x++) {
		for (y = 0; y < HEIGHT; y++) {
			glColor3d(buffer[x][y][R], buffer[x][y][G], buffer[x][y][B]);
			glRecti(VirtualToRealStart(x), VirtualToRealStart(y), VirtualToRealEnd(x), VirtualToRealEnd(y));
		}
	}

	lastTime = timestamp();
}

void keyDown(unsigned char key, int x, int y) {
	
}

// Window was resized
void reshape(int width, int height) {
	glViewport(0, 0, width, height);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	//set the coordinate system, with the origin in the top left
	gluOrtho2D(0, width, height, 0);
	glMatrixMode(GL_MODELVIEW);
}

// Calculate
void idle(void) {
	// glutPostRedisplay();
	if (timestamp() > (lastTime + INTERVALL)) {
		calculate();
		glutPostRedisplay();
	}
}

int main(int argc, char *argv) {
	fillBuffer(255, 0, 0);

	// Initialize GLUT
	glutInitWindowSize(WIDTH * MULTIPLIER, HEIGHT * MULTIPLIER);
	glutInitDisplayMode(GLUT_RGB | GLUT_SINGLE | GLUT_DEPTH); // RGB, Single Buffer, Depth Buffer
	glutInit(&argc, &argv);

	// Create window
	glutCreateWindow("OpenGL Test");

	// Register Callbacks
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutIdleFunc(idle);
	glutKeyboardFunc(keyDown);

	// Enter Main Loop
	glutMainLoop();

	return 0;
}