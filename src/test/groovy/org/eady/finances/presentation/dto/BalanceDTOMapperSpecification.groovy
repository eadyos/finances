package org.eady.finances.presentation.dto

import org.eady.finances.domain.model.account.Balance
import org.eady.finances.presentation.util.DateUtils
import spock.lang.Specification

class BalanceDTOMapperSpecification extends Specification{

    def "BalanceDTOs can be mapped from Balance Domain Objects"(){

        given:
        Balance balance = new Balance(amount: 2.34, date: DateUtils.parseDate("2019-02-04"))

        when:
        BalanceDTO balanceDTO = BalanceDTOMapper.map(balance)

        then:
        balanceDTO.amount == 2.34
        balanceDTO.date == "2019-02-04"
    }
}
