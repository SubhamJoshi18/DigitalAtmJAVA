package com.subham.ATM.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.subham.ATM.utils.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ResponseClient {



    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phoneNumber")
    private Long phone_number;

    @JsonProperty("role")
    private Role role;



}
