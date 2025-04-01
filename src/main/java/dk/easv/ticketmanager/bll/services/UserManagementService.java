package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.ObservableSet;

import java.util.List;

public interface UserManagementService
{
  List<UserModel> getUserModelList();
  ObservableSet<UserModel> getUserModelObservable();
  void addUser(User user);

}
