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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "personal_no")
    private String personalNo;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BankAccount> accountList;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Migration> migrationList;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Conviction> convictionList;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<HealthCare> healthCareList;
}
