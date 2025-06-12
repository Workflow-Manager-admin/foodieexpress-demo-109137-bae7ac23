#!/bin/bash
cd /home/kavia/workspace/code-generation/foodieexpress-demo-109137-bae7ac23/foodieexpress_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

