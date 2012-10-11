LIB = /System/Library/Frameworks/GLUT.framework/GLUT
LIB += /System/Library/Frameworks/OpenGL.framework/OpenGL
SRC = main.c
TARGET = openGLTest
CC = gcc
RM = rm -rf

all: $(TARGET)

run: $(TARGET)
	$(TARGET)

$(TARGET): $(SRC)
	 gcc $(LIB) $(SRC) -o $(TARGET)

clean:
	$(RM) $(TARGET)