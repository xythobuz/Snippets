#include <stdio.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xatom.h>
#include <X11/keysym.h>

#define WIDTH 500
#define HEIGHT 500

Display *dsp;
Window win;
int screenNumber;
unsigned long white;
unsigned long black;
GC gc;
int eventLoop = 1; // Event loop expression

int init() {
	dsp = XOpenDisplay( NULL );
	if( !dsp )
		return 1;
	screenNumber = DefaultScreen(dsp);
	
	white = WhitePixel(dsp, screenNumber);
	black = BlackPixel(dsp, screenNumber);

	win = XCreateSimpleWindow(dsp, DefaultRootWindow(dsp), 10, 10, WIDTH, HEIGHT, 0, black, white);
	XMapWindow(dsp, win);
	gc = XCreateGC(dsp, win, 0, NULL);
	XSetForeground(dsp, gc, black);
	XFlush(dsp);

	return 0;
}

void close() {
	XDestroyWindow(dsp, win);
	XCloseDisplay(dsp);
}

void draw() {
	int a = rand() % 500;
	int b = rand() % 500;
	int c = rand() % 500;
	int d = rand() % 500;
	XDrawLine(dsp, win, gc, a, b, c, d); //from-to
}

void keyReact(KeySym key) {
	switch (key) {
		case XK_Escape:
		case XK_q:
		case XK_Q: 
			eventLoop = 0;
			return;
	}
}

int main(){

	XEvent evt;

	if (init() != 0) {
		printf("Error while creating Window\n");
	}

	XSelectInput( dsp, win, ExposureMask | KeyPressMask | ButtonPressMask | StructureNotifyMask );

	Atom wmDelete = XInternAtom(dsp, "WM_DELETE_WINDOW", True); // React to window managers...
	XSetWMProtocols(dsp, win, &wmDelete, 1); // ...close action

	while (eventLoop) {
		if (XPending(dsp) != 0) {
			XNextEvent(dsp, &evt);
			switch (evt.type) {
				case Expose:
					if (evt.xexpose.count == 0)
						draw();
					break;
				case ClientMessage: // User exited...
					eventLoop = 0;
					break;
				case ButtonPress:
					printf("Mouse Button: %d\n", evt.xbutton.button);
					break;
				case KeyPress:
					keyReact(XLookupKeysym(&evt.xkey, 0));
					break;
				case ConfigureNotify:
					if (evt.xconfigure.width != WIDTH || evt.xconfigure.height != HEIGHT)
						XResizeWindow(dsp, win, WIDTH, HEIGHT);
					break;
			}
		}
	}

	close();

	return 0;
}
