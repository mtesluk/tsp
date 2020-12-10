package com.besthacks.tsp.domain.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column
    private Long accountId;
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
