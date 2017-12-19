.PHONY: all
all: clean build start

.PHONY: clean
clean:
	@mvn clean

.PHONY: build
build:
	@mvn install

.PHONY: start
start:
	@java -jar ./target/point_and_click_adventure-1.0.jar
