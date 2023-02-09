plugins {
    java
}

group = "dev.denux"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    //Testing stuff
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}