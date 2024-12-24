package com.t01g02.project.controller;

import com.t01g02.project.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScoreControllerTest {
    private Score score;
    private ScoreController scoreController;

    @BeforeEach
    void setUp(){
        score = mock(Score.class);
        scoreController = new ScoreController(score);
    }

    @Test
    void testIncrementScore_WhenStarNotPickedUp() {
        when(score.getScore()).thenReturn(100);

        scoreController.incrementScore(10);

        verify(score).setScore(110);
    }

    @Test
    void testIncrementScore_WhenStarPickedUp() {
        when(score.getScore()).thenReturn(100);

        scoreController.pickedStar();
        scoreController.incrementScore(10);

        verify(score).setScore(200);
    }

    @Test
    void testFriendPickedUp_WhenStarNotPickedUp() {
        when(score.getScore()).thenReturn(0);

        scoreController.friendPickedUp();

        verify(score).setScore(50);
    }

    @Test
    void testFriendDroppedOff_WhenStarPickedUp() {
        when(score.getScore()).thenReturn(300);

        scoreController.pickedStar();
        scoreController.friendDroppedOff();

        verify(score).setScore(600);
    }

    @Test
    void testPickedStar() {
        when(score.getScore()).thenReturn(500);

        scoreController.pickedStar();
        verify(score).setScore(1000);
    }

    @Test
    void testFriendPickedUp_StarNotPickedUp_ScoreZero() {
        when(score.getScore()).thenReturn(0);

        scoreController.friendPickedUp();

        verify(score).setScore(50);
    }

    @Test
    void testFriendPickedUp_StarNotPickedUp_ScoreGreaterThanZero() {
        when(score.getScore()).thenReturn(10);

        scoreController.friendPickedUp();

        verify(score).setScore(60);
    }

    @Test
    void testFriendDroppedOff_StarNotPickedUp_ScoreZero() {
        when(score.getScore()).thenReturn(0);

        scoreController.friendDroppedOff();

        verify(score).setScore(50);
    }

    @Test
    void testFriendDroppedOff_StarNotPickedUp_ScoreNotZero() {
        when(score.getScore()).thenReturn(10);

        scoreController.friendDroppedOff();

        verify(score).setScore(60);
    }


    @Test
    void testPickedStar_AfterFriendPickedUp() {
        when(score.getScore()).thenReturn(200);

        scoreController.pickedStar();

        verify(score).setScore(400);
    }


    @Test
    void testGetScore() {
        when(score.getScore()).thenReturn(0);

        int result = scoreController.getScore();

        assertEquals(0, result);
    }
}
