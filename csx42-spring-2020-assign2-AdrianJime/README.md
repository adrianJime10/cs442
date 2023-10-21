# CSX42: Assignment 2
## Name: Adrian Jimenez

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DrunAvgOutFile="<output file path to which running averages are written>" \
-Dk="<max size of the list containing the top K numbers>" \
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" \
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```

-----------------------------------------------------------------------
## Description: This program reads an input file with a single number per line and -
- Creates an output file with all the peaks in the input file
- Creates an output file with the top k numbers in the input file
- Creates an output file with the running average of numbers within a given window size

This program users the Observer design pattern

-----------------------------------------------------------------------
##Data Structures used: 

HashMap - A hashMap is used to efficiently map filters to their respective observers
	  so that the observers would only listen to specific events in O(n^2) time.

ArrayList - An arraylist is used in the NumberProcessor and another two are used in the
	    NumberPeaksData class so that elements may be added and retrieved from the
	    list in O(1) time.

PriorityQueue - A PriorityQueue is used in the TopKNumbersData and Observer class to
	        keep track of the largest numbers by only performing one comparison
	        each time a number is added to the queue in O(N log N) time.

ArrayBlockingQueue - An ArrayBlockingQueue is used in the RunningAverageObserver to
		     prevent the addition of elements past the max size of the array
		     and providing the implementation of a FIFO queue at the same time.
		     Poll() and Offer() are performed in O(1) time.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [2/23/20]


