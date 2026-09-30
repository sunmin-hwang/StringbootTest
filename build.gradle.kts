// Kotlin DSL로 작성한 동일한 빌드 설정이다.
// 기본 settings.gradle은 실제 빌드 파일로 build.gradle을 선택한다.
// 이 파일은 학습 및 PPT 캡처용이며 settings.kotlin-dsl.gradle.kts로 별도 검증할 수 있다.

plugins {
    java
    war
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.web"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Web: Controller와 REST API
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Data JPA: Entity와 Repository
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Spring Security: 로그인과 접근 권한
    implementation("org.springframework.boot:spring-boot-starter-security")

    // OpenAPI와 Swagger UI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
