package com.example.uims.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Conviction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conviction_id_gen")
    @SequenceGenerator(name = "conviction_id_gen", sequenceName = "conviction_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
