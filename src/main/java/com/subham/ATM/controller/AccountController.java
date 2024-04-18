package com.subham.ATM.controller;


import com.subham.ATM.CustomerDTO.ResponseCustomerDTO;
import com.subham.ATM.dto.AccountSignInDTO;
import com.subham.ATM.dto.RequestAccount;
import com.subham.ATM.dto.ResponseAccount;
import com.subham.ATM.dto.ResponseClient;
import com.subham.ATM.model.Balance;
import com.subham.ATM.service.impl.AccountService;
import com.subham.ATM.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:5173")
public class AccountController {


    private final AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

  @GetMapping("test")
  public ResponseEntity<String> testing(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
  }

    @PostMapping("/auth/register")
    public ResponseClient registerAccount(@RequestBody RequestAccount requestAccount){
        ResponseClient responseClient = this.accountService.createAccount(requestAccount);
        return  responseClient;
    }


    @PostMapping("/auth/login")
    public ResponseAccount loginAccount(@RequestBody AccountSignInDTO accountSignInDTO){

      ResponseAccount responseaccount =   this.accountService.loginAccount(accountSignInDTO);
      return responseaccount;
    }



}
