package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.gui.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User>{
    List<User> getUsersByRoleName(String roleName);
    Optional<User> findUserByEmail(String email);
}
