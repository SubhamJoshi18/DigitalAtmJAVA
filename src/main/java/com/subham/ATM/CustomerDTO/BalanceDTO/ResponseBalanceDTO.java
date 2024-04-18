package com.subham.ATM.CustomerDTO.BalanceDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.subham.ATM.model.AccountHolder;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ResponseBalanceDTO {




    @JsonProperty("Balance_Id")
    private Integer id;


    @JsonProperty("Account Name")
    private String account_name;



    @JsonProperty("AccountHolderId")
    private Integer accountHolderId;


    @JsonProperty("current_Money")
    private Long currentMoney;


    @JsonProperty("Total_Money")
    private Long total_money;


}
