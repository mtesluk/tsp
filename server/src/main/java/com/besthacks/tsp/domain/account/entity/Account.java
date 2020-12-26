package com.besthacks.tsp.domain.account.entity;

import com.besthacks.tsp.domain.report.entity.Report;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String city;
    @Column
    private String password;
    @Column
    @Enumerated(value = EnumType.STRING)
    private AccountRole accountRole;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Report> reports;
}
