package com.subham.ATM.service.impl;

import com.subham.ATM.dto.AccountSignInDTO;
import com.subham.ATM.dto.RequestAccount;
import com.subham.ATM.dto.ResponseAccount;
import com.subham.ATM.dto.ResponseClient;
import com.subham.ATM.model.AccountHolder;
import com.subham.ATM.model.Balance;
import com.subham.ATM.repository.AccountRepository;
import com.subham.ATM.repository.BalanceRepository;
import com.subham.ATM.service.AccountServiceImpl;
import com.subham.ATM.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements  AccountServiceImpl  {


    private final AccountRepository accountRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncode;


    @Autowired
    BalanceRepository balanceRepository;




    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }



    public ResponseClient createAccount(RequestAccount requestAccount) {
        AccountHolder accountHolder = new AccountHolder();

        // Set account holder details
        accountHolder.setFirstname(requestAccount.getFirstname());
        accountHolder.setLastname(requestAccount.getLastname());
        accountHolder.setName(requestAccount.getName());
        accountHolder.setPhoneNumber(requestAccount.getPhoneNumber());
        accountHolder.setPin(passwordEncode.encode(requestAccount.getPin()));
        accountHolder.setRole(requestAccount.getRole());

        // Initialize default balance
        Balance defaultBalance = new Balance();
        defaultBalance.setAccount_name(requestAccount.getName());
        defaultBalance.setCurrent_status("Logged In");
        defaultBalance.setCurrent_Money(0L);
        defaultBalance.setDesposited_Money(0L);
        defaultBalance.setWithDraw_Money(0L);
        defaultBalance.setTotal_Cash(0L);
        defaultBalance.setAccountHolder(accountHolder);

        // Associate balance with account holder



        // Save account holder along with associated balance
        AccountHolder savedAccount = this.accountRepository.save(accountHolder);

        // Create response
        return  ResponseClient.builder()
                .firstname(savedAccount.getFirstname())
                .lastname(savedAccount.getLastname())
                .name(savedAccount.getName())
                .phone_number(savedAccount.getPhoneNumber())
                .role(savedAccount.getRole())
                .build();


    }

//      public ResponseAccount loginAccount(AccountSignInDTO accountSignInDTO){
//
//
//        List<AccountHolder> dbAccount = this.accountRepository.findAll();
//        Optional<AccountHolder> accountHoldera = this.accountRepository.findByName(accountSignInDTO.getName());
//        boolean found = false;
//
//        for(int i = 0; i<dbAccount.size(); i++){
//            if(dbAccount.get(i).getName().equals(accountSignInDTO.getName())){
//                found = true;
//                break;
//            }
//        }
//        if(found){
//            if(passwordEncode.matches(accountSignInDTO.getPin(),accountHoldera.get().getPin() )){
//                System.out.println("line no 80");
//                AccountHolder accountHolder = accountHoldera.get();
//                String accessToken = this.jwtService.generateJWTToken(accountHolder);
//                return ResponseAccount.builder().accesstoken(accessToken).build();
//
//            }
//        }
//        return ResponseAccount.builder().accesstoken("Empty").build();
//
//    }
//}



    public ResponseAccount loginAccount(AccountSignInDTO accountSignInDTO){
        List<AccountHolder> dbAccount = this.accountRepository.findAll();
        Optional<AccountHolder> accountHoldera = this.accountRepository.findByName(accountSignInDTO.getName());
        boolean foundCustomer = false;

        for(int i = 0; i<dbAccount.size(); i++){
            if(dbAccount.get(i).getName().equals(accountSignInDTO.getName())){
                foundCustomer = true;
                System.out.println("Name matches With DTO Name" + dbAccount.get(i).getName());

                break;
            }
        }


        if(passwordEncode.matches(accountSignInDTO.getPin(),accountHoldera.get().getPin() )){
            System.out.println(foundCustomer);
            Balance balance = new Balance();
            balance.setAccount_name(accountSignInDTO.getName());
            balance.setCurrent_Money(0L);
            balance.setCurrent_status("Logged In");
            balance.setWithDraw_Money(0L);
            balance.setDesposited_Money(0L);
            balance.setTotal_Cash(0L);

            this.balanceRepository.save(balance);



        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                accountSignInDTO.getName(),
                accountSignInDTO.getPin()
        ));

        var customer = this.accountRepository.findByName(accountSignInDTO.getName()).orElseThrow(() -> new UsernameNotFoundException("User Does not Exists"));
        String accesstoken = this.jwtService.generateJWTToken(customer);

        return ResponseAccount.builder().accesstoken(accesstoken).build();
    }


}