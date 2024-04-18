package com.subham.ATM.service.impl;

import com.subham.ATM.CustomerDTO.BalanceDTO.ResponseBalanceDTO;
import com.subham.ATM.CustomerDTO.Deposited.DepositedDTO;
import com.subham.ATM.CustomerDTO.Deposited.DepositedResponseDTO;
import com.subham.ATM.CustomerDTO.ResponseCustomerDTO;
import com.subham.ATM.CustomerDTO.WithdrawnDTO.WithDrawResponseDto;
import com.subham.ATM.CustomerDTO.WithdrawnDTO.WithDrawnRequestDTO;
import com.subham.ATM.model.Balance;
import com.subham.ATM.repository.BalanceRepository;
import com.subham.ATM.service.CustomerServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerService implements CustomerServiceImpl {

    private final BalanceRepository balanceRepository;


    @Autowired
    public CustomerService(BalanceRepository balanceRepository){
        this.balanceRepository = balanceRepository;
    }


    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public List<ResponseCustomerDTO> showCustomerDetails() {
        List<Balance> balanceList = this.balanceRepository.findAll();

        List<ResponseCustomerDTO> balanceDtoList = balanceList.stream().map(balance -> {
            return new ResponseCustomerDTO(balance.getAccount_name(), balance.getCurrent_status(), balance.getCurrent_Money(), balance.getTotal_Cash());
        }).toList();

        System.out.println(balanceDtoList);

        return balanceDtoList;
    }







    @Override
    @Transactional
    public DepositedResponseDTO depositedAmount(DepositedDTO depositedDTO) {
        Long defaultAmount = 0L;
        if(depositedDTO.getAmount() == 0 || depositedDTO.getAmount().toString().equals("0") ){
            throw new RuntimeException("Cannot Deposited 0 Amount , Please Increase Your Amount");
        }

        Optional<Balance> existingBalanceOptionl = this.balanceRepository.findById(depositedDTO.getId());
        if(existingBalanceOptionl.isEmpty()){
            throw new RuntimeException("Balance Of the Given Customer Does not Valid");

        }
        Balance exisitingBalance = existingBalanceOptionl.get();
        var check = exisitingBalance.getTotal_Cash()  ==  0 ? exisitingBalance.getTotal_Cash() + 5 : 0;
        if(depositedDTO.getAmount() >= check){
            long newTotalCash = exisitingBalance.getTotal_Cash() + depositedDTO.getAmount();
            System.out.println(exisitingBalance);
            exisitingBalance.setTotal_Cash(newTotalCash);
            exisitingBalance.setCurrent_Money(newTotalCash);
            exisitingBalance.setDesposited_Money(depositedDTO.getAmount());

        }else{
            throw new RuntimeException("Cannot Deposited Money Lesser than 5");
        }

        Balance balance = exisitingBalance;

        return DepositedResponseDTO.builder().amount(depositedDTO.getAmount()).total_cash(balance.getTotal_Cash()).current_Money(balance.getCurrent_Money()).account_name(balance.getAccount_name()).build();

    }


    @Override
    public ResponseBalanceDTO findBalanceById(Integer id){

        Optional<Balance> balance =  this.balanceRepository.findById(id);
        if(balance.isEmpty() || !balance.isPresent()){
            throw new RuntimeException("You Do not own This is Balance Id");
        }
        List<?> random = balance.stream().filter(data -> data.getBalance_id() == id).collect(Collectors.toList());
        if(random.size() == 0 || random.isEmpty()){
            throw new RuntimeException("ID did not Matches");
        }
        Balance balance1 = balance.get();
        return  ResponseBalanceDTO.builder().id(balance1.getBalance_id()).account_name(balance1.getAccount_name()).accountHolderId(balance1.getAccountHolder().getId()).currentMoney(balance1.getCurrent_Money()).total_money(balance1.getTotal_Cash()).build();



    }



    @Override
    @Transactional
    public  WithDrawResponseDto withDrawAmount(WithDrawnRequestDTO withDrawnRequestDTO){
        var withDrawMoney = ((Long) withDrawnRequestDTO.getWithDrawn_Amount()).intValue();
        switch(withDrawMoney){
            case 0:{
                throw new RuntimeException("Cannot WithDrawn Amount Equal to or Lesser than 0");

            }

            case 5 : {
                throw new RuntimeException("Cannot WithDrawn Amount Equal to or Lesser than 5");

            }

            default:
        }

        long retriveCash;

        Optional<Balance> balanceDetails = this.balanceRepository.findById(withDrawnRequestDTO.getId());
        if(balanceDetails.isEmpty() || !balanceDetails.isPresent()){
            throw new RuntimeException("Cannot Find the Customer Balance Details");
        }

        if( withDrawnRequestDTO.getWithDrawn_Amount() == balanceDetails.get().getTotal_Cash()){
            retriveCash = balanceDetails.get().getTotal_Cash() - withDrawnRequestDTO.getWithDrawn_Amount();
            balanceDetails.get().setCurrent_Money(0L);
            balanceDetails.get().setTotal_Cash(0L);

        }

        if(balanceDetails.get().getTotal_Cash() >= withDrawnRequestDTO.getWithDrawn_Amount()){
            long newTotalCash = balanceDetails.get().getTotal_Cash() - withDrawnRequestDTO.getWithDrawn_Amount();
            System.out.println(newTotalCash);
            long newCurrentMoney = balanceDetails.get().getCurrent_Money() - withDrawnRequestDTO.getWithDrawn_Amount();
            System.out.println(newCurrentMoney);
            balanceDetails.get().setTotal_Cash(newTotalCash);
            balanceDetails.get().setCurrent_Money(newTotalCash);
            retriveCash = withDrawnRequestDTO.getWithDrawn_Amount();

        }else{
            throw new RuntimeException("Insufficient Balance");
        }


        Balance balance =  balanceDetails.get();

        return WithDrawResponseDto.builder().withDrawn_Money(retriveCash).Total_Cash(balance.getTotal_Cash()).Current_Money(balance.getCurrent_Money()).message(retriveCash  + "Has Been Withdrawn SuccessFully").build();






    }


}
