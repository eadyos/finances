package org.eady.finances.domain.model.account

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.institution.Institution

@CompileStatic
class Account{

    String accountId
    String name
    Balance currentBalance
    Institution institution

    void updateBalance(BigDecimal newBalance){
        currentBalance = new Balance(amount: newBalance, date: new Date())
    }

}

