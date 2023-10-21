CS442: Assignment 5

Name: Adrian Jimenez

Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in visitorPlay/src folder.

Instruction to clean: ####Command: ant -buildfile visitorPlay/src/build.xml clean Description: It cleans up all the .class files that were generated when you compiled your code.

Instruction to compile: ####Command: ant -buildfile visitorPlay/src/build.xml all Description: Compiles your code and generates .class files inside the BUILD folder.

Instruction to run: ####Command: ant -buildfile visitorPlay/src/build.xml run-visitor-driver -DinputFile="inputFile" -DacceptableWordsFile="acceptableWordsFile" -Dk="k" -DtopKOutputFile="topKOutputFile" -DspellCheckOutputFile="spellCheckOutputFile" Note: Arguments accept the absolute path of the files.

Description: This program uses the vistor pattern to both spellcheck each word in a sentence and find the top k most frequent words in a sentence. This spell checked words and the top k most frequent words will be written to the specified output files. This program will not accept any empty input files.

Structures Used: Priority Queue - This structure was used to determine the top k most frequent words in a sentence because of its capability to compare objects as you place them into the queue in addition to being able to remove the head of the queue in O(1) time.

ArrayList - This structure was used to store the acceptable words read in from a file because of its ability to resize itself since there is no restriction on the size of the acceptable words file.

HashMap - This structure was used to correlate misspelled words with possible corrections because of its O(1) insertion time and because of its capability to relate keys and values.

Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating an official form will be submitted to the Academic Honesty Committee of the Watson School to determine the action that needs to be taken. " Date: [5/5/2020]
