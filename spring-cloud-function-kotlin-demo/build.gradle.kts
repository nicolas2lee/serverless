import com.github.jengelman.gradle.plugins.shadow.transformers.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	//id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
//for fat/uber jar
	id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "tao.berich"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-function-dependencies:3.1.4")
	}
}

dependencies {
	implementation("org.springframework.cloud:spring-cloud-function-adapter-aws")
	implementation("org.springframework.cloud:spring-cloud-starter-function-webflux")
	//implementation("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("com.amazonaws:aws-lambda-java-events:2.0.2")
	compileOnly("com.amazonaws:aws-lambda-java-core:1.1.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//test
	//testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}




tasks{
	test{
		useJUnitPlatform()
	}

	shadowJar {
		manifest {
			attributes(Pair("Main-Class", "tao.berich.fund.FundApplication"))
		}
		// Required for Spring
		mergeServiceFiles()
		append("META-INF/spring.handlers")
		append("META-INF/spring.schemas")
		append("META-INF/spring.tooling")
		val t = PropertiesFileTransformer()
		t.paths = listOf("META-INF/spring.factories")
		t.mergeStrategy = "append"
		transform(t)

//		transform(PropertiesFileTransformer) {
//			paths = ['META-INF/spring.factories']
//			mergeStrategy = "append"
//		}
	}
}