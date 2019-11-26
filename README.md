# Documentation: key changes for maintenance and extension

16/11/19
Project initiated.

18/11/19
Project structure established, resources reorganised.

19/11/19
Tests implemented for abstract classes.
Modifications on visibility of classes and methods.
Minor reformatting.

20/11/19
Frog class partially refactored.

21/11/19
Further refactoring on the Frog class.
Bug: Points calculation error when frog enters the same slot multiple times.
Bug: Points not awarded when holding W to progress forward.
Fix: Points not awarded when holding W to progress forward: extract method refactoring.
Bug: Points calculation error on certain deaths.
Bug: Frog movement issues.

23/11/19
Fix: Points calculation error when frog enters the same slot multiple times.
Fix: Frog movement issues.
Fix: Points display and calculation error.

24/11/19
Resources reorganised.
General refactoring on concrete Actor classes.
Extracted BackgroundMusic class from MyStage and applied with Singleton pattern.
World class made concrete and MyStage class removed.
Fix: Turtles and WetTurtles disappear on the left edge.

25/11/19
Start adopting the MVC pattern.

26/11/19
Preliminary design to the Home view.
Game view generally adapted to the MVC pattern.
Refined pictures on Game view.
