#### Network automation using Kotlin programming language

This repository contains a few examples of how you can automate 
network devices using Kotlin programming language. Specifically,
in the provided examples, Junos OS-based devices and NETCONF
protocol are used.

The Q&A below contains some more information.

###### Q: What is Kotlin?
A: [Kotlin](https://kotlinlang.org/) is a modern cross-platform 
programming language. It is statically-typed and has 
expressive and concise syntax. Many developers consider Kotlin as a 
natural successor to Java (e.g., according to Google, Kotlin 
is now the [preferred language for Android](https://techcrunch.com/2019/05/07/kotlin-is-now-googles-preferred-language-for-android-app-development/) 
development). In fact, Kotlin code can be compiled into Java
bytecode and executed in JVM; furthermore, complete 
interoperability with Java code including existing libraries 
is guaranteed. Kotlin can also compile to JavaScript and native
machine code via LLVM but that is a different story.

###### Q: Shall I automate networks using Kotlin?
A: If you want to develop your automation solution using a 
feature-rich statically typed language (rather than 
dynamically-typed language like Python or Ruby), you should 
at least look at Kotlin. The final choice is yours, but
generally speaking statically-typed languages allow the
compiler to find more programmer's errors at compile-time
rather than run-time and should be preferred for larger projects.

###### Q: Are network automation libraries available for Kotlin?
A: As noted above, existing Java libraries can be used, as
demonstrated in this repository using Juniper Networks' 
[Java NETCONF library](https://github.com/juniper/netconf-java).
Other protocols such as gRPC can also be used (you are welcome
to add an example to this repository, by the way!). 

#### The following examples are currently available in src/main/kotlin/examples:
- **parse_system_info.kt** - Read basic system information from a device.
- **load_config.kt** - Load set-configuration on the device.
- **CLI_command.kt** - Execute a CLI command (with some automatic connect/close via an extension function).

Note: we are using 2.0.0 version of java-netconf released in
July 2019. It has some non-backwards-compatible 
[changes](https://github.com/Juniper/netconf-java/releases/tag/v2.0.0)
to the API compared to version 1.x. Also, the library is only 
currently available as source code, no compiled version is put in
public repository - so you will have to compile it yourself (this
is not hard).
