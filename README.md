# Yet Another Simple Programming Language

This is an implementation of a programming language, to generate the lexical analyzer has been used [JFlex](https://jflex.de/), to generate the parser has been used [JavaCup](http://www2.cs.tum.edu/projects/cup/), the semantic analysis has been managed using the [Visitor](https://en.wikipedia.org/wiki/Visitor_pattern) pattern after this last analysis is generated the C code(always using the visitor pattern). 

## How to install
1. You can download the YASPL compiler [here](https://github.com/CiccioTecchio/YASPL3/releases/tag/2.0).
3. For more information about the lexical, grammatical and semantic rules specification read the [wiki](https://github.com/CiccioTecchio/YASPL3/wiki/Yet-Another-Simple-Programming-Language).
2. You can clone this project you can import it in IntelliJ an run the Maven task **jflex** to generate the lexical analyzer and run the Maven task **jcup** to generate the parser.
