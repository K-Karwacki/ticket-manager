package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.models.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserManagementServiceImpl implements UserManagementService {
    private final RepositoryService repositoryService;
    private final AuthorizationService authorizationService;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    private final UserListModel userListModel;

    public UserManagementServiceImpl(){
        repositoryService = null;
        authorizationService = null;
        authenticationService = null;
        userRepository = null;
        userListModel = null;
    }

    public UserManagementServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService, AuthenticationService authenticationService){
        this.repositoryService = repositoryService;
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
        userRepository = this.repositoryService.getRepository(UserRepository.class);
        userListModel = new UserListModel();

        initialize();
//        System.out.println(userListModel.getUsersObservable().size());
    }

    private void initialize(){
        List<User> users = userRepository.getAll().stream().toList();
        System.out.println(users.size());
        Set<UserModel> userModels = new HashSet<>();
        users.forEach(user ->
        {
            UserModel userModel = new UserModel(user);
            System.out.println(userModel.getName());
            userModels.add(userModel);
        });
        userListModel.setUsers(userModels);
    }

    @Override
    public UserListModel getUserListModel() {
        return userListModel;
    }

    @Override public boolean deleteUser(UserModel userModel)
    {
        return false;
    }

    @Override public boolean updateUser(UserModel userModel)
    {
        return false;
    }

    @Override public void addUser(UserModel savedUser)
    {
        userListModel.addUserModel(savedUser);
    }

}
