package com.example.sqs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EcmDto {

    @NotEmpty(message = "ecmId값이 빈값입니다.")
    private String ecmId;

}