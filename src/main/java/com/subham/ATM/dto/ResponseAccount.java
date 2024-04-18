package com.subham.ATM.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAccount {


     @JsonProperty("access_token")
     private String accesstoken;


}
