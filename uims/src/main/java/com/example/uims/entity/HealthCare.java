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
@Table(name = "health_cares")
public class HealthCare {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_cares_id_gen")
    @SequenceGenerator(name = "health_cares_id_gen", sequenceName = "health_cares_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "hospital")
    private String hospital;
    @Column(name = "date")
    @Past
    private LocalDate date;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
