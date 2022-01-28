Made by Brandon Atwal BSA190001 in CS3345.0W3
I am submitting Project 2: Binary Search Trees with Lazy Deletion. I developed and compiled this project with NetBeans 8.2 with Java 1.8.0_05 (Java JDK 8 update 5). It can be run like any other NetBeans project. Every feature should work properly

Both java files are in the directory \AtwalProject2\src\. 
Your input file can be placed in the directory \AtwalProject2\. My own test input file inputAtwal.txt should be in that folder already, but per instructions, you can run it with the file name you choose by using different arguments.

My test run files are available within within the files inputAtwal.txt and outputAtwal.txt, but I have also copied them here.
Here is the contents of the input file:
Insert:31
Insert:8
Insert:50
Insert:12
Insert:10
Insert:20
PrintTree
next
Delete:12
Delete:45
Contains:10
FindMin
FindMax
PrintTree
Height
Size
Insert:98
Insert:84
Delete:50
Insert:132
PrintTree
Insert:980
insert:15
Insert
hih

Here is the contents of the output file:
True
True
True
True
True
True
31 8 12 10 20 50 
Error in Line: next
True
False
True
8
50
31 8 *12 10 20 50 
4
6
True
True
True
Error in insert: IllegalArgumentException raised
31 8 *12 10 20 *50 98 84 
Error in insert: IllegalArgumentException raised
Error in Line: insert:15
Error in Line: Insert
Error in Line: hih
