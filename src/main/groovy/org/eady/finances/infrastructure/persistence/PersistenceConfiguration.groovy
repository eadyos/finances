package org.eady.finances.infrastructure.persistence

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.account.AccountRepository
import org.eady.finances.domain.model.institution.InstitutionRepository
import org.eady.finances.infrastructure.persistence.file.AccountRepositoryFileImpl
import org.eady.finances.infrastructure.persistence.file.InstitutionRepositoryFileImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.properties")
@CompileStatic
class PersistenceConfiguration {

    @Value( '${accountRepositoryFilename}' )
    private String accountRepositoryFilename

    @Value( '${institutionRepositoryFilename}' )
    private String institutionRepositoryFilename

    @Bean
    File accountRepositoryFile(){
        new File(accountRepositoryFilename)
    }

    @Bean
    File institutionRepositoryFile(){
        new File(institutionRepositoryFilename)
    }

    @Bean
    AccountRepository accountRepository(){
        new AccountRepositoryFileImpl(accountRepositoryFile())
    }

    @Bean
    InstitutionRepository institutionRepository(){
        new InstitutionRepositoryFileImpl(institutionRepositoryFile())
    }
}
