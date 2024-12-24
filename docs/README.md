# LDTS Project T01G02 - Hello Kitty's Party


<p align="center">
    <img width="500" src="/docs/images/GameStart.png" alt="Hello Kitty's Party"/>
</p>

## Description
Our game is inspired by an existing game from our childhood, however we added our own personal twist.

Players take on the role of Kitty, whose mission is to gather her friend´s- Cinnamon Roll, Kuromi, My Melody, and Pompompurin- from their homes at a time, and escort them to her birthday party venue within a five-minute time limit. Failure to accomplish this means Kitty won´t be able to reunite her friends for this special occasion, resulting in a loss.

The gameplay is simple and engaging. Use the key arrows to guide Kitty through town. When you reach a friend´s house, they will begin following Kitty. Your job is then to bring them to the designated party venue.

### Features
At the top of the screen, you will find the timer and your current score, keep an eye on both! 

The aim of the game is to pick up the four friends and drop them off at the party as quickly as possible. The faster you complete your mission and the more bonuses you collect, the higher your final score.

To make the game more interesting and enjoyable there are power-ups and obstacles. Lightning bolts will incre
ase Kitty´s speed, helping you complete the tasks more efficiently. Hearts add extra points to your score. Avoid roadblocks and beware of mud puddles, as they will slow Kitty down and reduce your score.


<div align="center">
    <table>
        <tr>
            <td align="center">
                <img width="300" src="/docs/images/PickupFriend.png" alt="Pickup Friend"/><br/>
                <strong>Picking up a Friend</strong>
            </td>
            <td align="center">
                <img width="300" src="/docs/images/Star.png" alt="Bonus Star"/><br/>
                <strong>Bonus Star</strong>
            </td>
        </tr>
    </table>
</div>


### Mockups

<div align="center">
    <table>
        <tr>
            <td align="center">
                <img width="300" src="/docs/images/menu_mockup.jpeg" alt="Menu Mockup"/><br/>
                <strong>Menu Mockup</strong>
            </td>
            <td align="center">
                <img width="300" src="/docs/images/game_mockup.jpg" alt="Game Mockup"/><br/>
                <strong>Game Mockup</strong>
            </td>
        </tr>
    </table>
</div>


### Design Patterns

### Menu
For our game’s menu, we adhered to the **Model-View-Controller (MVC)** architectural pattern, organizing the code into three interfaces: IModel, IView, and IController—and their respective implementations: GameMenuModel, GameMenuView, and GameMenuController. 
Additionally, we have also created new classes to handle our game´s settings following the same principle.

The GameMenuModel class handles data such as menu options and game information. 
The GameMenuView renders the interface based on the model’s data. Finally, the GameMenuController processes user inputs, updating the view and model accordingly.

Similarly, the SettingsModel class manages settings data, such as music and sound options, storing the current state and providing methods to update this state. 
The SettingsView class is responsible for rendering the settings menu based on the data in the SettingsModel. 
Ultimately, the SettingsController processes user inputs, updating the settings model and refreshing the view.

We also have other components in our menu system, such as:

•	GameOverController: Manages the game over state, processes inputs related to restarting or exiting the game, and updates the view accordingly.

•	GameOverView: Displays the game over screen to the player.

Regarding design patterns, our menu employs the strategy, command, and observer behavioral patterns, as well as the factory creational pattern.

1.	**Observer Pattern**: Implemented by the interaction between the GameMenuModel and GameMenuView classes. The view continuously checks for changes in the model and updates the display accordingly, such as redrawing the screen. This ensures that the view remains consistent with the underlying data.
2.	**Strategy Pattern**: Identified in the SettingsController class, which encapsulates related behaviors such as toggleMusic() and toggleSound(). These methods dynamically modify the system’s behavior by toggling music or sound settings, allowing for flexible and interchangeable strategies.
3.	**Command Pattern**: Evident in the processInput() method in the GameMenuController and SettingsController, which processes various user inputs (ArrowLeft, ArrowRight, Esc, and Enter). Each input triggers distinct actions, such as selecting options, exiting, or starting the game. This decouples the sender of a request from its receiver, promoting flexibility in command execution.
4.	**Factory Pattern**: Demonstrated through the use of DefaultTerminalFactory().createTerminal(). This abstraction simplifies the creation of terminal objects, allowing the client code in Main to interact with a generic interface without dealing with the instantiation details. This promotes encapsulation and decouples object creation from its usage.

These design patterns help us maintain a clean and scalable architecture, facilitating easier maintenance and extension of the game’s menu system. 
By adhering to MVC and employing these patterns, we ensure a clear separation of concerns and robust design.

<p align="center">
    <img width="500" src="/docs/images/UMLs/Menu.png" alt="Menu UML"/>
</p>


### Game
#### Architectural Pattern

We also implemented the MVC (Model/View/Controller) architectural pattern in the game itself.

- The Model stores all the structure of each element inside the game (City, Characters).
- The View displays the Model in a graphical user interface.
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

#### Observer Pattern

We also applied the observer pattern in the Game, making the Score Controller a Kitty Observer. We used Score Controller
so that the MVC would be clean and there would be no classes in model observing the controllers. Kitty Observer 
observed the Friends Controller to always be notified immediately when hello kitty picked up or dropped off a friend and 
Kitty Controller, so that the score would double

**Consequences**

The Observer pattern keeps the ScoreController updated whenever something important happens, like a friend being picked 
up or dropped off. This makes the system easier to expand or change since the ScoreController doesn’t need to directly 
control or depend on the other components. However, it can make debugging harder because the updates happen 
automatically in the background.

<p align="center">
    <img width="500" src="/docs/images/UMLs/ObserverPattern.png" alt="OberverUML"/>
</p>



#### KNOWN CODE SMELLS

Some code smells that we know are present in our code is for example the game loop, along with its initialization. 
We realized too late that there were far too many elements that needed to be initialized and then updated, and so the
game loop became very messy. We created a separate method for element initialization to keep the constructor cleaner.
However, the game loop isn't sustainable in the long term and should be updated, perhaps with a State pattern. 

Another detail we noticed, is that we since we initialize the game in a new terminal that is not the same in the menu
screen, there's a disconnect in the user experience. This forces the user to switch between interfaces, which isn't 
smooth or intuitive. Refactoring this process to maintain a unified interface throughout the game, possibly by reusing 
the same terminal or using a single window with dynamic views, would improve the overall user experience.

Finally, some methods and classes have grown larger than initially planned, making them harder to read and maintain. 
Breaking these into smaller, more focused components would align better with the Single Responsibility Principle and 
improve code readability and scalability. This includes simplifying the logic in controllers and separating concerns 
further, ensuring each part of the code has a clear and specific role.


### TESTING

The full Pitest report is included in the Docs folder
<p align="center">
    <img width="500" src="/docs/images/PitestReport.png" alt="PitestReport"/>
</p>

### SELF-EVALUATION

- Gabriela de Mattos: 33%
- Matilde Duarte: 33%
- Matilde Sousa: 33%


