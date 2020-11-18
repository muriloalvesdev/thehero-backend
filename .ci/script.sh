#!/bin/bash
 export MAVEN_OPTS="-Xmx1G -Xms128m"
 export CODECOV_TOKEN="92ab5c75-9ee3-4ed2-ba5a-e3438ab9ee4a"
 mvn clean install -Pstaging
