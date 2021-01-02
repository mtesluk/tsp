package com.besthacks.tsp.dto;

import com.besthacks.tsp.entity.AccountRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    @NotBlank(message = "Password must exists")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Email must exists")
    private String email;

    @NotBlank(message = "Username must exists")
    private String username;

    @NotBlank(message = "City must exists")
    private String city;

    @NotBlank(message = "Password must exists")
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(value = EnumType.STRING)
    private AccountRole role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer points;
}
