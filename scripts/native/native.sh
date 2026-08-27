#!/usr/bin/env bash

set -e

rm -rf "build" "cattype"

mkdir -p "build/classes"

find "src" -name "*.java" -print0 |
    xargs -0 javac -d "build/classes"

native-image \
    --no-fallback \
    -O3 \
    --gc=serial \
    -march=native\
    -H:+UnlockExperimentalVMOptions \
    -H:+RemoveSaturatedTypeFlows \
    -H:+ReportExceptionStackTraces \
    -cp "build/classes" \
    "Main" \
    "cattype"

rm -rf "build"

echo "Built!"
echo "Run with: ./cattype"