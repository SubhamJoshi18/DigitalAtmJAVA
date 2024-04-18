package com.subham.ATM.CustomerDTO.WithdrawnDTO;

public class WithDrawnRequestDTO {


    private Integer id;


    private Long withDrawn_Amount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getWithDrawn_Amount() {
        return withDrawn_Amount;
    }

    public void setWithDrawn_Amount(Long withDrawn_Amount) {
        this.withDrawn_Amount = withDrawn_Amount;
    }
}
