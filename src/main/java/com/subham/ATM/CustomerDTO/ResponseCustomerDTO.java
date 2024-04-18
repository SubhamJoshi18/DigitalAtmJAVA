package com.subham.ATM.CustomerDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCustomerDTO {

    @JsonProperty("Customer_Name")
    private String account_name;

    @JsonProperty("Current_Status")
    private String current_status;

    @JsonProperty("Current_Money")
    private Long current_Money;

    @JsonProperty("Total_Cash")
    private Long total_Cash;

    public ResponseCustomerDTO(String account_name, String current_status, Long current_Money, Long total_Cash) {
        this.account_name = account_name;
        this.current_status = current_status;
        this.current_Money = current_Money;
        this.total_Cash = total_Cash;
    }
}
