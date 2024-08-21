package com.example.mindboost.Security.Service;

import com.example.mindboost.Security.entities.AppRole;
import com.example.mindboost.Security.entities.AppUser;

public interface AccountService {
    AppUser AddUser(String userName, String password, String email,String confirmedPassword); // i can use it as sign up
    AppUser LoadUserByUserName(String userName);
    void AddRoleToUser(String userName, String roleName);
    AppRole AddRole(String role);
    void removeRoleFromUser(String userName, String roleName);
}
