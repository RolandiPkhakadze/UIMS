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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "amount_id_gen")
    @SequenceGenerator(name = "amount_id_gen", sequenceName = "amount_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "from_acc_id")
    private long fromAccId;
    @Column(name = "to_acc_id")
    private long toAccId;
    @Column(name = "amount")
    private long amount;
    @Column(name = "description")
    private String description;

}
