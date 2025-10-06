#!/bin/sh
# Downloads the maven-wrapper.jar into .mvn/wrapper/ so ./mvnw will work.
# This script requires curl or wget and network access.
set -e
WRAPPER_DIR=".mvn/wrapper"
mkdir -p "$WRAPPER_DIR"
JAR_URL="https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
echo "Downloading maven-wrapper.jar from $JAR_URL ..."
if command -v curl >/dev/null 2>&1; then
  curl -L "$JAR_URL" -o "$WRAPPER_DIR/maven-wrapper.jar"
elif command -v wget >/dev/null 2>&1; then
  wget -O "$WRAPPER_DIR/maven-wrapper.jar" "$JAR_URL"
else
  echo "Error: curl or wget is required to download the wrapper jar."
  exit 1
fi
echo "Downloaded to $WRAPPER_DIR/maven-wrapper.jar"
