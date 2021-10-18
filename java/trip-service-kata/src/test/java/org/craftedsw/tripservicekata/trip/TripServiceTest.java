package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    private TripService tripService;

    @Mock
    private User user;

    @BeforeEach
    void setup() {
        tripService = new MyTripService();
    }

    @Test
    void shouldThrowAnException() {
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(user));
    }


    private static class MyTripService extends TripService {
        @Override
        User getLoggedUser() {
            return null;
        }
    }

}
