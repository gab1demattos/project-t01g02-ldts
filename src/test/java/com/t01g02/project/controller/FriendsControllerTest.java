package com.t01g02.project.controller;

import com.t01g02.project.menu.SettingsModel;
import com.t01g02.project.menu.SoundTest;
import com.t01g02.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;


import java.util.ArrayList;
import java.util.List;

import static com.t01g02.project.controller.FriendsController.cityModel;
import static com.t01g02.project.model.CharacterModel.friends;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class FriendsControllerTest {
    private CityModel mockCityModel;
    private Position position;
    private CharacterModel friend;
    private SettingsModel settingsModel;

    @BeforeEach
    void setUp() {
        mockCityModel = mock(CityModel.class);
        cityModel = mockCityModel;
        settingsModel = mock(SettingsModel.class);
        friend = mock(CharacterModel.class);
        position = new Position(10, 20);
        when(friend.getPosition()).thenReturn(position);

        CharacterModel.friends.clear();
    }

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

    @Test
    void testCheckPickup_FriendAlreadyFollowing() {
        Zone pickupZone = mock(Zone.class);
        Position kittyPosition = new Position(5, 5);
        CharacterModel friend = mock(CharacterModel.class);

        when(pickupZone.getType()).thenReturn(Tile.Type.PICKUP);
        when(pickupZone.isWithin(kittyPosition)).thenReturn(true);
        when(pickupZone.getAssociatedFriend()).thenReturn(friend);
        when(friend.isFollowing()).thenReturn(true);

        CharacterModel.hellokitty = mock(CharacterModel.class);
        when(CharacterModel.hellokitty.getPosition()).thenReturn(kittyPosition);
        CharacterModel.friends.add(friend);

        CityModel mockCityModel = mock(CityModel.class);
        when(mockCityModel.getZones()).thenReturn(List.of(pickupZone));

        FriendsController controller = new FriendsController(mockCityModel, null, null);
        controller.checkPickup();

        verify(friend, never()).setFollowing(true);
    }

    @Test
    void testCheckDropoff_NotInDropoffZone() {
        Zone nonDropoffZone = mock(Zone.class);
        when(nonDropoffZone.getType()).thenReturn(Tile.Type.PICKUP);

        CharacterModel friend = mock(CharacterModel.class);
        when(friend.isFollowing()).thenReturn(true);

        CharacterModel.friends.add(friend);

        CityModel mockCityModel = mock(CityModel.class);
        when(mockCityModel.getZones()).thenReturn(List.of(nonDropoffZone));

        FriendsController controller = new FriendsController(mockCityModel, null, null);
        controller.checkDropoff();

        verify(friend, never()).setFollowing(false);
    }

    @Test
    void testUpdateFriendsPosition_FriendLeavesHouse() {
        Zone zone1 = mock(Zone.class);

        CharacterModel friend = mock(CharacterModel.class);
        when(friend.isFollowing()).thenReturn(true);
        when(friend.isOutOfHouse()).thenReturn(false);
        Position mockPosition = new Position(10, 20);
        when(friend.getPosition()).thenReturn(mockPosition);

        CharacterModel hellokitty = mock(CharacterModel.class);
        Position kittyPosition = new Position(20, 20);
        when(hellokitty.getPosition()).thenReturn(kittyPosition);
        when(zone1.isWithin(kittyPosition)).thenReturn(false);

        CharacterModel.friends.add(friend);
        CityModel mockCityModel = mock(CityModel.class);

        List<Zone> mockZones = new ArrayList<>();
        mockZones.add(zone1);
        when(mockCityModel.getZones()).thenReturn(mockZones);
        when(zone1.isWithin(friend.getPosition())).thenReturn(true);

        FriendsController controller = new FriendsController(mockCityModel, null, null);
        controller.updateFriendsPosition();

        verify(friend).setOutOfHouse(true);
    }
    @Test
    void testUpdateFriendsPosition_FriendEntersHouse() {
        CharacterModel friend = mock(CharacterModel.class);
        when(friend.isFollowing()).thenReturn(false);
        when(friend.isOutOfHouse()).thenReturn(true);
        when(friend.isInParty()).thenReturn(false);

        Position mockPosition = new Position(10, 20); // Example position
        when(friend.getPosition()).thenReturn(mockPosition);

        CharacterModel.friends.add(friend);

        FriendsController controller = new FriendsController(null, null, null);
        controller.updateFriendsPosition();

        verify(friend).setInParty(true);
    }


    @Test
    void testCheckDropoff_FriendNotInDropoffZone() {
        Zone nonDropoffZone = mock(Zone.class);
        when(nonDropoffZone.getType()).thenReturn(Tile.Type.PICKUP);

        CharacterModel friend = mock(CharacterModel.class);
        when(friend.isFollowing()).thenReturn(true);

        CharacterModel.friends.add(friend);

        CityModel mockCityModel = mock(CityModel.class);
        when(mockCityModel.getZones()).thenReturn(List.of(nonDropoffZone));

        FriendsController controller = new FriendsController(mockCityModel, null, null);
        controller.checkDropoff();

        verify(friend, never()).setFollowing(false);  // Ensure the friend isn't dropped off.
    }
    @Test
    void testAreAllFriendsInParty() {
        CharacterModel friend1 = mock(CharacterModel.class);
        when(friend1.isInParty()).thenReturn(true);

        CharacterModel friend2 = mock(CharacterModel.class);
        when(friend2.isInParty()).thenReturn(false);  // This one is not in the party.

        friends.clear();
        friends.add(friend1);
        friends.add(friend2);

        FriendsController controller = new FriendsController(null, null, null);
        boolean result = controller.areAllFriendsInParty();

        assertFalse(result);  // Should return false since not all friends are in the party.
    }


    @Test
    void testObserverNotifiedOnPickup() {
        KittyObserver observer = mock(KittyObserver.class);
        FriendsController controller = new FriendsController(null, null, null);
        controller.addObserver(observer);

        controller.notifyPickedUp();

        verify(observer).friendPickedUp();
    }

    @Test
    void testObserverNotifiedOnDropoff() {
        KittyObserver observer = mock(KittyObserver.class);
        FriendsController controller = new FriendsController(null, null, null);
        controller.addObserver(observer);

        controller.notifyDroppedOff();

        verify(observer).friendDroppedOff();
    }


}
