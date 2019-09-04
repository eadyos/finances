package org.eady.finances.presentation.controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.eady.finances.application.config.ApplicationUnitTestConfiguration
import org.eady.finances.presentation.config.PresentationConfiguration
import org.eady.finances.presentation.dto.AccountDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@ContextConfiguration(loader = AnnotationConfigContextLoader, classes = [PresentationConfiguration, ApplicationUnitTestConfiguration] )
class AccountRestControllerSpecification extends Specification {

    @Autowired
    AccountRestController accountRestController

    MockMvc mvc
    def setup(){
        mvc = MockMvcBuilders.standaloneSetup(accountRestController).build()
    }

    def "GET accounts, returns accounts json and a 200 status"() {

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.get('/resources/accounts/')
        ).andReturn().response
        List<Map> accountMaps = new JsonSlurper().parse(response.contentAsByteArray) as List<Map>

        then:
        response.status == HttpStatus.OK.value()
        accountMaps.size() == 2
        accountMaps[0].accountId == "testAccountId1"
        accountMaps[0].name == "Test Account"
    }

    def "GET {accountId}/balances returns the balance history json and a 200 status"(){

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

    def "POST accounts, returns a new account and a 201 status code"(){

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

    def "POST accounts/{accountId} can update an account name and currentBalance and return HTTP OK/200"(){

        given:
        AccountDTO accountDTO = new AccountDTO(name: "Test Account", currentBalance: 1.23, institutionName: "Test Institution")
        def accountJson = JsonOutput.toJson(accountDTO)

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.post("/resources/accounts/123")
                        .contentType("application/json")
                        .content(accountJson)
        ).andReturn().response

        then:
        response.status == HttpStatus.OK.value()
    }

}
