## LPOO_66 - Beast_1984

Your goal in this game is to kill every Beast that appears in your way, in the shortest time possible. You're trapped in a maze of movable blocks, and the only way to survive is by smashing your enemies between them. This might seem like an easy task, but as eggs evolve to Beasts, and its number increases, you'll soon realise that the power-ups will come in handy.

There's the option to choose from three different levels of difficulty but, regardless of your choice, the map will always be randomised. Once you win (or lose) the game, you'll be presented a scoreboard so that you can keep track of your performance improvements.

This project was Beastly developed by Athos Freitas (up202108792), Luís Du (up202105385) and Sofia Pinto (up202108682) for LP00 2022/2023.


### IMPLEMENTED FEATURES

**Move blocks** - Whenever the player moves, if it goes against a movable block, every linked block in that direction gets pushed.

**Hatching Eggs** - After some time eggs hatch into Beasts.

**Kill Beast** - In order for a Beast to be killed, it has to be smashed between any two blocks or a block and a wall.

**Kill Stronger Beast** - In order for a Stronger Beast to be killed, it necessarily has to be smashed against a wall.

![KillStrongerBeast](https://user-images.githubusercontent.com/92641892/208264166-8f3c3180-67c7-42ea-ae47-0951dd457334.gif)

**Catch Heart** - Hearts will randomly appear in the map as the game progresses. However, they might be inadvertently smashed between blocks. Whenever a player catches a heart, it gets added to the players lives, up to a maximum of 8.

**Catch Shield** - Similarly, shields will randomly appear in the map as the game progresses. However, they might be inadvertently smashed between blocks. They will turn whoever catches them immortal, for a short period of time.

**Catch Power-ups by the Beasts** - In the unfortunate scenario that a Beast catches any of the power-ups, it evolves to a Stronger Beast, unless it is already one. However, as beasts are a unified entity, whenever a power-up is caught by a Beast, all Beasts get a speed-up buff.

![Power-ups](https://user-images.githubusercontent.com/92641892/208264560-717d1e73-1222-456d-a960-ba6fd2150833.gif)

**Beasts follow player** - The movement of the Beasts is randomised in the direction of the Player (for instance, if the Player is on the top-left of the Beast it can only move up, left or in that diagonal)

**Respawn** - When the player takes damage it respawns at a random location of the map.

![Respawn](https://user-images.githubusercontent.com/92641892/208264168-f1737858-491f-4281-a286-22a399640040.gif)

**Scoreboard** - At the end of the game, you’ll be presented a scoreboard. The score is calculated based on the amount of Beast’s killed, the number of remaining lives and the number of shields caught, as well as the time it took the player to win (or lose).

![ScoreBoard](https://user-images.githubusercontent.com/92641892/208264170-464f511d-c4ad-4a8b-b4db-fd3104b1c1ad.gif)

### PLANNED FEATURES

There are no more planned features for the near future, since all of them were Beastly implemented.

### Class Diagram (UML)

In case you wish to check this diagram in more detail, please click [here](https://drive.google.com/file/d/181DXBcOBJZ6juCrQZg_XRwyEjpPJujS6/view?usp=sharing).

![UML](https://user-images.githubusercontent.com/92641892/209376441-091917a5-edb0-4393-a486-8892ec54bb82.jpg)

### DESIGN

#### GAME AND MENU STATES

**Problem in Context**

This project behaves differently, depending on which “part” is currently running. For instance, in the main menu it’s not desirable that a Beast appears out of nowhere, or that the scoreboard pops up during the game.

**State Pattern**

This pattern allows an object to modify its behaviour when its internal state changes. Therefore, we can smoothly switch from one state of the game to the other.

**Implementation**

![StatePattern](https://user-images.githubusercontent.com/92641892/209376446-537a37e8-739b-4fb2-8b67-04e5ccf4fa1f.jpg)

Classes:

- [State](../src/main/java/org.l06gr06/states/State.java)
- [ScoreboardMenuState](../src/main/java/org.l06gr06/states/ScoreboardMenuState.java)
- [MainMenuState](../src/main/java/org.l06gr06/states/MainMenuState.java)
- [ScoreMenuState](../src/main/java/org.l06gr06/states/ScoreMenuState.java)
- [LevelMenuState](../src/main/java/org.l06gr06/states/LevelMenuState.java)
- [GameState](../src/main/java/org.l06gr06/states/GameState.java)


**Consequences**

This way, we avoid using conditionals, plus, we make state transitions explicit, by creating a subclass (state) for each one of them.


#### THE GAME SHOULD RUN SMOOTHLY REGARDLESS OF A USER'S INPUT, OR LACK THEREOF.

**Problem in Context**

As the player's goal in this game is to kill every Beast in order to survive, the game keeps running even when the user isn’t providing input. If you sit staring at the screen, the game doesn’t freeze. Every object that's supposed to move, keeps doing so. If you’re unlucky, beasts catch powerups and keep attacking your player, until you lose.


**Game Loop Pattern**

This pattern allows a game loop to run continuously during gameplay. Each turn of the loop, it processes user input without blocking, updates the game state, and renders the game. It tracks the passage of time to control the rate of gameplay. This pattern allows us to address the identified problems because it ensures that the state of the game accurately reflects what it is meant to be, if some time has passed since the last update.

**Implementation**

![GameLoopPattern](https://user-images.githubusercontent.com/92641892/209367925-93fc595d-a250-4b73-9fc6-ffd534bfced5.jpg)

Classes:

- [GameController](../src/main/java/org.l06gr06/controller/game/GameController.java)
- [BeastController](../src/main/java/org.l06gr06/controller/game/BeastController.java)
- [PowerUpController](../src/main/java/org.l06gr06/controller/game/PowerUpController.java)
- [ArenaController](../src/main/java/org.l06gr06/controller/game/ArenaController.java)
- [PlayerController](../src/main/java/org.l06gr06/controller/game/PlayerController.java)
- [Arena](../src/main/java/org.l06gr06/model/game/arena/Arena.java)
- [GameViewer](../src/main/java/org.l06gr06/viewer/game/GameViewer.java)
- [ElementViewer](../src/main/java/org.l06gr06/viewer/game/ElementViewer.java)
- [PlayerViewer](../src/main/java/org.l06gr06/viewer/game/PlayerViewer.java)
- [BeastViewer](../src/main/java/org.l06gr06/viewer/game/BeastViewer.java)
- [BlockViewer](../src/main/java/org.l06gr06/viewer/game/BlockViewer.java)
- [WallViewer](../src/main/java/org.l06gr06/viewer/game/WallViewer.java)
- [PowerUpViewer](../src/main/java/org.l06gr06/viewer/game/PowerUpViewer.java)

**Consequences**

The use of the Game Loop Pattern, in the current design, decouples progression of game time from user input and processor speed.


#### CONTROLLERS AND VIEWERS

**Problem in Context**

As the game changes its state, each of them has its own controller and viewer. When running, the game doesn't know what "types" of controller and viewer will be required to create.

**Factory Method**

This pattern defines an interface for creating an object, but let subclasses decide which class to instantiate. The Factory method lets a class defer instantiation it uses to subclasses. It allowed to address the identified problems as it creates objects without exposing the instantiation logic to the client (therefore allowing for higher abstraction and cleaner code)

**Implementation**

![FactoryMethodPattern](https://user-images.githubusercontent.com/92641892/209376450-b7f585d9-2e10-46d8-a11c-c5619bd247a3.jpg)

Classes:

- [State](../src/main/java/org.l06gr06/states/State.java)
- [MainMenuState](../src/main/java/org.l06gr06/states/MainMenuState.java)
- [LevelMenuState](../src/main/java/org.l06gr06/states/LevelMenuState.java)
- [GameState](../src/main/java/org.l06gr06/states/GameState.java)
- [ScoreMenuState](../src/main/java/org.l06gr06/states/ScoreMenuState.java)
- [ScoreboardMenuState](../src/main/java/org.l06gr06/states/ScoreboardMenuState.java)
- [Controller](../src/main/java/org.l06gr06/controller/Controller.java)
- [MainMenuController](../src/main/java/org.l06gr06/controller/menu/MainMenuController.java)
- [LevelMenuController](../src/main/java/org.l06gr06/controller/menu/LevelMenuController.java)
- [GameController](../src/main/java/org.l06gr06/controller/game/GameController.java)
- [ScoreMenuController](../src/main/java/org.l06gr06/controller/menu/ScoreMenuController.java)
- [ScoreboardMenuController](../src/main/java/org.l06gr06/controller/menu/ScoreboardMenuController.java)
- [Viewer](../src/main/java/org.l06gr06/viewer/Viewer.java)
- [MainMenuViewer](../src/main/java/org.l06gr06/viewer/menu/MainMenuViewer.java)
- [LevelMenuViewer](../src/main/java/org.l06gr06/viewer/menu/LevelMenuViewer.java)
- [GameViewer](../src/main/java/org.l06gr06/viewer/game/GameViewer.java)
- [ScoreMenuViewer](../src/main/java/org.l06gr06/viewer/menu/ScoreMenuViewer.java)
- [ScoreboardMenuViewer](../src/main/java/org.l06gr06/viewer/menu/ScoreboardMenuViewer.java)

**Consequences**

The use of the Factory Pattern in the current design promotes the loose-coupling by eliminating the need to bind application-specific classes into the code. That means the code interacts solely with the resultant interface or abstract class, so that it will work with any classes that implement that interface or that extends that abstract class.


#### MVC - MODEL, VIEW, CONTROLLER

**Problem in Context** 

This project uses a GUI (Lanterna) to display the game and allow input by the player. It would become very confusing to handle the implementation of the game, take the inputs of the player and display everything in the same class. For example, having the Player class hold methods for taking input of the movement, for moving the Player and for showing it on screen would be confusing and hard to work on.

**MVC Pattern**

This Architectural pattern separates the implementation of classes into three different groups: 
The model classes - which represent the data; 
The viewer classes - which display the information using Lanterna GUI;
The controller classes - which also uses the GUI to get the next action (step) and alters the data accordingly.

**Implementation**

![MVCPattern](https://user-images.githubusercontent.com/92641892/209367929-a23fb045-db95-4a10-ae65-bb9569176c9e.jpg)

Packages:

- [Model Package](../src/main/java/org.l06gr06/model)
- [Viewer Package](../src/main/java/org.l06gr06/viewer)
- [Controller Package](../src/main/java/org.l06gr06/controller)

**Consequences**

This way, the program is subdivided in a way that is easier to work on (keeping the code organised) and test.


### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

#### Feature Envy

Due to the MVC architectural pattern that we have implemented, classes from packages `Viewer` and `Controller` both access data from the `Model` package more than their own.
This violates the Encapsulation principle, plus it may make the code harder to maintain, since methods from `Viewer` and `Controller` packages rely heavily on `Model`'s data.

This could be solved by **extracting** or **moving methods** from the first two packages to the later one, which would preclude the use of the MVC architectural pattern.

#### Shotgun Surgery

For instance, in case you wish to change the behaviour of a Heart, you would not only have to do that, but also change the way other objects interact with it, and maybe even the way it is drawn.
Some examples of methods you would have to alter include: `isEmpty()`, `canMove()`, `createPowerUp()` all in the `Arena` class; `drawHeart()` in the `LanternaGui` class; `step()` in both `PlayerController`and `BeastController`classes.

A way to improve the code would be to **move** all of those **methods** to a new appropriate class.

#### Parallel Inheritance Hierarchies

Whenever we wish to add a new class to the `Model` package, we then must add a new class to both `Viewer` and `Controller` packages. This makes the code harder to maintain and increases its fragility (tendency of software to break in multiple places whenever it is changed).

To remove these hierarchies we could **move fields** or **methods** to one of the packages, which would once more violate the MVC architectural pattern.

#### Switch Statements

The `PlayerController` class contains complex if...else statements in both `moveBlock()`and `movePlayer()` methods. This makes the code harder to read and understand.

A possible solution for this would be to **decompose the conditional**, which would imply the creation of new methods that would only be used in those particular cases.

#### Long Method

The `PlayerController` class includes the `moveBlock()`and `movePlayer()` methods, which are extensive and make the code hard to comprehend.

By **extracting** from these **methods**, we could make them smaller but, simultaneously, we would be creating methods of singular use.

#### Long Parameter List

The constructor of the `RandomArenaBuilder` class has a large amount of parameters, which can make the code harder to understand.

We could solve this by **introducing** a **parameter object** which would turn out to be a Data Class, hence the reason why we didn't do it.

### TESTING

#### Coverage Report

![CoverageReport](https://user-images.githubusercontent.com/92641892/209370489-92e63aec-3fef-4dda-a207-442a90fedd44.png)

#### Mutation Testing Report

Here is the [link](../pitest/index.html) to our mutation testing report.

![MutationTesting](https://user-images.githubusercontent.com/92641892/209371738-2f206f68-0fd4-4172-a4dd-bed47077356c.png)

### SELF-EVALUATION

We believe that, throughout the development of this project, the work was evenly distributed among all group members.

- Athos Freitas: 33%
- Luís Du: 33%
- Sofia Pinto: 33%
