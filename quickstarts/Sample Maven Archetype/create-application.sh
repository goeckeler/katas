#!/bin/sh
mvn archetype:generate -DarchetypeGroupId=kata -DarchetypeArtifactId=kata -DarchetypeVersion=1.0 \
                       -DgroupId=com.goeckeler.katas -DartifactId=demo -Dversion=1.0
