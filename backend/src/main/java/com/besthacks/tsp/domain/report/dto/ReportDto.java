package com.besthacks.tsp.domain.report.dto;

import com.besthacks.tsp.domain.account.entity.Account;
import com.besthacks.tsp.domain.report.entity.ReportStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ReportStatus reportStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Account account;

    @NotBlank
    private String description;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private Integer streetNumber;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDateTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDateTime;
}
