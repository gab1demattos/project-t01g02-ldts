package com.t01g02.project.controller;

import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.PopUpsModel;
import com.t01g02.project.model.Position;
import com.t01g02.project.viewer.Sprite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StarControllerTest {
    @Mock
    private CityModel city;

    @Mock
    private PopUpsModel star;

    @Mock
    private CharacterModel hellokitty;

    private StarController starController;
    private Position starPosition;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        when(city.getWidth()).thenReturn(100);
        when(city.getHeight()).thenReturn(100);

        // Create a real position object for the star
        starPosition = new Position(50, 50);
        when(star.getPosition()).thenReturn(starPosition);
        when(hellokitty.getPosition()).thenReturn(starPosition);

        starController = new StarController(city, star, hellokitty);

        doAnswer(invocation -> {
            Position newPosition = invocation.getArgument(0);
            when(star.getPosition()).thenReturn(newPosition);
            return null;
        }).when(star).setPosition(any(Position.class));

    }

    @Test
    void testMoveStarWhenStarPickedUp() {
        starController.starPickedUp = true;
        starController.moveStar();

        verify(star, never()).setPosition(any(Position.class));
    }
    @Test
    void testMoveStarWhenNotPickedUp() {
        starController.starPickedUp = false;
        starController.moveStar();

        verify(star, times(1)).setPosition(any(Position.class));
    }

    @Test
    public void testMoveStar_BounceOffHorizontalBoundary() {
        starPosition.setX(75);
        starPosition.setY(50);

        starController.moveStar();
        System.out.println(star.getPosition().getX());
        assertTrue(star.getPosition().getX() < 75);
    }

    @Test
    public void testMoveStar_BounceOffVerticalBoundary() {
        starPosition.setX(50);
        starPosition.setY(80);

        starController.moveStar();
        assertTrue(star.getPosition().getY() < 80);
    }

    @Test
    public void testMoveStar_AtTopLeftCorner() {
        starPosition.setX(0);
        starPosition.setY(0);

        starController.moveStar();

        assertTrue(star.getPosition().getX() >= 0 && star.getPosition().getY() >= 0);
    }
    @Test
    public void testMoveStar_AtBottomRightCorner() {
        starPosition.setX(city.getWidth() - 26);
        starPosition.setY(city.getHeight() - 21);

        starController.moveStar();


        assertTrue(star.getPosition().getX() < city.getWidth() && star.getPosition().getY() < city.getHeight());
    }

    @Test
    void testStarMovementInNegativeDirection() {
        CityModel city = new CityModel(1000, 1000);
        Position initialPosition = new Position(10, 10);
        PopUpsModel star = new PopUpsModel(null, initialPosition, null);
        CharacterModel hellokitty = new CharacterModel(null, new Position(20, 20), "HelloKitty");

        StarController starController = new StarController(city, star, hellokitty);

        starController.dx = -1;
        starController.dy = -1;

        Position beforeMove = star.getPosition();
        starController.moveStar();
        Position afterMove = star.getPosition();

        assertNotEquals(beforeMove, afterMove);
        assertTrue(afterMove.getX() < beforeMove.getX());
        assertTrue(afterMove.getY() < beforeMove.getY());
    }

    @Test
    public void testSetPositionCalled() {

        starController.moveStar();

        verify(star, times(1)).setPosition(any(Position.class));
    }

    @Test
    public void testStarPickedUpByHelloKitty() {
        Position helloKittyPosition = new Position(50, 50);
        Position starPosition = new Position(50, 50);

        // Mock Hello Kitty and Star's positions
        when(hellokitty.getPosition()).thenReturn(helloKittyPosition);
        when(star.getPosition()).thenReturn(starPosition);

        starController = spy(new StarController(city, star, hellokitty));

        starController.moveStar();

        assertTrue(starController.starPickedUp);
    }

    @Test
    public void testRandomDirectionChangeAfterSteps() {
        starController.moveStar();
        for (int i = 0; i < 50; i++) {
            starController.moveStar();
        }


        Position newPosition = star.getPosition();
        assertNotEquals(new Position(50, 50), newPosition);
    }

    @Test
    public void testStarDeletedAfterPickedUp() {
        when(hellokitty.getPosition()).thenReturn(new Position(50, 50));
        when(star.getPosition()).thenReturn(new Position(50, 50));

        starController = spy(new StarController(city, star, hellokitty));
        starController.moveStar();

        assertTrue(starController.starPickedUp);

        Position initialPosition = new Position(star.getPosition().getX(), star.getPosition().getY());
        starController.moveStar();

        Position newPosition = star.getPosition();
        assertEquals(initialPosition, newPosition);
    }

    @Test
    void testMoveStarDoesNotExceedBounds() {
        Position nearRightEdge = new Position(90, 50); // Near the right boundary
        Position nearTopEdge = new Position(50, 90); // Near the top boundary

        when(star.getPosition()).thenReturn(nearRightEdge);
        starController.moveStar();

        assertTrue(star.getPosition().getX() < city.getWidth());

        when(star.getPosition()).thenReturn(nearTopEdge);
        starController.moveStar();

        assertTrue(star.getPosition().getY() < city.getHeight());
    }
    @Test
    void testStarDoesNotMoveAfterPickedUp() {
        CityModel city = new CityModel(1000, 1000);
        Position initialPosition = new Position(10, 10);
        PopUpsModel star = new PopUpsModel(null, initialPosition, null);
        CharacterModel hellokitty = new CharacterModel(null, new Position(20, 20), "HelloKitty");

        StarController starController = new StarController(city, star, hellokitty);

        star.setPosition(new Position(hellokitty.getPosition().getX(), hellokitty.getPosition().getY()));
        starController.moveStar();
        assertTrue(starController.starPickedUp); // Star should be picked up

        Position beforeMove = star.getPosition();
        starController.moveStar();
        Position afterMove = star.getPosition();

        assertEquals(beforeMove, afterMove);
    }
}