# README

## Important Maintenance

### 1. Project Organisation

* Using Maven as build tool: a standardised project structure makes the project easier to use or import from a different context.

* Packaging of source code: model, controller, test.

* Categorised resources: view, CSS, images, sounds, fonts.

### 2. Testing

* Unit tests focusing on abstract models such as Actor were implemented prior to any refactoring to ensure that the most crucial functionalities were always preserved.

* Unit tests for concrete models such as World and Obstacle were created along the refactoring of the respective classes. All further refactoring or extension were introduced with the premise of passing all previous tests for quality and correctness assurance.

### 3. Refactoring on the Frog Class

* The original Animal class was renamed to Frog.

* The first stage of refactoring focused on reformatting, renaming and extracting methods. This process brought enhanced readability and reduced duplication of the code, while establishing a clearer understanding to the purposes of fields and methods and the structure of method calling chains.

* A further refactoring took place later on extracting the controls of Frog to GameController. This enhanced the separation of model and controller and brought higher clarity to the code.

### 4. Refactoring on Concrete Classes

* A MovingActor class was abstracted from actors with customisable speed to facilitate the implementation of related features such as speeding up moving actors on level up.

* Abstract class MyStage was removed and the World class was made concrete for the single purpose of managing actors and related events.

* BackgroundMusic was extracted from MyStage and adapted to the Singleton pattern to enforce the use of a single instance, and therefore facilitate further extensions and features such as muting and pausing.

* Visibility of fields and methods within or among packages was carefully managed.

* Unused fields and methods were eliminated.

### 5. Debugging

* Bugs were discovered along the refactoring and immediately fixed on separate branches. The most important fixes cover:  
    * Score calculation and display logic.
    * Handling of key press events.
    * Death checking logic.
    * Movement issues under control or around boundaries.

### 6. The MVC Pattern

* The project adheres the MVC pattern. This separates design, data/behaviour and event controlling, while providing a convenient link in between.

* FXML and CSS were used for the design of views.

* Based on the MVC pattern, different views were implemented with little effort.

### 7. Documentation

* Primary inline documentations were written along the refactoring and extending process.

* Javadoc was adopted at the final stage based on existing inline documentations, producing a systematic and user-friendly guide for future maintenance and development.

---

## Key Features

### Game Rule

* Infinite game, limited number of lives.

* Levels:
    * Level 1: Ordinary obstacles, logs, turtles and wet turtles. Slow speed.
    * Level 2: Random crocodile heads and flies start to emerge in slots. Increased speed.
    * Level 3: Snakes start to appear in middle lane. Medium speed.
    * Level 4: Introducing swimming crocodiles. Increased speed.
    * Level 5: Occasional snakes on logs. High speed.
    * Level 6 and onwards: Same as level 5.

* Bonus points for catching a fly.

* Bonus life on occasion.

### New Functionality

* Pausing with SPACE and muting with M.

* Home page and help page.

* The leaderboard records the top 10 high scores, the dates and the names of their players.

### Easter Egg

* A snowy theme can be activated or deactivated by typing "scyjc1" (which is my university username obviously) during the game. Do not hesitate to try it out!
