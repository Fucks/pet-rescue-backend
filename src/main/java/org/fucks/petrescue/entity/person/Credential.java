/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fucks.petrescue.entity.person;

import lombok.*;
import org.fucks.petrescue.entity.AbstractEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author fucks
 */
@Document
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Credential extends AbstractEntity implements UserDetails {
    
    @Getter
    @Setter
    @Field
    @Indexed
    private String username;
    
    @Getter
    @Setter
    @Field
    @Indexed
    private String password;
    
    @Getter
    @Setter
    @Field
    @Indexed
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static Optional<Credential> getLogged(){
        return Optional.of((Credential)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
