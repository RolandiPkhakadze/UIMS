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
@Table(name = "convictions")
public class Conviction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "convictions_id_gen")
    @SequenceGenerator(name = "convictions_id_gen", sequenceName = "convictions_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
