package com.subham.ATM.dto;

public class AccountSignInDTO {



    private String name;

    private String pin;


    public AccountSignInDTO(){
        this.name = name;
        this.pin = pin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
