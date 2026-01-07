#!/bin/bash
cd "$(dirname "$0")"

# Kill any running selenium-server Java process
pkill -f selenium-server

echo "Selenium Server stopped."
