package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserRepository;


public class TripService {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    public TripService(UserRepository userRepository, TripRepository tripRepository) {
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = userRepository.getSessionUser().orElseThrow(UserNotLoggedInException::new);
        if (user.hasFriend(loggedUser)) {
            return tripRepository.getUserTrips(user);
        }
        return Collections.emptyList();
    }

}
