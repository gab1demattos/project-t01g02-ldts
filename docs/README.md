# LDTS Project - Hello Kitty's Party


## Description
Our game is inspired by an existing game from our childhood, however we added our own personal twist.

Players take on the role of Kitty, whose mission is to gather her friend´s- Cinnamon Roll, Kuromi, My Melody, and Pompompurin- from their homes at a time, and escort them to her birthday party venue within a five-minute time limit. Failure to accomplish this means Kitty won´t be able to reunite her friends for this special occasion, resulting in a loss.

The gameplay is simple and engaging. Use the key arrows to guide Kitty through town. When you reach a friend´s house, they will begin following Kitty. Your job is then to bring them to the designated party venue.

### Features
At the top of the screen, you will find the timer and your current score, keep an eye on both! 

The aim of the game is to pick up the four friends and drop them off at the party as quickly as possible. The faster you complete your mission and the more bonuses you collect, the higher your final score.

To make the game more interesting and enjoyable there will be power-ups and obstacles. Lightning bolts will increase Kitty´s speed, helping you complete the tasks more efficiently. Hearts add extra points to your score. Avoid roadblocks and beware of mud puddles, as they will slow Kitty down and reduce your score.

### Mockups

Menu Mockup
<p align="center">
    <img width="500" src="/docs/images/menu_mockup.jpeg" alt="Menu Mockup"/>
</p>


Game Mockup
<p align="center">
    <img  src="/docs/images/game_mockup.jpg" alt="Menu Mockup"/>
</p>


## Design Patterns
Having studied design and architectural patterns, we took them into consideration before starting to write our code to improve readability, flexibility and code reusability.

### Menu
For our game´s menu, we adhered to the **Model-View-Controller (MVC)** architectural pattern, organizing the code into three interfaces- IModel, IView, and IController- and their respective implementations: GameMenuModel, GameMenuView, and GameMenuController. Additionally, we created a separate model class, Settings, to manage the game´s settings.

The GameMenuModel class handles data such as menu options and game information. The GameMenuView renders the interface based on the model´s data. Finally, the GameMenuController processes user inputs, updating the view and model accordingly.

Regarding design patterns, our menu employs the **strategy**, **command**, and **observer** **behavioral patterns**, as well as the **factory creational pattern**.

Firstly, the observer pattern is implemented by the interaction between the GameMenuModel and GameMenuView classes. The view continuously checks for changes in the model and updates the display accordingly, such as redrawing the screen.

Next, the strategy pattern can be identified in the Settings class, which encapsulates two related behaviors- musicSound() and toggleSound()- that dynamically modify the system´s behavior by toggling music or sound.

Moving on to the command pattern, this is evident in the processInput() method in the GameMenuController, which processes various user inputs (ArrowLeft, ArrowRight, Esc and Enter). Each input triggers distinct actions, such as selecting options, exiting or starting the game.

Finally, the factory method is demonstrated through the use of DefaultTerminalFactory().createTerminal(). This abstraction simplifies the creation of terminal objects, allowing the client code in Main to interact with a generic interface without dealing with the instantiation details.

### Game
#### Architectural Pattern

We Implemented the MVC (Model/View/Controller) design pattern.

- The Model stores all the structure of each element inside the game (City, Characters).
- The View displays the Model in a graphical user interface
- The Controller handles user input and updates the Model and View

**Consequences**

This pattern organizes the code, which makes it easier to update specific parts of the game without affecting others,
and improves the overall readability and scalability of the project.

#### Factory Method

This pattern is used to create complex objects in a centralized and reusable manner.
It's implemented in both Model Classes (CityModel and CharacterModel), with the initialize methods 
which are then called in the Game class.

**Consequences**

Factory methods in our game eliminate the need to hard-code specific classes. The game logic interacts with 
generic interfaces like Tile or CharacterModel, making it easy to add new types of tiles or characters without modifying
existing code.

#### Composite Pattern

We applied the Composite pattern to manage the hierarchy of game elements like CityModel, Tile, and Road. Roads are 
collections of Tile objects, and the CityModel organizes these components into a cohesive structure, treating individual 
tiles and groups (like roads) uniformly.

**Consequences**

This approach simplifies game logic by allowing it to handle tiles and composed structures the same way. It also makes 
the system more flexible, as adding new components like buildings or new road types can be done without modifying the
existing structure.


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/rUa5vdmg)
