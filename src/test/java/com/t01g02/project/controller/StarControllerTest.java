package com.t01g02.project.controller;

import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.CityModel;
import com.t01g02.project.model.PopUpsModel;
import com.t01g02.project.model.Position;
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

        // Set up mock city dimensions
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
    public void testSetPositionCalled() {
        Position initialPosition = star.getPosition();

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
    public void testRandomDirectionChangeAfterMultipleSteps() {
        Position initialPosition = new Position(star.getPosition().getX(), star.getPosition().getY());

        for (int i = 0; i < 50; i++) {
            starController.moveStar();
        }

        Position newPosition = star.getPosition();

        assertNotEquals(initialPosition, newPosition);
    }

    @Test
    public void testStarDeletedAfterPickedUp() {
        when(hellokitty.getPosition()).thenReturn(new Position(50, 50));
        when(star.getPosition()).thenReturn(new Position(50, 50));

        // Simulate star being picked up
        starController = spy(new StarController(city, star, hellokitty));
        starController.moveStar();

        assertTrue(starController.starPickedUp);

        // Move the star and verify it doesn't move after being picked up
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
}