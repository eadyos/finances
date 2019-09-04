package org.eady.finances.infrastructure.persistence.file

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.AccountRepository
import org.eady.finances.domain.model.account.Balance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@CompileStatic
@Repository
class AccountRepositoryFileImpl implements AccountRepository{

    private AccountRepositoryFileFacade accountRepositoryFileFacade

    @Autowired
    AccountRepositoryFileImpl(File accountRepositoryFile){
        accountRepositoryFileFacade = new AccountRepositoryFileFacade(accountRepositoryFile)
    }

    @Override
    Collection<Account> findAllAccounts() {
        accountRepositoryFileFacade.accountRepositoryFileDTO.accountByAccountId.values()
    }

    @Override
    Account findAccount(String accountId) {
        accountRepositoryFileFacade.accountRepositoryFileDTO.accountByAccountId[accountId]
    }

    @Override
    void store(Account account) {
        AccountRepositoryFileDTO existingFileContent = accountRepositoryFileFacade.accountRepositoryFileDTO
        existingFileContent.accountByAccountId[account.accountId] = account
        existingFileContent.balancesByAccountId.computeIfAbsent(account.accountId, {[]}) << account.currentBalance
        accountRepositoryFileFacade.saveAccountRepositoryFileDTO(existingFileContent)
    }

    @Override
    Collection<Balance> findBalances(String accountId) {
        accountRepositoryFileFacade.accountRepositoryFileDTO.balancesByAccountId[accountId]
                ?: [] as Collection<Balance>
    }

}
