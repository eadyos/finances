package org.eady.finances.application

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.Balance

@CompileStatic
interface AccountService {

    Collection<Account> getAllAccounts()

    void updateAccountBalance(String accountId, BigDecimal newBalance)

    Account createNewAccount(String accountName, String institutionName, BigDecimal balance)

    Collection<Balance> getBalanceHistoryForAccount(String accountId)

}
