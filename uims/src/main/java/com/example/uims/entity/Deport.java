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
@Table(name = "deports")
public class Deport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deports_id_gen")
    @SequenceGenerator(name = "deports_id_gen", sequenceName = "deports_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "migration_id")
    private Migration migration;
}
