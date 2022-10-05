package com.example.uims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_gen")
    @SequenceGenerator(name = "account_id_gen", sequenceName = "account_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "expiration_date")
    @Future
    private LocalDate expirationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "fromAccount")
    @JsonIgnore
    private List<Transaction> incomingTransactionList;
    @OneToMany(mappedBy = "toAccount")
    @JsonIgnore
    private List<Transaction> outgoingTransactionList;
}
