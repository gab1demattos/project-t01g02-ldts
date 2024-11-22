package com.t01g02.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterModelTest {
        private List<CharacterModel> characters;
        private CityModel city;

        @BeforeEach
        void setUp() throws IOException {
            city = new CityModel(500, 300);

            characters = List.of(
                    new CharacterModel(new Sprite(null, "/home/matilde/IdeaProjects/project-t01g02/src/test/resources/hellokitty.png"), new Position(340, 127), "HelloKitty"),
                    new CharacterModel(new Sprite(null, "/home/matilde/IdeaProjects/project-t01g02/src/test/resources/kuromi.png"), new Position(273, 226), "Kuromi"),
                    new CharacterModel(new Sprite(null, "/home/matilde/IdeaProjects/project-t01g02/src/test/resources/purin.png"), new Position(28, 41), "Purin"),
                    new CharacterModel(new Sprite(null, "/home/matilde/IdeaProjects/project-t01g02/src/test/resources/mymelody.png"), new Position(42, 177), "MyMelody"),
                    new CharacterModel(new Sprite(null, "/home/matilde/IdeaProjects/project-t01g02/src/test/resources/cinnamoroll.png"), new Position(222, 73), "Cinnamoroll")
            );
        }

        @Test
        void testCharacterInitialization() {
            assertEquals(5, characters.size());
            assertEquals("HelloKitty", characters.get(0).getName());
            assertEquals(new Position(340, 127), characters.get(0).getPosition());
        }

        @Test
        void testCharactersWithinCityBounds() {
            for (CharacterModel character : characters) {
                Position position = character.getPosition();
                assertTrue(position.getX() >= 0 && position.getX() < city.getWidth(),
                        character.getName() + " is out of city bounds on X-axis");
                assertTrue(position.getY() >= 0 && position.getY() < city.getHeight(),
                        character.getName() + " is out of city bounds on Y-axis");
            }
        }

        @Test
        void testCharacterPositionUpdate() {
            CharacterModel helloKitty = characters.get(0);
            Position newPosition = new Position(400, 200);
            helloKitty.setPosition(newPosition);

            assertEquals(newPosition, helloKitty.getPosition());
        }
}
