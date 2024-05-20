package com.example.mindboost.Security.Service;

import com.example.mindboost.Security.Repositories.AppRoleRepo;
import com.example.mindboost.Security.Repositories.AppUserRepo;
import com.example.mindboost.Security.entities.AppRole;
import com.example.mindboost.Security.entities.AppUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepo appUserRepo;
    private AppRoleRepo appRoleRepo;
   // private PasswordEncoder passwordEncoder;
    @Override
    public AppUser AddUser(String userName, String password, String email, String confirmedPassword) {
        AppUser appUser=appUserRepo.findByUserName(userName);
        if (appUser!=null) throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        appUser=AppUser.builder()
                .userName(userName)
                .email(email)
               // .Password(passwordEncoder.encode(password))
                .build();
        appUserRepo.save(appUser);
        return appUser;
    }

    @Override
    public AppUser LoadUserByUserName(String userName) {
        return appUserRepo.findByUserName(userName);
    }

    @Override
    public void AddRoleToUser(String userName, String role) {
        AppUser appUser=appUserRepo.findByUserName(userName);
        AppRole appRole=appRoleRepo.findById(role).get();
        appUser.getRoles().add(appRole);

    }

    @Override
    public AppRole AddRole(String role) {
        AppRole appRole=appRoleRepo.findByRole(role);
        if (appRole!=null) throw new RuntimeException("Role already exists");
        appRole=AppRole.builder().role(role).build();
        return appRoleRepo.save(appRole);
    }

    @Override
    public void removeRoleFromUser(String userName, String role) {
        AppUser appUser=appUserRepo.findByUserName(userName);
        AppRole appRole=appRoleRepo.findById(role).get();
        appUser.getRoles().remove(appRole);
    }
}
