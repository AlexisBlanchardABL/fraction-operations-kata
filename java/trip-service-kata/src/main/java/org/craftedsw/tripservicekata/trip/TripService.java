package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
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
        List<Trip> tripList = new ArrayList<>();
        User loggedUser = getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = getUserTrips(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    List<Trip> getUserTrips(User user) {
        return tripRepository.getUserTrips(user);
    }

    User getLoggedUser() {
        return userRepository.getSessionUser();
    }

}
