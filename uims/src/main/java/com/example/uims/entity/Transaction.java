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
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_id_gen")
    @SequenceGenerator(name = "transactions_id_gen", sequenceName = "transactions_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "amount")
    private long amount;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private BankAccount fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private BankAccount toAccount;

}
