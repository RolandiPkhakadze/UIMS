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
    @SequenceGenerator(name = "amount_id_gen", sequenceName = "amount_id_gen", allocationSize = 1)
    private long id;
    @Column(name = "from_acc_id")
    private long from_acc_id;
    @Column(name = "to_acc_id")
    private long to_acc_id;
    @Column(name = "amount")
    private long amount;
    @Column(name = "description")
    private String description;

}
