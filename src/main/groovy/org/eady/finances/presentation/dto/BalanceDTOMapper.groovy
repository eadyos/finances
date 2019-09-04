package org.eady.finances.presentation.dto

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.account.Balance
import org.eady.finances.presentation.util.StringUtils

@CompileStatic
class BalanceDTOMapper {

    static BalanceDTO map(Balance balance){
        new BalanceDTO(amount: balance.amount, date: StringUtils.formatDate(balance.date))
    }
}
