# CATTYPE
> **C**ATTYPE - **A**bsolutely **T**errible **T**o **Y**our **P**rogramming **E**xperience

CATTYPE is an esoteric programming language inspired by old programming languages such as COBOL, FORTRAN, and BASIC. It takes the worst parts of all three and somehow makes them even worse.

CATTYPE was designed to look like a perfectly reasonable programming language from a distance. But the more you go into this rabbit hole the more Java looks like lightweight and simple option.

It features:
- Global values ONLY
- Enterprise Java level verbosity
- `INT` and `STR` data types ONLY
- `GOTO` with additional `ON` as only way to do control flow
- Build-in functions that all end with `!` because yes
- Manual memory managment with `DEL` becuase I was too lazy to implament GC in this, yes intrepeter is written in Java which has GC but who cares
- Enterprise scale? Maybe idk.

# Getting started

## Download

### 1. Native image binary (Windows & Linux)

Download cattype executable for your os.

Run (example): `./cattype (your cattype file path)`

> NOTE: cattype command may vary based on executable downloaded

Or put cattype executable somewhere on PATH to use it anywhere

### 2. JAR (macOS other platforms with JVM)

Download JRE (Java 21+) and .jar from releases.
Run with `java -jar cattype.jar (your cattype file path)`

### 3. Compile it yourself

#### .jar file

- Requirements: Java 21+, bash or powershell

Compile:

- Bash:
  `bash scripts/build/build.sh`
- Powershell:
  `powershell scripts/build/build.ps1`

Run: `java -jar cattype.jar (your cattype file path)`

#### Native image

- Requirement: Java 21+ (Graalvm), bash or powershell

Compile:

- Bash:
  `bash scripts/native/native.sh`
- Powershell:
  `powershell scripts/native/native.ps1`

Run: `./cattype cattype.jar (your cattype file path)`

Or put cattype executable somewhere on PATH to use it anywhere

## Make your first program
Make a new file with .cat extension.

```
PRINT "Hello, world!";
```

Run it:

```bash
(your cattype usage option) ./yourfile.cat
```

Expected output:

```
Hello, world!
```

Congratulations, this is the best it gets.
# Explore syntax
Want to learn CATTYPE for some reason?
Explore language syntax rules, commands and build in functions in [Docs.md](./Docs.md) file.

# Contributions

If you for some reason want to contribute to this:
1. Reconsider.
2. Prepare to read worst Java code you ever seen if you skiped step 1. I write no comments and don't enforce any code quality standars. If it runs it runs.

Anyways PRs welcome.

# License
Public domain (under The Unlicense).

Do whatever you want.

- Sell it.
- Make your own version.
- Make incompatible forks. (Looking at you Visual J++!)
- Try to make POINTs act as functions
- Use it in production. (PLEASE DONT)
- Put "CATTYPE Certified Professional" on LinkedIn.
- Have an investor give you 4 million $ to build CATTYPE Enterprise Edition.
- Fork a rust project and rewrite it in CATTYPE.
- Get sued.
- Have fun.
