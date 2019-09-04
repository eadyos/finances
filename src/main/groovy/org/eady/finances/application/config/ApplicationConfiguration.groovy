package org.eady.finances.application.config

import groovy.transform.CompileStatic
import org.eady.finances.application.AccountService
import org.eady.finances.application.InstitutionService
import org.eady.finances.application.impl.AccountServiceImpl
import org.eady.finances.application.impl.InstitutionServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@CompileStatic
class ApplicationConfiguration {

    @Bean
    AccountService accountService(){
        new AccountServiceImpl()
    }

    @Bean
    InstitutionService institutionService(){
        new InstitutionServiceImpl()
    }
}
