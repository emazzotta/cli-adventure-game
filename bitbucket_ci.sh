#!/bin/bash

curl -i \
--user it15_ta_win:yVOnYOVO0YMB8gkeTsnMxIxPiDMz3UfB \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-X POST -d '{"key": "'$JOB_ID'", "state": "'"$1"'", "name": "Shippable Unit Tests", "url": "'$BUILD_URL'", "description": "Build: '$1'"}' \
https://api.bitbucket.org/2.0/repositories/it15_ta_win/point-and-click-adventure/commit/$COMMIT/statuses/build