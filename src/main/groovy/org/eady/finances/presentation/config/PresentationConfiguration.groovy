package org.eady.finances.presentation.config

import groovy.transform.CompileStatic
import org.eady.finances.presentation.controller.AccountRestController
import org.eady.finances.presentation.controller.InstitutionRestController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@CompileStatic
class PresentationConfiguration {

    @Bean
    AccountRestController accountRestController(){
        new AccountRestController()
    }

    @Bean
    InstitutionRestController institutionRestController(){
        new InstitutionRestController()
    }
}
