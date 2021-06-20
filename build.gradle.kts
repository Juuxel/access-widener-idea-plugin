import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.intellij") version "1.0"
    kotlin("jvm") version "1.4.32" // Corresponds to IDEA 2021.2 EAP, see kt jars inside the ideaIC dep in IDEA
    id("org.jmailen.kotlinter") version "3.4.4"
}

// TODO: Generate the parser and lexer here.
//   Currently not possible due to https://github.com/JetBrains/gradle-grammar-kit-plugin/issues/23

group = "io.github.juuxel"
version = "1.0.0"

repositories {
    mavenCentral()
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.1")
    plugins.set(listOf("java", "Kotlin", "PsiViewer:211-SNAPSHOT"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

sourceSets {
    main {
        java.srcDir("src/main/generated")
    }
}

tasks {
    jar {
        from("COPYING", "COPYING.LESSER")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"

        if (JavaVersion.current().isJava9Compatible) {
            options.release.set(11)
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {
        sinceBuild.set("211")
        untilBuild.set("212.*")
    }
}
