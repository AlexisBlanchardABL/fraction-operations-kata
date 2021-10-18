package org.craftedsw.tripservicekata.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getSessionUser();
}
