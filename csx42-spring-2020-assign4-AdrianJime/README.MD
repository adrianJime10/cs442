CS442: Assignment 4

Name: AdrianJimenez


Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in statePlay/src folder.


Instruction to clean:
####Command: ant -buildfile statePlay/src/build.xml clean
Description: It cleans up all the .class files that were generated when you compiled your code.


Instruction to compile:
####Command: ant -buildfile statePlay/src/build.xml all
Description: Compiles your code and generates .class files inside the BUILD folder.


Instruction to run:
####Command: ant -buildfile statePlay/src/build.xml run-state-driver -DinputFile="inputFile" -DitemsFile="itemsFile" -DwindowSize="windowSize" -DoutputFile="outputFile"
Note: Arguments accept the absolute path of the files.


Description:
This program uses the state pattern to determine if a person's spending state and whether or not
they will make certain purchases. The decisions of specific items will be written in the 
specified output file. This program will not take any empty input files.

Structures used: Queue - This structure was used for the window of income because of its 
constant time operations of adding to the tail of the queue and removing the head of this queue 
(in a fifo fashion).

Map - This structure was used for its constant time retrieval and placement of key value pairs.
It was also used because it allows for multiple keys to be mapped to a single value.


Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating an official form will be submitted to the Academic Honesty Committee of the Watson School to determine the action that needs to be taken. "
Date: [4/19/2020]
