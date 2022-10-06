package com.example.uims.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "migrations")
public class Migration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "migrations_id_gen")
    @SequenceGenerator(name = "migrations_id_gen", sequenceName = "migrations_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "from_country")
    private String fromCountry;
    @Column(name = "to_country")
    private String toCountry;
    @Column(name = "date")
    @Past
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
