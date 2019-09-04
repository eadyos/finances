package org.eady.finances.application.impl


import org.eady.finances.application.config.ApplicationUnitTestConfiguration
import org.eady.finances.domain.model.institution.Institution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification

@ContextConfiguration(loader = AnnotationConfigContextLoader, classes = [ApplicationUnitTestConfiguration])
class InstitutionServiceImplSpecification extends Specification {

    @Autowired
    InstitutionServiceImpl institutionService

    def "The institution service can return all institutions"() {

        when:
        def institutions = institutionService.getAllInstitutions()

        then:
        institutions.size() == 1
    }

    def "The institution service can create a new institution"() {

        given:
        String institutionName = "institution two"

        when:
        Institution institution = institutionService.createNewInstitution(institutionName)

        then:
        institution.name == institutionName
        institution.institutionId
    }
}
