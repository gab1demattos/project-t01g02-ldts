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

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StarControllerTest {
    @Mock
    private CityModel city;

    @Mock
    private PopUpsModel star;

    @Mock
    private CharacterModel hellokitty;

    private StarController starController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes mocks
        when(city.getWidth()).thenReturn(100);  // Mock city width
        when(city.getHeight()).thenReturn(100);  // Mock city height
        when(star.getPosition()).thenReturn(new Position(50, 50));  // Mock star's position
        when(hellokitty.getPosition()).thenReturn(new Position(50, 50));  // Mock HelloKitty's position
        starController = new StarController(city, star);  // Instantiate the controller
        when(starController.isPosOnStar(hellokitty.getPosition())).thenReturn(true);

    }

    @Test
    public void testMoveStar_BounceOffHorizontalBoundary() {
        // Position the star near the right boundary
        when(star.getPosition()).thenReturn(new Position(98, 50));

        // Move the star and check its position after the move
        starController.moveStar();
        Position newPosition = star.getPosition();

        // Verify that the star's X-position has bounced back (decreased)
        assertTrue(newPosition.getX() < 98);
    }

    @Test
    public void testMoveStar_BounceOffVerticalBoundary() {
        // Position the star near the bottom boundary
        when(star.getPosition()).thenReturn(new Position(50, 98));

        // Move the star and check its position after the move
        starController.moveStar();
        Position newPosition = star.getPosition();

        // Verify that the star's Y-position has bounced back (decreased)
        assertTrue(newPosition.getY() < 98);
    }

    @Test
    public void testStarPickedUpByHelloKitty() {
        // Simulate that the star is at the same position as HelloKitty
        when(star.getPosition()).thenReturn(new Position(50, 50));
        when(hellokitty.getPosition()).thenReturn(new Position(50, 50));

        // Move the star and check if it gets picked up
        starController.moveStar();

        // Assert that the star is picked up
        assertTrue(starController.starPickedUp);
    }

    @Test
    public void testRandomDirectionChangeAfterSteps() {
        // Move the star and simulate 50 steps (so it should change direction randomly)
        starController.moveStar();
        for (int i = 0; i < 50; i++) {
            starController.moveStar();
        }

        // After 50 moves, verify the direction has changed (we can't predict the exact direction)
        // So we just ensure that the position has changed from the initial one.
        Position newPosition = star.getPosition();
        assertNotEquals(new Position(50, 50), newPosition);  // Ensure position isn't the same
    }

    @Test
    public void testStarDoesNotMoveWhenPickedUp() {
        // Initially move the star and it gets picked up
        starController.moveStar();
        assertTrue(starController.starPickedUp);

        // Try moving it again, but it should not move after being picked up
        starController.moveStar();

        // Ensure the position hasn't changed
        verify(star, times(1)).setPosition(any(Position.class));  // It should only be called once
    }
}