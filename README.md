# ClassParser: A JVM ClassFile parser ☕

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Java Version](https://img.shields.io/badge/Java%20Version-JDK%2022%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
![Built with Love](https://img.shields.io/badge/Built%20with%20♥️-fdb0c0)

## Overview

ClassParser is a general purpose classfile parser for .class files.<br>
It's built on JDK 22 and later, and was created with the goal of testing [JEP 447](https://openjdk.org/jeps/447)<br>
It grants you access to everything contained in the classfile, **including the bytecode**.<br>
If you only need the ClassModel, and not the bytecode, the  [ClassModel API](https://docs.oracle.com/en%2Fjava%2Fjavase%2F22%2Fdocs%2Fapi%2F%2F/java.base/java/lang/classfile/ClassModel.html) is a better fit for you.

## Features

- **Simple and straightfoward API:** Feed the class the path to the parser. Get what you need. It's that simple.
- **Obtain the bytecode:** Unlike some others classfile parsers, you can obtain the byte[]s directly from the API.
- **Unopinionated:** Want the bytecode? Here's the byte[]s.


### Give-me-the-bytes Example

```java
Path path = Path.of("path/to/file"); // Get the path 📁
ClassModelParser parser = new ClassModelParser(path); // Create the parser 🔍
byte[] mainMethodBytes = parser.findMainMethodBytecode; // Get the bytecode 👾!

```

### Give-me-the-major-version Example
```java
Path path = Path.of("path/to/file"); // Get the path 📁
ClassModelParser parser = new ClassModelParser(path); // Create the parser 🔍
int majorVersion = parser.getMajorVersion(); // Get the major version ☕
```
### Give-me-'foo'-method-bytecode Example
```java
Path path = Path.of("path/to/file"); // Get the path 📁
ClassModelParser parser = new ClassModelParser(path); // Create the parser 🔍
byte[] fooBytes = parser.findMethodBytecode("foo"); // Get the bytecode 👾!
```

