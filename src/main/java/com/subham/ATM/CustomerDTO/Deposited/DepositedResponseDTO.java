package com.subham.ATM.CustomerDTO.Deposited;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositedResponseDTO {



    @JsonProperty("Deposited_Amount")
    private Long amount;



    @JsonProperty("Account_Name")
    private String account_name;


    @JsonProperty("current_Money")
    private Long current_Money;


    @JsonProperty("Total_Cash")
    private Long total_cash;


}
