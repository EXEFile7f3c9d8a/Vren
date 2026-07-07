import {run} from "../../../res/scripts/deploy.js";
import {gradleS, mavenS, mavenF, gradleF} from "../../../res/scripts/gradle-maven.js";

run(document, document.getElementById("deploy"), [
    [
        "Gradle",
        gradleF(),
        gradleS("main-SNAPSHOT")
    ],
    [
        "Maven",
        mavenF(),
        mavenS("main-SNAPSHOT")
    ]
], "SNAPSHOT");
