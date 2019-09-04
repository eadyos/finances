package org.eady.finances.presentation.controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.eady.finances.application.config.ApplicationConfiguration
import org.eady.finances.infrastructure.persistence.file.PersistenceIntegrationTestConfiguration
import org.eady.finances.presentation.config.PresentationConfiguration
import org.eady.finances.presentation.dto.AccountDTO
import org.eady.finances.presentation.util.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@ContextConfiguration(loader = AnnotationConfigContextLoader,
        classes = [PresentationConfiguration, ApplicationConfiguration, PersistenceIntegrationTestConfiguration] )
class AccountRestServiceIntegrationTest extends Specification{

    @Autowired
    AccountRestController accountRestController

    MockMvc mvc
    def setup(){
        mvc = MockMvcBuilders.standaloneSetup(accountRestController).build()
    }

    def "The service can return a list of accounts and an HTTP 200/OK status"(){

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.get('/resources/accounts/')
        ).andReturn().response
        List<Map> accountMaps = new JsonSlurper().parse(response.contentAsByteArray) as List<Map>

        then:
        response.status == HttpStatus.OK.value()
        accountMaps.size() == 1
        accountMaps[0].accountId == "1"
        accountMaps[0].name == "My Account"
        accountMaps[0].currentBalance == 1.23
        accountMaps[0].currentBalanceDate ==~ /\d{4}-\d{2}-\d{2}/
        accountMaps[0].institutionName == "My Bank"
    }

    def "The service can return a balance history for an account"(){

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.get('/resources/accounts/1/balances')
        ).andReturn().response
        List<Map> balanceMaps = new JsonSlurper().parse(response.contentAsByteArray) as List<Map>

        then:
        response.status == HttpStatus.OK.value()
        balanceMaps.size() == 1
        balanceMaps[0].amount == 1.23
        balanceMaps[0].date ==~ /\d{4}-\d{2}-\d{2}/
    }

    def "A new account can be created"(){

        given:
        AccountDTO accountDTO = new AccountDTO(name: "Test Account", currentBalance: 1.23, institutionName: "Test Institution")
        def accountJson = JsonOutput.toJson(accountDTO)

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.post("/resources/accounts/")
                        .contentType("application/json")
                        .content(accountJson)
        ).andReturn().response
        AccountDTO createdAccountDTO = new JsonSlurper().parse(response.contentAsByteArray) as AccountDTO

        then:
        response.status == HttpStatus.CREATED.value()
        createdAccountDTO.accountId
        createdAccountDTO.name == "Test Account"
        createdAccountDTO.currentBalance == 1.23
        createdAccountDTO.institutionName == "Test Institution"
    }

    def "An account balance can be updated"(){

        given:
        AccountDTO accountDTO = new AccountDTO(name: "Test Account", currentBalance: 2.34, institutionName: "Test Institution")
        def accountJson = JsonOutput.toJson(accountDTO)

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.post("/resources/accounts/1")
                        .contentType("application/json")
                        .content(accountJson)
        ).andReturn().response

        then:
        response.status == HttpStatus.OK.value()

        when:
        response = mvc.perform(
                MockMvcRequestBuilders.get('/resources/accounts/1/balances')
        ).andReturn().response
        List<Map> balanceMaps = new JsonSlurper().parse(response.contentAsByteArray) as List<Map>

        then:
        response.status == HttpStatus.OK.value()
        balanceMaps.size() == 2
        balanceMaps[0].amount == 1.23
        balanceMaps[1].amount == 2.34
        DateUtils.parseDate(balanceMaps[1].date) > DateUtils.parseDate(balanceMaps[0].date)
    }

}
