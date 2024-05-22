
package com.example.mindboost.Security.Service;

import com.example.mindboost.DTOs.UserDTO;
import com.example.mindboost.Security.entities.AppUser;
import com.example.mindboost.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
private AccountService accountService;
private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.LoadUserByUserName(username);
        if(userDTO == null) throw new UsernameNotFoundException("User not found");
        UserDetails userDetails= User
                .withUsername(userDTO.getUserName())
                .password(userDTO.getPassword())
                .roles(userDTO.getRole())
                .build();
        return userDetails;
    }


/*
    @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.LoadUserByUserName(username);
        if(appUser == null) throw new UsernameNotFoundException("User not found");
        String[] roles = appUser.getRoles().stream().map(role -> role.getRole()).toArray(String[]::new);
        UserDetails userDetails= User
                .withUsername(appUser.getUserName())
                .password(appUser.getPassword())
                .roles(roles).build();
        return userDetails;
    }*/

}

