# LDTS Project T01G02 - Hello Kitty's Party


## Description
Our game is inspired by an existing game from our childhood, however we added our own personal twist.

Players take on the role of Kitty, whose mission is to gather her friend´s- Cinnamon Roll, Kuromi, My Melody, and Pompompurin- from their homes at a time, and escort them to her birthday party venue within a five-minute time limit. Failure to accomplish this means Kitty won´t be able to reunite her friends for this special occasion, resulting in a loss.

The gameplay is simple and engaging. Use the key arrows to guide Kitty through town. When you reach a friend´s house, they will begin following Kitty. Your job is then to bring them to the designated party venue.

### Features
At the top of the screen, you will find the timer and your current score, keep an eye on both! 

The aim of the game is to pick up the four friends and drop them off at the party as quickly as possible. The faster you complete your mission and the more bonuses you collect, the higher your final score.

To make the game more interesting and enjoyable there will be power-ups and obstacles. Lightning bolts will increase Kitty´s speed, helping you complete the tasks more efficiently. Hearts add extra points to your score. Avoid roadblocks and beware of mud puddles, as they will slow Kitty down and reduce your score.

METER SCREENSHOTS DAS FEATURES

### Mockups

Menu Mockup
<p align="center">
    <img width="500" src="/docs/images/menu_mockup.jpeg" alt="Menu Mockup"/>
</p>


Game Mockup
<p align="center">
    <img  src="/docs/images/game_mockup.jpg" alt="Menu Mockup"/>
</p>

### Design Patterns

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** Explain the diifferent problemse description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

------

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

**Problem in Context**

There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.

**The Pattern**

We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementation**

The following figure shows how the pattern’s roles were mapped to the application classes.

///rewrite this into the top part
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
 ////

#### KNOWN CODE SMELLS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

Menu and game start is confusing and should the game start and loop should be cleaner


### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

- Gabriela de Mattos:
- Matilde Duarte:
- Matilde Sousa:


