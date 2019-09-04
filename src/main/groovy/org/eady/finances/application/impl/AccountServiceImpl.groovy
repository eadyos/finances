package org.eady.finances.application.impl

import groovy.transform.CompileStatic
import org.eady.finances.application.AccountService
import org.eady.finances.application.util.IDGenerator
import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.AccountRepository
import org.eady.finances.domain.model.account.Balance
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.domain.model.institution.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@CompileStatic
@Service
class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository
    @Autowired
    private InstitutionRepository institutionRepository

    @Override
    Collection<Account> getAllAccounts() {
        accountRepository.findAllAccounts()
    }

    @Override
    void updateAccountBalance(String accountId, BigDecimal balanceAmount) {
        Account account = accountRepository.findAccount(accountId)
        Balance newBalance = new Balance(amount: balanceAmount, date: new Date())
        account.currentBalance = newBalance
        accountRepository.store(account)
    }

    @Override
    Account createNewAccount(String accountName, String institutionName, BigDecimal balanceAmount) {
        Institution institution = institutionRepository.findInstitutionByName(institutionName)
        Account account = new Account(
                accountId: IDGenerator.generateAccountId(),
                name: accountName,
                currentBalance: new Balance(amount: balanceAmount, date: new Date()),
                institution: institution
        )
        accountRepository.store(account)
        account
    }

    @Override
    Collection<Balance> getBalanceHistoryForAccount(String accountId) {
        accountRepository.findBalances(accountId)
    }
}
