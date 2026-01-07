#!/bin/bash
cd "$(dirname "$0")"

# Path to your selenium-server jar
SELENIUM_JAR="/Users/raymondrohring/Downloads/selenium-server-4.39.0.jar"

# Start the server in the background
nohup java -jar "$SELENIUM_JAR" standalone > selenium.log 2>&1 &

echo "Selenium Server started."
