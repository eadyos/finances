package org.eady.finances.application.impl

import org.eady.finances.application.config.ApplicationUnitTestConfiguration
import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.Balance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification

@ContextConfiguration(loader = AnnotationConfigContextLoader, classes = [ApplicationUnitTestConfiguration])
class AccountServiceImplSpecification extends Specification {

    @Autowired
    AccountServiceImpl accountService = new AccountServiceImpl()

    def "The account service can return all accounts"() {

        when:
        def accounts = accountService.getAllAccounts()

        then:
        accounts.size() == 2
        accounts[0].name == "Test Account"
        accounts[0].accountId == "testAccountId1"
        accounts[1].accountId == "testAccountId2"
    }

    def "The account service can update an account's balance"() {

        given:
        String accountId = "testAccountId1"
        BigDecimal newBalance = 6.78

        when:
        accountService.updateAccountBalance(accountId, newBalance)

        then:
        noExceptionThrown()
    }

    def "The account service can create a new account"() {

        given:
        String accountName = "New Account"
        String institutionName = "Test Institution"
        BigDecimal balance = 5.67

        when:
        Account account = accountService.createNewAccount(accountName, institutionName, balance)

        then:
        account.name == accountName
        account.currentBalance.amount == balance
        account.currentBalance.date > new Date() - 1
        account.institution.name == "Test Institution"
    }

    def "The account service can return a balance history for an account"() {

        given:
        String accountId = "testAccountId1"

        when:
        Collection<Balance> balances = accountService.getBalanceHistoryForAccount(accountId)

        then:
        balances.size() == 2
        balances[0].amount == 1.22
        balances[1].amount == 1.23
    }

}