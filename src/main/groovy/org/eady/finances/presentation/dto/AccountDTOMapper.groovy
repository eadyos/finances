package org.eady.finances.presentation.dto

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.account.Account
import org.eady.finances.presentation.util.StringUtils

@CompileStatic
class AccountDTOMapper {

    static AccountDTO map(Account account){
        new AccountDTO(
                accountId: account.accountId,
                name: account.name,
                currentBalance: account.currentBalance.amount,
                currentBalanceDate: StringUtils.formatDate(account.currentBalance.date),
                institutionName: account.institution.name
        )
    }
}
