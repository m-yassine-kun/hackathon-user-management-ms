package com.hackaton.msuser.service;

import com.hackaton.msuser.entities.AppRole;
import com.hackaton.msuser.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(String username, String password,String confirmedPassword);
    public AppRole saveRole(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);

}
