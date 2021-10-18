package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @BeforeEach
    void setup() {
        tripService = new TripService(userRepository, tripRepository);
    }


    @Test
    void throwsAnException_whenLoggedUserIsNull() {
        // Given
        doReturn(Optional.empty()).when(userRepository).getSessionUser();
        User user = new User();
        // When Then
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }

    @Nested
    @DisplayName("Given a logged in user")
    class LoggedInUser {
        private User loggedUser;

        @BeforeEach
        void setUp() {
            loggedUser = new User();
            doReturn(Optional.of(loggedUser)).when(userRepository).getSessionUser();
        }

        @Test
        void returnEmptyTripList_whenUserHasNoFriend() {
            // Given
            User userWithNoFriends = new User();
            // When
            List<Trip> trips = tripService.getTripsByUser(userWithNoFriends);
            // Then
            assertThat(trips).isEmpty();
        }

        @Test
        void returnTripListOfUser_whenUserHasAFriendWhichIsLoggedUser() {
            // Given
            User user = userWithFriend(loggedUser);
            Trip trip = saveTrip(user, new Trip());
            // When
            List<Trip> trips = tripService.getTripsByUser(user);
            // Then
            assertThat(trips).containsExactly(trip);
        }

        private Trip saveTrip(User user, Trip trip) {
            doReturn(singletonList(trip)).when(tripRepository).getUserTrips(user);
            return trip;
        }
    }


    private User userWithFriend(User loggedUser) {
        User user = new User();
        user.addFriend(loggedUser);
        return user;
    }

}
