package com.besthacks.tsp.domain.report.entity;

import com.besthacks.tsp.domain.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ReportStatus reportStatus;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Integer streetNumber;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column
    private LocalDateTime createdDateTime;

    @Column
    private LocalDateTime updatedDateTime;

    @PrePersist
    private void setCreatedDateTime() {
        createdDateTime = LocalDateTime.now();
        updatedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void setUpdatedDateTime() {
        updatedDateTime = LocalDateTime.now();
    }
}
