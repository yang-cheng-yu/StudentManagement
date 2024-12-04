# Final Project for Programming 2 course
### What it is
This is a student management system client coded in `Java` with `Maven` using `JavaFX` library for GUI design. The client uses `Java Platform SE Binary` for displaying and running the GUI. 
### Features
- Pastel dark theme
- Add student window
- Student list table view with selection feature
- Student ID search function
- Remove student button
- File chooser (txt) for saving and loading
### Software
- IntelliJ
- Scene Builder
### Installation
This is an app-image. No installation required
Extract the `.zip` file and run `StudentManagement.exe`
Everything required to run the software is bundled using `JPackage`
### User Guide
---
#### GUI guide
Drag the light pink bar to move the windows around
Press [X\] or [Exit\] to close and [_\] to minimize
---
#### Add a student
Use [Add Student\] button to open 'Add Student' window
Fill the form and click submit to add a student. (Student ID is automatically generated)
Close the 'Add Student' window using [X\] button
*Note: [Add Student\] button is disabled when 'Add Student' window is open*
---
#### View student
Students can be view on the side list
Select a student to view, or search for ID using search box
Selecting a student will provide student Name, Gender and GPA
Selecting a student will also allow the user to remove selected student using [Remove\] button, in which case, the student will be removed from the list and all student information will be cleared
---
#### Save file
Click choose file to open Windows File Explorer
Choose a `.txt` file
The path of the selected file will show up next to the button to confirm selected file
Click [Save\] button to save data to the `.txt` file
Click [Load\] button to load data from existing file
*Note: All data is encrypted*
