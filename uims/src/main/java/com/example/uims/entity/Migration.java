package com.example.uims.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Migration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "migration_id_gen")
    @SequenceGenerator(name = "migration_id_gen", sequenceName = "migration_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "from_country")
    private String fromCountry;
    @Column(name = "to_country")
    private String toCountry;
    @Column(name = "date")
    @Past
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
