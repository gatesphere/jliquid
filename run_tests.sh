#!/usr/bin/env bash

CP=classes/.:.

echo "Running..."
if [[ $OSTYPE == linux-gnu ]] || [[ ${OSTYPE//[0-9.]/} == darwin ]]; then
  java -cp $CP suschord.jliquid.test.Fib
elif [[ $OSTYPE == cygwin ]]; then
  java -cp `cygpath -wp $CP` suschord.jliquid.test.Fib
else 
  echo "Unknown platform."
fi
echo "Done."
