package com.subham.ATM.CustomerDTO.Deposited;

public class DepositedDTO {



    private Integer id;

    private Long amount;


    public DepositedDTO(){


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepositedDTO(Long amount){
        this.amount = amount;


    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
