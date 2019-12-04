package com.example.demo3.domain.federation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_users_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserRuleId.class)
public class UserRule implements Serializable {

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    public User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_rule", referencedColumnName = "id_rule")
    public Rule rule;

    @Column(name="is_grant")
    @JsonProperty("is_grant")
    private Boolean isGrant;
}

class UserRuleId implements Serializable{
    private Long user;
    private Long rule;
}