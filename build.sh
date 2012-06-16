#!/usr/bin/env bash

CP=.
SRC_DIR=src/suschord/jliquid/
TEST_DIR=$SRC_DIR/test/
CLASS_DIR=classes/

echo "Building..."
if [ ! -d $CLASS_DIR ]; then
  mkdir $CLASS_DIR
fi

if [[ $OSTYPE == linux-gnu ]] || [[ ${OSTYPE//[0-9.]/} == darwin ]]; then
  javac -cp $CP -sourcepath $SRC_DIR -d $CLASS_DIR $1 $SRC_DIR/*.java && echo "Done building lib."
  javac -cp $CP -sourcepath $TEST_DIR -d $CLASS_DIR $1 $TEST_DIR/*.java && echo "Done building tests."
elif [[ $OSTYPE == cygwin ]]; then
  javac -cp `cygpath -wp $CP` -sourcepath $SRC_DIR -d $CLASS_DIR $1 $SRC_DIR/*.java && echo "Done building lib."
  javac -cp `cygpath -wp $CP` -sourcepath $TEST_DIR -d $CLASS_DIR $1 $TEST_DIR/*.java && echo "Done building tests."
else
  echo "Unknown platform."
fi
