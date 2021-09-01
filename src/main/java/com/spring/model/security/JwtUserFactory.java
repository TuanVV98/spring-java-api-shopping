package com.spring.model.security;

import com.spring.enumeration.RoleEnum;
import com.spring.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUserFactory {

    public static JwtUserDetailsImpl create(User user) {
//        System.out.println("user :"+user.getUsername());
        return new JwtUserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), createGrantedAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> createGrantedAuthorities(RoleEnum role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}
