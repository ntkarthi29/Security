package com.basic.base.model;
import com.basic.base.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.basic.base.enums.AccountType;
import com.basic.base.enums.Gender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private Gender gender;
    private String phoneNumber;
    private AccountType accountType;
    private ERole roles;

    public User() {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getUsername() {
        return email;
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

    public String getUserName() {
        return userName;
    }

    public void setRole(ERole roles) {

    }
}