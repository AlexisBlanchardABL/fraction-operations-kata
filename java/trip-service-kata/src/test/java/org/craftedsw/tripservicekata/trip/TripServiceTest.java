package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    private TripService tripService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private TripRepository tripRepository;


    @Test
    void throwsAnException_whenLoggedUserIsNull() {
        // Given
        User loggedUser = null;
        doReturn(loggedUser).when(userRepository).getSessionUser();
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
        User loggedUser = new User();
        doReturn(loggedUser).when(userRepository).getSessionUser();
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
        User loggedUser = new User();
        Trip trip = new Trip();
        User user = new User();
        user.addFriend(loggedUser);

        doReturn(singletonList(trip)).when(tripRepository).getUserTrips(user);
        doReturn(loggedUser).when(userRepository).getSessionUser();
        tripService = new TripService(userRepository, tripRepository);
        // When
        List<Trip> trips = tripService.getTripsByUser(user);
        // Then
        assertThat(trips).containsExactly(trip);
    }

}
