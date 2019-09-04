package org.eady.finances.application.config

import org.eady.finances.application.AccountService
import org.eady.finances.application.impl.AccountServiceImpl
import org.eady.finances.application.impl.InstitutionServiceImpl
import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.Balance
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.infrastructure.persistence.PersistenceUnitTestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import spock.lang.Specification

@Configuration
@Import(PersistenceUnitTestConfiguration)
class ApplicationUnitTestConfiguration extends Specification {

    Account mockAccount1() {
        new Account(
                name: "Test Account",
                accountId: "testAccountId1",
                currentBalance: new Balance(amount: 1.23, date: new Date()),
                institution: new Institution(institutionId: "testInstitutionID", name: "Test Institution")
        )
    }

    Account mockAccount2() {
        new Account(
                name: "Test Account",
                accountId: "testAccountId2",
                currentBalance: new Balance(amount: 1.24, date: new Date()),
                institution: new Institution(institutionId: "testInstitutionID", name: "Test Institution")
        )
    }


    @Bean
    AccountService accountService() {
        Mock(AccountService) {
            getAllAccounts() >> [mockAccount1(), mockAccount2()]
            createNewAccount(_ as String, _ as String, _ as BigDecimal) >> mockAccount1()
            getBalanceHistoryForAccount(_ as String) >> [
                    mockAccount1().currentBalance
            ]
        }
    }

    @Bean
    AccountServiceImpl accountServiceImpl() {
        new AccountServiceImpl()
    }

    @Bean
    InstitutionServiceImpl institutionServiceImpl() {

        new InstitutionServiceImpl()
    }


}
