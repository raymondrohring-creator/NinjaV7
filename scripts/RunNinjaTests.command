#!/bin/zsh

echo "========================================"
echo " Starting NinjaV7 Maven Test Run"
echo "========================================"
echo ""

cd /Users/raymondrohring/eclipse-workspace/NinjaV7
mvn clean test

echo ""
echo "========================================"
echo " Test run complete"
echo "========================================"
echo ""
echo "Press any key to close this window..."
read -k 1
