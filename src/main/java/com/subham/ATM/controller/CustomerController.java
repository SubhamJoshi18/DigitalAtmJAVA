package com.subham.ATM.controller;

import com.subham.ATM.CustomerDTO.BalanceDTO.ResponseBalanceDTO;
import com.subham.ATM.CustomerDTO.Deposited.DepositedDTO;
import com.subham.ATM.CustomerDTO.Deposited.DepositedResponseDTO;
import com.subham.ATM.CustomerDTO.ResponseCustomerDTO;
import com.subham.ATM.CustomerDTO.WithdrawnDTO.WithDrawResponseDto;
import com.subham.ATM.CustomerDTO.WithdrawnDTO.WithDrawnRequestDTO;
import com.subham.ATM.model.Balance;
import com.subham.ATM.service.impl.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
@PreAuthorize("hasRole( 'CUSTOMER')")
public class CustomerController {



    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String test(){
        return "Hi ";
    }

    @GetMapping("/details/")
    public List<ResponseCustomerDTO> showCustomerDetails() {
        return customerService.showCustomerDetails();
    }



    @PostMapping("/deposit")
    public DepositedResponseDTO depositedMoney(@RequestBody DepositedDTO depositedDTO){
       DepositedResponseDTO depositedDTO1 = this.customerService.depositedAmount(depositedDTO);
        return depositedDTO1;
    }


    @GetMapping("/{id}")
    public ResponseEntity findByBalance(@PathVariable Integer id){
        ResponseBalanceDTO responseBalanceDTO = this.customerService.findBalanceById(id);
       return ResponseEntity.ok(responseBalanceDTO);
    }






    @PostMapping("/withdraw")
    public WithDrawResponseDto withDrawnBalance(@RequestBody WithDrawnRequestDTO withDrawnRequestDTO){

        WithDrawResponseDto withDrawResponseDto = this.customerService.withDrawAmount(withDrawnRequestDTO);
        return  withDrawResponseDto;

    }









}

