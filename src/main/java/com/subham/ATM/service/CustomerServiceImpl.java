package com.subham.ATM.service;

import com.subham.ATM.CustomerDTO.BalanceDTO.ResponseBalanceDTO;
import com.subham.ATM.CustomerDTO.Deposited.DepositedDTO;
import com.subham.ATM.CustomerDTO.Deposited.DepositedResponseDTO;
import com.subham.ATM.CustomerDTO.ResponseCustomerDTO;
import com.subham.ATM.CustomerDTO.WithdrawnDTO.WithDrawResponseDto;
import com.subham.ATM.CustomerDTO.WithdrawnDTO.WithDrawnRequestDTO;

import java.util.List;

public interface CustomerServiceImpl {

    List<ResponseCustomerDTO>  showCustomerDetails();




    DepositedResponseDTO depositedAmount(DepositedDTO depositedDTO);



    ResponseBalanceDTO findBalanceById(Integer id);


    WithDrawResponseDto withDrawAmount(WithDrawnRequestDTO withDrawnRequestDTO);



}
