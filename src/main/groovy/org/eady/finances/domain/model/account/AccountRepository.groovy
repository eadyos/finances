package org.eady.finances.domain.model.account

import groovy.transform.CompileStatic

@CompileStatic
interface AccountRepository {

    Collection<Account> findAllAccounts()

    Account findAccount(String accountId)

    void store(Account account)

    Collection<Balance> findBalances(String accountId)
}
