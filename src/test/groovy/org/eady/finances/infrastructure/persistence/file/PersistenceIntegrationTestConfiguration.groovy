package org.eady.finances.infrastructure.persistence.file

import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.AccountRepository
import org.eady.finances.domain.model.account.Balance
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.domain.model.institution.InstitutionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.properties")
class PersistenceIntegrationTestConfiguration {

    @Value( '${accountRepositoryFilename}' )
    private String accountRepositoryFilename

    @Value( '${institutionRepositoryFilename}' )
    private String institutionRepositoryFilename

    @Bean
    File accountRepositoryFile(){
        File repoFile = new File(accountRepositoryFilename)
        repoFile.deleteOnExit()
        repoFile
    }

    @Bean
    File institutionRepositoryFile(){
        File repoFile = new File(institutionRepositoryFilename)
        repoFile.deleteOnExit()
        repoFile
    }

    @Bean
    AccountRepository accountRepository(){
        AccountRepository accountRepository = new AccountRepositoryFileImpl(accountRepositoryFile())
        Account account = new Account(
                accountId: 1,
                name: "My Account",
                currentBalance: new Balance(amount: 1.23, date: new Date()-1),
                institution: new Institution(name: "My Bank", institutionId: "1")
        )
        accountRepository.store(account)
        accountRepository
    }

    @Bean
    InstitutionRepository institutionRepository(){
        InstitutionRepository institutionRepository = new InstitutionRepositoryFileImpl(institutionRepositoryFile())
        Institution institution = new Institution(name: "Test Institution", institutionId: "1")
        institutionRepository.store(institution)
        institutionRepository
    }


}
