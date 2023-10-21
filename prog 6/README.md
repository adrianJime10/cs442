# CS442: Assignment 3
## Name: Evan McNierney, AdrianJimenez

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in primePlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile primePlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile primePlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile primePlay/src/build.xml run-prime-detector \
-DinputFile="inputFile" \
-DnumThreads="numThreads" \
-Dcapacity="capacity" \
-DpersisterServiceIp="IP" \
-DpersisterServicePort="port" \
-DdebugValue="debugValue"

####Command: ant -buildfile primePlay/src/build.xml run-persister-service \
-Dport="port" \
-DoutputFile="outputFile"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:

This program takes in a an input text file and finds the prime numbers in it.
The text file should have one integer per line. The numbers are detected by multiple
worker threads and are sent via sockets to a persister program using another thread.
This program will not accept an empty input file. An error will be read to stderr and
the program will terminate.

Notes:
This program should work completely as intended. If there is an error in the input file
The persister service will not terminate, as it has not yet received a stop message
as per the assignment 3 instructions. Methods are public so they can be reused by other programs.

Structures used:
ArrayList - This structure was used for both the Thread Pool and the Results Data Structure.
it was chosen because of the ease of access, addition and removal of elements in it. Time complexity for
add/remove is O(n) and space complexity is O(n).

Design Patterns Used:
Object Pool - we used an object pool to store the threads. We are able to borrow from this pool,
but we can't take more than the pool has, ensuring that resources will not be wasted.

We would like to use two slack days because we committed more than 50% of our code within 48 hours of the deadline.
Neither of us have used a slack day yet.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [3/29/2020]


