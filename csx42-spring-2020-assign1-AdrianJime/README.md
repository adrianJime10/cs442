# CSX42: Assignment 1
## Name: Adrian Jimenez

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Darg0="input.txt" -Darg1="output.txt" -Darg2="metrics.txt"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description: This program reverses the words of an input file


-----------------------------------------------------------------------
## Data Structures Used:

ArrayList - This program uses an array list to store each word within the input file as its own element
so that they may be accessed, and overwritten in O(n) time.

HashMap - This program uses a hashmap so that word frequencies may be updated in O(1) time.

-----------------------------------------------------------------------

## Citations:

//https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [02/05/2020]


