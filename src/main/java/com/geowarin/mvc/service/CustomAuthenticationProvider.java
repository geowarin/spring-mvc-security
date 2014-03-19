package com.geowarin.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Authentication service using a hash map to store the users.
 *
 * Date: 4/7/13
 * Time: 10:08 PM
 *
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private final Map<String, String> usersAndPasswords = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsers() {
        // this will add a user and encode its password to md5
        // Spring StandardPasswordEncoder will add salt by default
        usersAndPasswords.put("user", passwordEncoder.encode("user"));
        usersAndPasswords.put("admin", passwordEncoder.encode("admin"));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // Do nothing
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        log.debug("Authentication = {}", authentication);

        if (!usersAndPasswords.containsKey(username)) {
            throw new UsernameNotFoundException("User name not found : " + username);
        }

        String password = authentication.getCredentials().toString();
        String encodedPassword = usersAndPasswords.get(username);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new BadCredentialsException("Wrong password");
        }
        return new User(username, password, createAuthorities("role1", "role2"));
    }

    private Collection<? extends GrantedAuthority> createAuthorities(String... roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
