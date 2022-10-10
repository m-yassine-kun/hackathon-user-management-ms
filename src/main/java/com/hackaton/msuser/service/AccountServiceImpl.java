package com.hackaton.msuser.service;

import com.hackaton.msuser.dao.AppRoleRepository;
import com.hackaton.msuser.dao.AppUserRepository;
import com.hackaton.msuser.entities.AppRole;
import com.hackaton.msuser.entities.AppUser;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
        AppUser user= appUserRepository.findByUsername(username);
        if (user!=null) throw new RuntimeException("User already exists!");
        if (!password.equals(confirmedPassword)) throw new RuntimeException("Passwords doesn't match!");
        AppUser appUser=new AppUser();
        appUser.setUsername(username);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(username,"USER");
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser= appUserRepository.findByUsername(username);
        AppRole appRole= appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }
}
