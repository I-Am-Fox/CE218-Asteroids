# Asteroids Game

This is a simple implementation of the classic Asteroids game in Java.

## Game Description

In this game, the player controls a spaceship and must avoid and destroy incoming asteroids. The game ends when the player's spaceship is hit by an asteroid.

## Game States

The game can be in one of the following states:

- Menu: The game is in the main menu.
- Title: The game is in the title screen.
- Game: The game is currently being played.
- Hit: The player's spaceship has been hit.
- GameOver: The game is over.

## Classes

The game is implemented using several classes:

- `Game`: This is the main class that controls the game logic and state.
- `Spaceship`: This class represents the player's spaceship.
- `Asteroid`: This class represents an asteroid.
- `HUD`: This class represents the heads-up display, which shows the player's score and remaining lives.
- `EnemyShip`: This class represents an enemy ship.
- `Shot`: This class represents a shot fired by a ship.

## How to Run

To run the game, compile and run the `Game` class.

## Future Work

Future updates will include more game states, additional types of game objects, and improved collision detection.
