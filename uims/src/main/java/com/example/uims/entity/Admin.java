package com.example.uims.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
