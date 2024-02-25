plugins {
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

configurations {
    create("invoker")
}

val invoker by configurations

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation ("com.google.cloud.functions:functions-framework-api:1.0.4")
    invoker ("com.google.cloud.functions.invoker:java-function-invoker:1.3.1")
}


tasks.register<JavaExec>("runFunction") {
    mainClass= "com.google.cloud.functions.invoker.runner.Invoker"
    classpath(configurations.getByName("invoker"))
    inputs.files(configurations.runtimeClasspath, sourceSets.main.get().output)
    args(
        "--target", project.findProperty("run.functionTarget") ?: "",
        "--port", project.findProperty("run.port") ?: 8080
    )
    doFirst {
        args("--classpath", files(configurations.runtimeClasspath, sourceSets.main.get().output).asPath)
    }
}

