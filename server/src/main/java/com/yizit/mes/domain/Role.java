package com.yizit.mes.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "角色名称不能为空")
    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Authority> authorityList;

    protected Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
