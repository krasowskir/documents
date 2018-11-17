package com.example.documents.service;

import com.example.documents.model.Identity;
import com.example.documents.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Identity saveUser(Identity source){
        Identity toPersist = new Identity();
        toPersist.setUsername(source.getUsername());
        toPersist.setPassword(passwordEncoder.encode(source.getPassword()));
        toPersist.setAccount(source.getAccount());

        return identityRepository.save(toPersist);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Identity optionalUser = identityRepository.findById(s).get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (optionalUser.getUsername().equals("richard")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (optionalUser.getUsername().equals("toni")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new org.springframework.security.core.userdetails.User(optionalUser.getUsername(), optionalUser.getPassword(),authorities);
    }

    public List<Identity> findAllUsers(){
        List<Identity> users = new ArrayList<>();
        Iterable<Identity> iterator =  identityRepository.findAll();
        iterator.forEach(identity -> {
            users.add(identity);
        });
        return users;
    }
}
