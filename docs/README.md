# LDTS Project - Hello Kitty's Party


## Description

### Features
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
### Architectural Pattern

We Implemented the MVC (Model/View/Controller) design pattern.

- The Model stores all the structure of each element inside the game (City, Characters).
- The View displays the Model in a graphical user interface
- The Controller handles user input and updates the Model and View

**Consequences**

This pattern organizes the code, which makes it easier to update specific parts of the game without affecting others,
and improves the overall readability and scalability of the project.

### Factory Method

This pattern is used to create complex objects in a centralized and reusable manner.
It's implemented in both Model Classes (CityModel and CharacterModel), with the initialize methods 
which are then called in the Game class.

**Consequences**
Factory methods in our game eliminate the need to hard-code specific classes. The game logic interacts with 
generic interfaces like Tile or CharacterModel, making it easy to add new types of tiles or characters without modifying
existing code.

### Composite Pattern

We applied the Composite pattern to manage the hierarchy of game elements like CityModel, Tile, and Road. Roads are 
collections of Tile objects, and the CityModel organizes these components into a cohesive structure, treating individual 
tiles and groups (like roads) uniformly.

**Consequences**

This approach simplifies game logic by allowing it to handle tiles and composed structures the same way. It also makes 
the system more flexible, as adding new components like buildings or new road types can be done without modifying the
existing structure.


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/rUa5vdmg)
