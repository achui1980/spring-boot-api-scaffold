#!/bin/bash



function staticAnalysis(){
    echo "[INFO] Scanning AND Analyzing Static Code"
    mvn clean site
}

function runUnitAndIntTests(){
    echo "[INFO] Running Unit AND Integration Tests"
    mvn clean package
}


function runAccTests() {
    echo "[INFO] Running Acceptance Tests"
    echo `pwd`
    mvn clean integration-test
}
