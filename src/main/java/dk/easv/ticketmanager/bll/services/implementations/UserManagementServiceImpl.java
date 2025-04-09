package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.models.lists.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.ImageConverter;
import dk.easv.ticketmanager.utils.PasswordHasher;
import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserManagementServiceImpl implements UserManagementService {
    private final RepositoryService repositoryService;
    private final AuthorizationService authorizationService;
    private final UserRepository userRepository;

    private final UserListModel userListModel;

    public UserManagementServiceImpl(){
        repositoryService = null;
        authorizationService = null;
        userRepository = null;
        userListModel = null;
    }

    public UserManagementServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
        this.repositoryService = repositoryService;
        this.authorizationService = authorizationService;
        userRepository = this.repositoryService.getRepository(UserRepository.class);
        userListModel = new UserListModel();

        initialize();
//        System.out.println(userListModel.getUsersObservable().size());
    }

    private void initialize(){
        List<User> fetchedUsers = userRepository.getAll().stream().toList();
        System.out.println(fetchedUsers.size());
        Set<UserModel> userModels = new HashSet<>();
        fetchedUsers.forEach(user ->
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

    @Override public UserModel registerNewUser(String firstName, String lastName, long role_id, String email, String phone, String password, Image image)
    {
        if(userRepository == null){
            throw new RuntimeException("Repositories failed");
        }
        System.out.println("try register");

        Optional<Role> roleOptional = repositoryService.getRepository(
            AuthRepository.class).getById(role_id);

        if(roleOptional.isEmpty()){
            System.out.println("role cannot be null");
            return null;
        }

        System.out.println("found role");

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setRole(roleOptional.get());
        newUser.setEmail(email);
        newUser.setPhoneNumber(phone);
        newUser.setHashedPassword(PasswordHasher.hashPassword(password));
        if(image != null) {
            newUser.setImageData(ImageConverter.convertToByteArray(image));
        }
        User savedUser = userRepository.save(newUser);
        if(savedUser != null){
            UserModel userDTO = new UserModel(savedUser);
            userListModel.addUserModel(userDTO);
            return userDTO;
        }

        return null;
    }

    @Override public boolean deleteUser(UserModel userModel)
    {
        userListModel.deleteUserModel(userModel);
        Optional<User> user = userRepository.getById(userModel.getID());
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }
        else{
            return false;
        }

    }

    @Override public boolean updateUser(UserModel userModel)
    {
        userListModel.updateUser(userModel);
        Optional<User> optionalUser = userRepository.getById(userModel.getID());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setFirstName(userModel.getName());
            user.setLastName(userModel.getLastName());
            user.setEmail(userModel.getEmail());
            user.setPhoneNumber(userModel.getPhoneNumber());
            user.setRole(userModel.getRole());
            user.setImageData(ImageConverter.convertToByteArray(userModel.getImage()));
            user.setHashedPassword(PasswordHasher.hashPassword(userModel.getPassword()));
            return userRepository.update(user) != null;
        }
        return false;
    }


}
