package com.example.uims.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
