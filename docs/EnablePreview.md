Enable Preview for Java JDK
============================

This document describes how to enable 
preview mode in the Java JDK. 

Preview mode enables the user 
to try out features in the jdk that 
are not yet fully released. 



## IntelliJ changes

### Settings

In the settings dialog got 
to Build section then Java Compiler. 

```declarative
Settings    
    -> Build, Execution, Deployment
    -> -> Compiler
    -> -> -> Java Compiler
```

Under 
"Additional command line parameters", 
add:

```declarative
--enable-preview --source 23
```

### Project Profile

In the Project Profile, 

1. Ensure the correct Java SDK is selected
2. Language Level: contains the word "preview"

## Gradle Changes

To fix Gradle to compile in preview mode, 

Add the following: 

```groovy
test {
    useJUnitPlatform()
    jvmArgs(['--enable-preview'])
}
tasks.withType(JavaCompile).configureEach {
    options.compilerArgs += "--enable-preview"
}

tasks.withType(Test).configureEach {
    jvmArgs += "--enable-preview"
}

tasks.withType(JavaExec).configureEach {
    jvmArgs += '--enable-preview'
}

```