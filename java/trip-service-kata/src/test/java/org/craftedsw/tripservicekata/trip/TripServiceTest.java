package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    private TripService tripService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private TripRepository tripRepository;


    @Test
    void throwsAnException_whenLoggedUserIsNull() {
        // Given
        User loggedUser = null;
        givenLoggedUser(loggedUser);
        tripService = new TripService(userRepository, tripRepository);
        User user = new User();
        // When Then
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }

    @Test
    void returnEmptyTripList_whenUserHasNoFriend() {
        // Given
        givenLoggedUser(new User());
        tripService = new TripService(userRepository, tripRepository);
        User userWithNoFriends = new User();
        // When
        List<Trip> trips = tripService.getTripsByUser(userWithNoFriends);
        // Then
        assertThat(trips).isEmpty();
    }

    @Test
    void returnTripListOfUser_whenUserHasAFriendWhichIsLoggedUser() {
        // Given
        User user = new User();
        User loggedUser = new User();
        user.addFriend(loggedUser);
        Trip trip = new Trip();
        givenTripsForUser(trip, user);
        givenLoggedUser(loggedUser);
        tripService = new TripService(userRepository, tripRepository);
        // When
        List<Trip> trips = tripService.getTripsByUser(user);
        // Then
        assertThat(trips).containsExactly(trip);
    }

    private void givenTripsForUser(Trip trip, User user) {
        doReturn(singletonList(trip)).when(tripRepository).getUserTrips(user);
    }

    private void givenLoggedUser(User loggedUser) {
        doReturn(Optional.ofNullable(loggedUser)).when(userRepository).getSessionUser();
    }

}
