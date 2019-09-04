package org.eady.finances.presentation.dto

import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.Balance
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.presentation.util.DateUtils
import spock.lang.Specification

class AccountDTOMapperSpecification extends Specification{



    def "An Account can be mapped to an AccountDTO"(){

        given:
        Account account = new Account(
                accountId: "123",
                name: "account name",
                currentBalance: new Balance(amount: 1.23, date: DateUtils.parseDate("2019-01-01")),
                institution: new Institution(name: "institution name", institutionId: "456")
        )

        when:
        AccountDTO accountDTO = AccountDTOMapper.map(account)

        then:
        accountDTO.name == "account name"
        accountDTO.accountId == "123"
        accountDTO.currentBalance == 1.23
        accountDTO.currentBalanceDate == "2019-01-01"
        accountDTO.institutionName == "institution name"
    }
}
