package com.subham.ATM.CustomerDTO.WithdrawnDTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WithDrawResponseDto {



    @JsonProperty("message")
    private String message;


    @JsonProperty("Withdrawn_Money")
    private Long withDrawn_Money;


    @JsonProperty("Current_Money")
    private Long Current_Money;

    @JsonProperty("Total_Cash")
    private Long Total_Cash;


}
