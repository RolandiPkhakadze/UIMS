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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    @SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "from_account_number")
    private String fromAccountNumber;
    @Column(name = "to_account_number")
    private String toAccountNumber;
    @Column(name = "amount")
    private long amount;
    @Column(name = "description")
    private String description;

}
