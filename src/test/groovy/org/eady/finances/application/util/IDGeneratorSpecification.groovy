package org.eady.finances.application.util

import spock.lang.Specification

class IDGeneratorSpecification extends Specification{

    def "The IDGenerator can generate an accountId"(){

        when:
        String id = IDGenerator.generateAccountId()

        then:
        !id.isEmpty()
    }

    def "The IDGenerator can generate an InstitutionId"(){

        when:
        String id = IDGenerator.generateInstitutionId()

        then:
        !id.isEmpty()
    }
}

