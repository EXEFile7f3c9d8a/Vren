import {run} from "../../../../../res/scripts/deploy.js";
import {gradleS, mavenS, mavenF, gradleF} from "../../../../../res/scripts/gradle-maven.js";

run(document, document.getElementById("deploy"), [
    [
        "Gradle",
        gradleF(),
        gradleS("map-v1.0.0.003")
    ],
    [
        "Maven",
        mavenF(),
        mavenS("map-v1.0.0.003")
    ]
], "v1.0.0.003");
