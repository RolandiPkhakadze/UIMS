package com.example.uims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "personal_no")
    private String personalNo;
    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<BankAccount> accountList;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Migration> migrationList;
}
