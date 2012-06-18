#!/usr/bin/env bash

CLASS_DIR=classes

echo "Making jar..."

cd $CLASS_DIR
jar cvfm ../jliquid.jar Manifest.txt suschord/*

echo "Done."
