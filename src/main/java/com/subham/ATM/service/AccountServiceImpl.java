package com.subham.ATM.service;

import com.subham.ATM.dto.AccountSignInDTO;
import com.subham.ATM.dto.RequestAccount;
import com.subham.ATM.dto.ResponseAccount;
import com.subham.ATM.dto.ResponseClient;

public interface AccountServiceImpl {
    ResponseClient createAccount(RequestAccount requestAccount);

    ResponseAccount loginAccount(AccountSignInDTO accountSignInDTO);
}
