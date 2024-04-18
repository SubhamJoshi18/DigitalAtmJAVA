package com.subham.ATM.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int balance_id;




    private String account_name;


    private Long Desposited_Money= 0L;


    private String Current_status= "Logged In";


    private Long WithDraw_Money=0L;


    private Long  Current_Money=0L;

    private Long Total_Cash=0L;





   @OneToOne
   @JoinColumn(name = "accountHolder_id")
   private AccountHolder accountHolder;

    public int getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(int balance_id) {
        this.balance_id = balance_id;
    }


    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Long getDesposited_Money() {
        return Desposited_Money;
    }

    public void setDesposited_Money(Long desposited_Money) {
        Desposited_Money = desposited_Money;
    }

    public String getCurrent_status() {
        return Current_status;
    }

    public void setCurrent_status(String current_status) {
        Current_status = current_status;
    }

    public Long getWithDraw_Money() {
        return WithDraw_Money;
    }

    public void setWithDraw_Money(Long withDraw_Money) {
        WithDraw_Money = withDraw_Money;
    }

    public Long getCurrent_Money() {
        return Current_Money;
    }

    public void setCurrent_Money(Long current_Money) {
        Current_Money = current_Money;
    }

    public Long getTotal_Cash() {
        return Total_Cash;
    }

    public void setTotal_Cash(Long total_Cash) {
        Total_Cash = total_Cash;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}


