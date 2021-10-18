package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    private TripService tripService;


    @Test
    void shouldThrowAnException() {
        User loggedUser = null;
        tripService = new MyTripService(loggedUser);
        User user = new User();
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }

    @Test
    void shoultReturnEmptyTripList_whenUserHasNoFriend() {
        // Given
        User loggedUser = new User();
        tripService = new MyTripService(loggedUser);
        User userWithNoFriends = new User();
        // When
        List<Trip> trips = tripService.getTripsByUser(userWithNoFriends);
        // Then
        assertTrue(trips.isEmpty());
    }


    private static class MyTripService extends TripService {
        private final User loggedUser;

        public MyTripService(User loggedUser) {
            this.loggedUser = loggedUser;
        }

        @Override
        User getLoggedUser() {
            return loggedUser;
        }
    }

}
