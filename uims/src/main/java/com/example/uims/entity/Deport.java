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
public class Deport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deport_id_gen")
    @SequenceGenerator(name = "deport_id_gen", sequenceName = "deport_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "migration_id")
    private Migration migration;
}
