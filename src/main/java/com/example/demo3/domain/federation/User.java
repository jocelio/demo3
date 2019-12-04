package com.example.demo3.domain.federation;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name="tb_users", schema = "db")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Long id;

    @NotEmpty(message = "Please provide a username")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany
    @JoinTable(
            name = "tb_groups_users",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id_group"))
    @Builder.Default
    private List<Group> groups = new ArrayList<>();


    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    @JsonProperty("user_rules")
    @Builder.Default
    private List<UserRule> userRules = new ArrayList<>();

    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.mapUserRoles().distinct().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public List<String> getRoles() {
        return this.mapUserRoles().distinct().collect(toList());
    }

    private Stream<String> mapUserRoles(){
        List<String> grantRules = filterUserRules(f -> f.getIsGrant());
        List<String> revokeRules = filterUserRules(f -> !f.getIsGrant());

        List<String> rules = this.groups.stream()
                .flatMap(g -> g.getRules().stream())
                .map(Rule::getName)
                .filter(name -> !revokeRules.contains(name))
                .collect(toList());

        return Stream.concat(grantRules.stream(), rules.stream());
    }

    private List<String> filterUserRules(Predicate<UserRule> p){
        return userRules.stream()
                .filter(p).map(UserRule::getRule).map(Rule::getName).collect(toList());
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
}