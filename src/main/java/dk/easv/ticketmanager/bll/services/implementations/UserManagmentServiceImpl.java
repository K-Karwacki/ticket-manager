package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagmentService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserManagmentServiceImpl implements UserManagmentService {
    private final RepositoryService repositoryService;
    private final AuthorizationService authorizationService;

    private final UserListModel userListModel = new UserListModel();

    public UserManagmentServiceImpl(){
        repositoryService = null;
        authorizationService = null;
    }

    public UserManagmentServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
        this.repositoryService = repositoryService;
        this.authorizationService = authorizationService;

        setUserListModel();
    }

    @Override
    public UserListModel getUserListModel() {
        return userListModel;
    }

    @Override
    public void setUserListModel(){
        List<User> users = repositoryService.getRepository(UserRepository.class).getAll();
        List<UserModel> userModels = new ArrayList<>();
        users.forEach(user ->
        {
            UserModel userModel = new UserModel(user.getID(), user.getRole());
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setEmail(user.getEmail());
            userModel.setPhoneNumber(user.getPhoneNumber());
            userModels.add(userModel);
            userModels.add(userModel);
        });
        userListModel.setUsers(userModels);
    }
}
