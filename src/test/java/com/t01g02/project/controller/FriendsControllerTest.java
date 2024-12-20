package com.t01g02.project.controller;

import com.t01g02.project.model.CharacterModel;
import com.t01g02.project.model.Position;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;


import java.util.ArrayList;
import java.util.List;

import static com.t01g02.project.model.CharacterModel.friends;
import static org.mockito.Mockito.*;

public class FriendsControllerTest {
    @Test
    void testMoveFollowingCharacters() {
        CharacterModel mockedHelloKitty = mock(CharacterModel.class);
        Position helloKittyLastPosition = new Position(100, 200);
        List<Position> kittyPositionHistory = new ArrayList<>();
        kittyPositionHistory.add(helloKittyLastPosition);
        when(mockedHelloKitty.getKittyLastPositions()).thenReturn(kittyPositionHistory);
        CharacterModel.hellokitty = mockedHelloKitty;

        CharacterModel friend1 = mock(CharacterModel.class);
        when(friend1.isFollowing()).thenReturn(true);
        when(friend1.isOutOfHouse()).thenReturn(true);

        CharacterModel friend2 = mock(CharacterModel.class);
        when(friend2.isFollowing()).thenReturn(false);
        when(friend2.isOutOfHouse()).thenReturn(true);

        CharacterModel friend3 = mock(CharacterModel.class);
        when(friend3.isFollowing()).thenReturn(true);
        when(friend3.isOutOfHouse()).thenReturn(false);

        friends.add(friend1);
        friends.add(friend2);
        friends.add(friend3);

        FriendsController controller = new FriendsController(null, null, null);

        controller.moveFollowingCharacters();

        verify(friend1).setPosition(helloKittyLastPosition);
        verify(friend2, never()).setPosition(any());
        verify(friend3, never()).setPosition(any());
    }

    @Property
    void followChangesValidFriendsState(@ForAll("characterStates") boolean isFollowing,
                                        @ForAll("characterStates") boolean isOutOfHouse) {

        CharacterModel mockedHelloKitty = mock(CharacterModel.class);
        Position helloKittyLastPosition = new Position(100, 200);
        List<Position> kittyPositionHistory = new ArrayList<>();
        kittyPositionHistory.add(helloKittyLastPosition);
        when(mockedHelloKitty.getKittyLastPositions()).thenReturn(kittyPositionHistory);
        CharacterModel.hellokitty = mockedHelloKitty;

        CharacterModel friend = mock(CharacterModel.class);
        when(friend.isFollowing()).thenReturn(isFollowing);
        when(friend.isOutOfHouse()).thenReturn(isOutOfHouse);

        friends.clear();
        friends.add(friend);

        FriendsController controller = new FriendsController(null, null, null);

        controller.moveFollowingCharacters();

        if (isFollowing && isOutOfHouse) {
            verify(friend).setPosition(helloKittyLastPosition);
        } else {
            verify(friend, never()).setPosition(any());
        }
    }

    @Provide
    public Arbitrary<Boolean> characterStates() {
        return Arbitraries.of(true, false);
    }

    @Property
    void testFriendFollowKittyPosition(
            @ForAll("characterStates") boolean isFollowing,
            @ForAll("characterStates") boolean isOutOfHouse,
            @ForAll int positionHistoryLength) {

        CharacterModel mockedHelloKitty = mock(CharacterModel.class);

        List<Position> kittyPositionHistory = new ArrayList<>();
        for (int i = 0; i < Math.abs(positionHistoryLength % 5) + 1; i++) {
            kittyPositionHistory.add(new Position(100 + i, 200 + i));
        }
        when(mockedHelloKitty.getKittyLastPositions()).thenReturn(kittyPositionHistory);
        CharacterModel.hellokitty = mockedHelloKitty;

        CharacterModel friend = mock(CharacterModel.class);
        when(friend.isFollowing()).thenReturn(isFollowing);
        when(friend.isOutOfHouse()).thenReturn(isOutOfHouse);

        friends.clear();
        friends.add(friend);

        FriendsController controller = new FriendsController(null, null, null);

        controller.moveFollowingCharacters();

        if (isFollowing && isOutOfHouse) {
            verify(friend).setPosition(kittyPositionHistory.getFirst());
        } else {
            verify(friend, never()).setPosition(any());
        }
    }

}
