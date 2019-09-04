package org.eady.finances.domain.model.account

import spock.lang.Specification

class AccountSpecification extends Specification{

    def "An account's current balance can be updated"(){

        given:
        Account account = new Account()
        Date yesterday = new Date() - 1
        Balance balance = new Balance(amount: 1.23, date: yesterday)
        account.currentBalance = balance

        when:
        account.updateBalance(4.56)

        then:
        account.currentBalance.amount == 4.56
        account.currentBalance.date > yesterday
    }
}
