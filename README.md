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
Bug: Boundary movement issues at right and bottom.

24/11/19
Resources reorganised.
General refactoring on concrete Actor classes.
Extracted BackgroundMusic class from MyStage and applied with Singleton pattern.
World class made concrete and MyStage class removed.
