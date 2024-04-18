package com.subham.ATM.model;


import com.subham.ATM.utils.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name ="customers")
public class AccountHolder  implements UserDetails {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;



    private String  name;

    private String firstname;

    private String  lastname;

    private  Long phoneNumber;

    private String pin;


    @Enumerated(EnumType.STRING)
    private Role role;



   @OneToOne(mappedBy = "accountHolder", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Balance balance;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return name;
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


}
