#!/usr/bin/env bash

CLASS_DIR=classes

echo "Cleaning..."

# Remove java class binaries
rm -rf $CLASS_DIR/suschord
rm *.jar

echo "Done."
