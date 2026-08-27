# CATTYPE Language Specification and Usage Guide

## Ideas and Syntax Basics

CATTYPE is an esoteric programming language inspired by old programming languages such as COBOL, FORTRAN, and BASIC. It takes the worst parts of all three and somehow makes them even worse.

CATTYPE does not require semicolons (`;`) at the end of lines, although using them is not prohibited.

### Data Types

CATTYPE currently supports the following data types:

- **INT** - A 32-bit signed integer.
- **STR** - A UTF-8 string.

> **NOTE:** Functions that are expected to produce a Boolean result use an `INT` value of `0` or `1` instead.

> **NOTE:** Although `POINT`s are defined using a separate command, they are normal `INT` values and have the same functionality as integers defined using `DEF`.

### Primary Elements

The primary elements of CATTYPE are:

- **Commands** (e.g. `PRINT`) - Fully uppercase and must appear at the beginning of a line.
- **Built-in functions** (e.g. `TOSTR!()`) - Fully uppercase, end with `!`, and accept one or two arguments separated by spaces.
- **Values** - User values. They must be fully lowercase, must not start with a number, and must not end with `!`.

### Comments

Comments in CATTYPE can be written in two ways.

#### 1. Line comments

A line beginning with `#` is treated as a comment.

- **Example:**

  ```
  # This is a comment
  ```

> **NOTE:** CATTYPE does not support multiline comments.

#### 2. Trailing comments

A comment can be placed after the expected core of a command. Everything following the command's expected arguments is ignored.

- **Example:**

  ```
  PRINT "Hello world!" This will be ignored
  ```

## Usage notation

The following notation is used throughout this guide:

`[TYPE] EXPECTED`

- **TYPE** - The source of the value:
  - `IMD` - Value suplied directly from code.
  - `FUN` - Function return value.

- **EXPECTED** - The expected data type.

Alongside with:

- `name` - User value name
- `dt` - Data type (`INT` OR `STR`);

## List of commands

### DEF

- **Description:** Creates a value.

- **Usage:**

  ```
  DEF (name) AS (DT) ([IMD] DT | [FUN] DT);
  ```

- **Example code:**
  ```
  DEF x AS INT 5;
  ```

### DEL

- **Description:** Deletes the value from memory.

- **Usage:**

  ```
  DEL (name);
  ```

- **Example code:**
  ```
  DEL x;
  ```

### EXPLODE

- **Description:** Stops program execution.

- **Usage:**

  ```
  EXPLODE;
  ```

- **Example code:**
  ```
  EXPLODE;
  ```

### GOTO

- **Description:** Go to a line number, with optional condition (true if >= 1) with ON.

- **Usage:**

  ```
  GOTO ([IMD] INT | [FUN] INT);
  ```

  or

  ```
  GOTO ([IMD] INT | [FUN] INT) ON [[IMD] INT | [FUN] INT];
  ```

- **Example code:**
  ```
  GOTO 5;
  ```
  also
  ```
  GOTO GETVAL!(point) ON EQLS!(GETVAL!(x) 5);
  ```

### IN

- **Description:** Change value to user input.

- **Usage:**

  ```
  IN (name);
  ```

- **Example code:**
  ```
  IN x;
  ```

### POINT

- **Description:** Define a new INT value with command's line number.

- **Usage:**

  ```
  POINT (name);
  ```

- **Example code:**
  ```
  POINT x;
  ```

### PRINT

- **Description:** Prints a string to the console.

- **Usage:**

  ```
  PRINT ([IMD] STR | [FUN] STR);
  ```

- **Example code:**
  ```
  PRINT "Hello, world!";
  ```

### SET

- **Description:** Sets existing value to new content.

- **Usage:**

  ```
  SET (name) TO ([IMD] DT | [FUN] DT);
  ```

- **Example code:**
  ```
  SET x TO ADD!(GETVAL!(x) 5);
  ```

### SLEEP

- **Description:** Sleeps for some amount of seconds.

- **Usage:**

  ```
  SLEEP ([IMD] INT | [FUN] INT);
  ```

- **Example code:**
  ```
  SLEEP 5;
  ```

## List of build in functions

### ADD

- **Description:** Adds 2 numbers.
- **Returns:** INT

- **Usage:**

  ```
  ADD!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  ADD!(GETVAL!(x) 1);
  ```

### CMPSTR

- **Description:** Compares 2 strings if they are equal.
- **Returns:** INT (0/1)

- **Usage:**

  ```
  CMPSTR!(([FUN] STR) ([FUN] STR));
  ```

- **Example code:**
  ```
  CMPSTR!(GETVAL!(x) GETVAL!(y));
  ```

### DIV

- **Description:** Divides 2 numbers to nearest INT.
- **Returns:** INT

- **Usage:**

  ```
  DIV!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  DIV!(GETVAL!(x) 1);
  ```

### EQLS

- **Description:** Compares 2 numbers if they are equal.
- **Returns:** INT (0/1)

- **Usage:**

  ```
  EQLS!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  EQLS!(GETVAL!(x) 5);
  ```

### GETVAL

- **Description:** Gets contents of the value.
- **Returns:** DT

- **Usage:**

  ```
  GETVAL!((name));
  ```

- **Example code:**
  ```
  GETVAL!(x);
  ```

### JOINSTR

- **Description:** Joins 2 strings into 1.
- **Returns:** STR

- **Usage:**

  ```
  JOINSTR!(([FUN] STR) ([FUN] STR));
  ```

- **Example code:**
  ```
  JOINSTR!(GETVAL!(x) GETVAL!(y));
  ```

### LS

- **Description:** Compares 2 numbers if 1st one is smaller than 2nd.
- **Returns:** INT (0/1)

- **Usage:**

  ```
  LS!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  LS!(GETVAL!(x) 5);
  ```

### MOD

- **Description:** Modulo of 2 numbers.
- **Returns:** INT

- **Usage:**

  ```
  MOD!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  MOD!(GETVAL!(x) 1);
  ```

### MR

- **Description:** Compares 2 numbers if 1st one is bigger than 2nd.
- **Returns:** INT (0/1)

- **Usage:**

  ```
  MR!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  MR!(GETVAL!(x) 5);
  ```

### MUL

- **Description:** Multiplies 2 numbers.
- **Returns:** INT

- **Usage:**

  ```
  MUL!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  MUL!(GETVAL!(x) 1);
  ```

### NOT

- **Description:** Inverts a boolean: returns 1 if input is 0, returns 0 if input is greater than 0.
- **Returns:** INT (0/1)

- **Usage:**

  ```
  NOT!(([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  NOT!(GETVAL!(x));
  ```

### POW

- **Description:** Power of 1st number in 2nd.
- **Returns:** INT

- **Usage:**

  ```
  POW!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  POW!(GETVAL!(x) 1);
  ```

### ROOT

- **Description:** 2nd number root of 1st number, rounded to nearest INT.
- **Returns:** INT

- **Usage:**

  ```
  ROOT!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  ROOT!(GETVAL!(x) 1);
  ```

### SUB

- **Description:** Substracts 2 numbers.
- **Returns:** INT

- **Usage:**

  ```
  SUB!(([IMD] INT | [FUN] INT) ([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  SUB!(GETVAL!(x) 1);
  ```

### TOINT

- **Description:** Converts input to INT.
- **Returns:** INT

- **Usage:**

  ```
  TOINT!(([FUN] DT));
  ```

- **Example code:**
  ```
  TOINT!(GETVAL!(x));
  ```

### TOSTR

- **Description:** Converts input to STR.
- **Returns:** STR

- **Usage:**

  ```
  TOSTR!(([FUN] DT));
  ```

- **Example code:**
  ```
  TOSTR!(GETVAL!(x));
  ```

### STRSIZE

- **Description:** Returns size of a STR.
- **Returns:** INT

- **Usage:**

  ```
  STRSIZE!(([FUN] STR));
  ```

- **Example code:**
  ```
  STRSIZE!(GETVAL!(x));
  ```

### GETCHARAT

- **Description:** Returns character at position in the STR starting from 0.
- **Returns:** STR

- **Usage:**

  ```
  GETCHARAT!(([FUN] STR) ([IMD] INT | [FUN] INT ));
  ```

- **Example code:**
  ```
  GETCHARAT!(GETVAL!(x) 1);
  ```

### CHARFROMINT

- **Description:** Returns a character from INT code.
- **Returns:** STR

- **Usage:**

  ```
  CHARFROMINT!(([IMD] INT | [FUN] INT));
  ```

- **Example code:**
  ```
  CHARFROMINT!(65);
  ```