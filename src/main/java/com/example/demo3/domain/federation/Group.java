package com.example.demo3.domain.federation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="tb_groups")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    public Group(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    Long id;

    @NotEmpty
    private String name;

    @JsonIgnore
    @OneToMany(fetch = LAZY, cascade = CascadeType.DETACH, mappedBy = "groups")
    private List<User> users;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "tb_groups_rules",
            joinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id_group"),
            inverseJoinColumns = @JoinColumn(name = "id_rule", referencedColumnName = "id_rule"))
    private List<Rule> rules;


    public Group addUser(User user){
        if(users == null)
            users = Arrays.asList(user);
        else
            users.add(user);
        return this;
    }
}
