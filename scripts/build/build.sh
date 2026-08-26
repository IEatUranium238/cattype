#!/usr/bin/env bash
set -e

rm -rf "build"
mkdir -p "build/classes"

find "src" -name "*.java" -print0 |
    xargs -0 javac -d "build/classes"

jar cfe "cattype.jar" "Main" -C "build/classes" .

wait

rm -rf build

echo "Built!"
echo "Run with: java -jar cattype.jar"