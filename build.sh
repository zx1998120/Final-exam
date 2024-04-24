#!/bin/bash

# Stop on first error
set -e

# Optional: Clear any previous build outputs
echo "Cleaning project..."
mvn clean

# Compile the project
echo "Compiling the project..."
mvn compile

# Run static code analysis tools
echo "Running Checkstyle..."
mvn checkstyle:check

#echo "Running SpotBugs..."
#mvn spotbugs:check

# Generate documentation
echo "Generating Javadoc..."
mvn javadoc:javadoc

# Run tests
echo "Running unit tests..."
mvn test

# Build the package
echo "Building package..."
mvn package

echo "Build completed successfully."
