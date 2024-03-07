# Relative path to the JDK within the project
LOCAL_JDK=./jdk11

# Make sure mvnw is executable
MVN=./mvnw
PREPARE:
	chmod +x $(MVN)
	chmod +x $(LOCAL_JDK)/bin/*

JAVA=$(LOCAL_JDK)/bin/java
JAVAC=$(LOCAL_JDK)/bin/javac
MAIN_CLASS=org.app.cpsc4140hw1.App
TARGET_DIR=target
CLASSPATH=$(TARGET_DIR)/classes
JAVAFX_LIB=./javafx-sdk-11/lib

all: prepare compile

prepare: PREPARE

compile:
	export JAVA_HOME=$(LOCAL_JDK); \
	$(MVN) compile

package:
	export JAVA_HOME=$(LOCAL_JDK); \
	$(MVN) package

clean:
	$(MVN) clean

run:
	export JAVA_HOME=$(LOCAL_JDK); \
	$(JAVA) --module-path $(JAVAFX_LIB) --add-modules javafx.controls,javafx.fxml -cp $(CLASSPATH) $(MAIN_CLASS)

.PHONY: all prepare compile package clean run
