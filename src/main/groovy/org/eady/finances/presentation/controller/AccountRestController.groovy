package org.eady.finances.presentation.controller

import groovy.transform.CompileStatic
import org.eady.finances.application.AccountService
import org.eady.finances.domain.model.account.Account
import org.eady.finances.presentation.dto.AccountDTO
import org.eady.finances.presentation.dto.AccountDTOMapper
import org.eady.finances.presentation.dto.BalanceDTO
import org.eady.finances.presentation.dto.BalanceDTOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("resources/accounts")
@CompileStatic
class AccountRestController {

    @Autowired
    AccountService accountService

    @GetMapping("/")
    Collection<AccountDTO> getAllAccounts(){
        accountService.getAllAccounts().collect { account ->
            AccountDTOMapper.map(account)
        }
    }

    @GetMapping("/{accountId}/balances")
    List<BalanceDTO> getAllBalances(@PathVariable("accountId") String accountId){
        accountService.getBalanceHistoryForAccount(accountId).collect { balance ->
            BalanceDTOMapper.map(balance)
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    AccountDTO createAccount(@RequestBody AccountDTO accountDTO){
        Account account = accountService.createNewAccount(accountDTO.name, accountDTO.institutionName, accountDTO.currentBalance)
        AccountDTOMapper.map(account)
    }

    @PostMapping("/{accountId}")
    void updateAccount(@PathVariable("accountId") String accountId, @RequestBody AccountDTO accountDTO){
        accountService.updateAccountBalance(accountId, accountDTO.currentBalance)
    }

}
