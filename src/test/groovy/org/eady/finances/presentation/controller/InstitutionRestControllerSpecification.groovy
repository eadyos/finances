package org.eady.finances.presentation.controller

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.eady.finances.application.config.ApplicationUnitTestConfiguration
import org.eady.finances.presentation.config.PresentationConfiguration
import org.eady.finances.presentation.dto.InstitutionDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@ContextConfiguration(loader = AnnotationConfigContextLoader, classes = [PresentationConfiguration, ApplicationUnitTestConfiguration] )
class InstitutionRestControllerSpecification extends Specification {

    @Autowired
    InstitutionRestController institutionRestController

    MockMvc mvc
    def setup(){
        mvc = MockMvcBuilders.standaloneSetup(institutionRestController).build()
    }

    def "GET institutions, returns institutions json and a 200 status"() {

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.get('/resources/institutions/')
        ).andReturn().response
        List<Map> institutionMaps = new JsonSlurper().parse(response.contentAsByteArray) as List<Map>

        then:
        response.status == HttpStatus.OK.value()
        institutionMaps.size() == 1
        institutionMaps[0].id == "testInstitutionID"
        institutionMaps[0].name == "Test Institution"
    }

    def "POST institutions, returns a new institution and a 201 status code"(){

        given:
        InstitutionDTO institutionDTO = new InstitutionDTO(name: "My Bank")
        def institutionJson = JsonOutput.toJson(institutionDTO)

        when:
        def response = mvc.perform(
                MockMvcRequestBuilders.post("/resources/institutions/")
                        .contentType("application/json")
                        .content(institutionJson)
        ).andReturn().response
        InstitutionDTO createdInstitutionDTO = new JsonSlurper().parse(response.contentAsByteArray) as InstitutionDTO

        then:
        response.status == HttpStatus.CREATED.value()
        createdInstitutionDTO.id
        createdInstitutionDTO.name == "My Bank"
    }

}
