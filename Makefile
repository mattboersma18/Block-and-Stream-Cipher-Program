JAVAC = javac
JAVA = java
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)
SUBMIT_NAME = Matthew-Boersma-PA1.tar.gz

default: all

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

run: all
	$(JAVA) cipher

clean:
	rm -f *.class $(SUBMIT_NAME)

.PHONY: clean package

package: 
	tar -czvf $(SUBMIT_NAME) *.java Makefile