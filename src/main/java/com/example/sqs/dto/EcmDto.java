package com.example.sqs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcmDto {

    @NotEmpty(message = "ecmId값이 빈값입니다.")
    private String ecmId;

}