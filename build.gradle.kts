plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.disi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// https://mvnrepository.com/artifact/com.sun.mail/javax.mail
	implementation("com.sun.mail:javax.mail:1.6.2")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//spring-security
	implementation("org.springframework.boot:spring-boot-starter-security")
	//lombok
	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
	testCompileOnly("org.projectlombok:lombok:1.18.26")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
