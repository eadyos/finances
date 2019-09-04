package org.eady.finances.presentation.dto

import groovy.transform.CompileStatic

@CompileStatic
class AccountDTO {

    String accountId
    String name
    BigDecimal currentBalance
    String currentBalanceDate
    String institutionName
}
