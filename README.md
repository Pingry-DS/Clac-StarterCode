# Clac Assignment

## Introducing the Claculator
Clac is a new stack-based programming language developed by a Pittsburgh-area startup called Reverse Polish Systems (RPS). Any similarities of Clac with Forth or PostScript are purely coincidental. In the first part of this assignment, we will be implementing the core features of the Claculator, and later we will be adding a few more interesting features.

Clac works like an interactive calculator. When it runs, it maintains an operand stack. Entering numbers will simply push them onto the operand stack. When an operation such as addition `+` or multiplication `*` is encountered, it will be applied to the top elements of the stack (consuming them in the process) and the result is pushed back onto the stack. When a newline is read, the number on top of the stack will be printed.

This is an example where we start Clac and type `3 4 + `, followed by a newline:
```
clac>> 3 4 +
stack] 7
```

Here is another example:
```
clac>> 1 2 + 3 *
Stack] 9
```

Before moving on please make sure you understand why the following will output 11:
```
clac>> 4 3 * 2 - 1 +
Stack] 11
```

## How Clac Works
In addition to the arithmetic operations, clac implements a few special operations. The table below lists the complete set of operations.

To specify the operations, we use the notation `S | Q → S’ | Q’`
to mean that the stack `S` and the queue `Q` transitions to become stack `S’` and queue `Q’`. We use a queue to hold the numbers and operations Clac evaluates. Stacks are written with the top element at the right end!

For example, multiplication is stated as
`S, x, y | * , Q  →  S, x*y | Q`
which means: "When you dequeue the token `*`, pop the top element (`y`) and the next element (`x`) from the stack, multiply `y` by `x`, and push the result `x * y` back onto the stack." The fact that we write S in the rule above means that there can be many other integers on the stack that will not be affected by the operation.

Every operation in Clac is determined by the token that has just been dequeued. The `<` and `if` tokens cause different things to happen depending on the specific values on the stack.

`S, x, y | < , Q  →  S, 1 | Q` if x < y

`S, x, y | < , Q  →  S, 0 | Q` if x ≥ y

In Clac, we use the integer `0` to mean false and we treat non-zero values like `1` or `7` as true.

## Instruction Set
|   S   |   Q   |   S'  | Q'  | Condition / Effect |
| ----- | ----- | ----- | --- | ------------------ |
| S     | n, Q  | S, n| Q   |   |
| S, n  | `print`, Q | S | Q | prints top of stack to screen |
| S     | `quit` , Q | S | Q | quits clac interpreter |
|S, x, y| + , Q | S, x+y | Q | |
|S, x, y| - , Q | S, x-y | Q | |
|S, x, y| * , Q | S, x*y | Q | |
|S, x, y| / , Q | S, x/y | Q | |
|S, x, y| % , Q | S, x%y | Q | |
|S, x, y|** , Q | S, x^y | Q | |
|S, x, y| < , Q | S, 1   | Q | if x < y |
|S, x, y| < , Q | S, 0   | Q | if x >= y|
|S, x, y| = , Q | S, 1   | Q | if x = y |
|S, x, y| = , Q | S, 0   | Q | if x != y |
| S, x  |`drop`,Q|  S    | Q | |
|S, x, y|`swap`, Q |S, y, x|Q| |
|S, x, y, z|`rot`,Q|S, y, z, x|Q| |
| S, x  |`if`, Q| S      | Q |x != 0|
| S, x  |`if`, tok<sub>1</sub>, tok<sub>2</sub>, tok<sub>3</sub>, Q | S | Q | x = 0 |
| S, x<sub>n</sub>, . . . , x<sub>1</sub>, n |`pick`, Q |S, x<sub>n</sub>, . . . , x<sub>n</sub>| Q |See note|
| S, n  |`skip`, tok<sub>1</sub>, . . . , tok<sub>n</sub>, Q | S | Q |See note |

Note: `pick` should throw an `IndexOutOfBoundsException` if n <= 0. `skip` should throw an `IndexOutOfBoundsException` if n < 0.

## First Task (Basic Operators)
#### You Must
* Add implementations of `+`, `-`, `*`, `**`, `/`, `%`, `<`, and `=` according to the specification.
* Add definitions of `drop`, `swap`, `rot`, `if`, `skip`, and `pick` according to the specification.
* Raise the appropriate exceptions according to the instruction set.
* Add your authorship info to this Readme
* Remove this portion of the assignment once it is complete

#### You Must Not
* Modify files other than `clac.java`
* Modify code outside of the specified block
* Manually suppress compiler warnings
* Commit any additional `.class` files

#### To run your code
* `java ClacRunner` opens a clac interpreter to interactively test your current clac implementation.

* `java ClacTester` runs your implementation of clac against a test suite.

## Second Task (Function Definitions)
Finally, we add definitions to Clac. A definition has the form:
```
clac>> : name token 1 ... token n ;
```
#### Parsing Tokens
When we encounter the token `:` in the input queue, we interpret the following token as a name. Then we create a new (separate) queue, intended to hold the definition of name. Then we continue to scan the input queue, copying each token to the new queue until we encounter a token `;` which signals the end of the definition. Then we add name, with the new queue as its definition, to the dictionary.
If the queue ends after the `:`, or if there is no semicolon `;` in the remainder of the queue, an error should be signaled. In a definition, name can be any token, but if it is a built-in operator or a number, then the definition can never be invoked since it is always superseded by the predefined meaning.

#### An example
```
clac>> : dup 1 pick;
stack]

clac>> 2 dup
stack] 2 2

clac>> : square dup * ;
stack] 2 2

clac>> square
stack] 2 4
```
This defines `dup` and `square`. Whenever we see square in the input subsequently, it has the effect of replacing n on the top of the stack with n<sup>2</sup> .

How do we process a defined name when we encounter it in the queue of tokens? This is not entirely straightforward, as the following example illustrates:
```
clac>> 3 square 4 square +
stack] 25
```
When we see the first occurrence of square we cannot simply replace the rest of the queue with the definition of square , since after squaring 3 we have to continue to process the rest of the queue, namely `4 square +` .
In order to implement this, we maintain a stack of queues of tokens. This is called the return stack or sometimes the call stack. When we encounter a defined token, we push the remainder of the queue onto the return stack, and begin using the definition as our queue of tokens. When we finish executing a definition we pop the prior queue of tokens from the return stack and continue with processing it. When there are no longer any queues on the return stack we return from the eval function.

You may remove the same queue from a dictionary more than one time, like the queue one associated with square in the example above. Therefore, it’s important that each time you look up a queue in the dictionary, you make a copy of it before you dequeue from that queue. There is a method in `clac.java` that returns a copy of the queue passed as a parameter.

Here is an example of complex clac program that converts a decimal number to a binary number:
```
clac>> : convert 1 pick 2 / dec2bin 10 * swap 2 % + ;
stack]

clac>> : dec2bin 1 pick if convert ;
stack]

clac>> 37 dec2bin
stack] 100101
```
