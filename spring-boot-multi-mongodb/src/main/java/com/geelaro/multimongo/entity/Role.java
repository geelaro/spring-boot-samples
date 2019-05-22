package com.geelaro.multimongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "role")
public class Role {

    @Id
    private Long id;

    private String role;

    private String desc;

    public Role(Long id, String role, String desc) {
        this.id = id;
        this.role = role;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
