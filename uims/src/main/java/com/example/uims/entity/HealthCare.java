package com.example.uims.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class HealthCare {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "health_care_id_gen")
    @SequenceGenerator(name = "health_care_id_gen", sequenceName = "health_care_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "hospital")
    private String hospital;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
