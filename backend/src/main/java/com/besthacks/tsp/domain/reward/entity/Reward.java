package com.besthacks.tsp.domain.reward.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer amount;
    @Column
    private Long accountId;
}
