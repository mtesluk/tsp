package com.besthacks.tsp.domain.report.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long reportId;
    @Column
    private String fileType;
    @Column
    private String filename;
    @Lob
    private byte[] data;
}
