package org.eady.finances.infrastructure.persistence

import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.AccountRepository
import org.eady.finances.domain.model.account.Balance
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.domain.model.institution.InstitutionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import spock.lang.Specification

@Configuration
class PersistenceUnitTestConfiguration extends Specification{

    Account mockAccount1(){
        new Account(
                name: "Test Account",
                accountId: "testAccountId1",
                currentBalance: new Balance(amount: 1.23, date: new Date()),
                institution: new Institution(institutionId: "testInstitutionID", name: "Test Institution")
        )
    }

    Account mockAccount2(){
        new Account(
                name: "Test Account",
                accountId: "testAccountId2",
                currentBalance: new Balance(amount: 1.24, date: new Date()),
                institution: new Institution(institutionId: "testInstitutionID", name: "Test Institution")
        )
    }


    @Bean
    AccountRepository accountRepository(){

        AccountRepository accountRepositoryMock = Mock(AccountRepository){
            findAllAccounts() >> [mockAccount1(), mockAccount2()]
            findAccount("testAccountId1") >> mockAccount1()
            findAccount("testAccountId2") >> mockAccount1()
            findBalances("testAccountId1") >> [
                    new Balance(amount: 1.22, date: new Date() - 1),
                    mockAccount1().currentBalance
            ]
        }

        accountRepositoryMock
    }

    @Bean
    InstitutionRepository institutionRepository(){

        Institution mockInstitution = new Institution(
                name: "Test Institution", institutionId: "testInstitutionID"
        )


        InstitutionRepository institutionRepositoryMock = Mock(InstitutionRepository){
            findAllInstitutions() >> [mockInstitution]
            findInstitution("testInstitutionID") >> mockInstitution
            findInstitutionByName("Test Institution") >> mockInstitution
        }

        institutionRepositoryMock
    }
}
