package org.eady.finances.infrastructure.persistence.file

import org.eady.finances.domain.model.institution.Institution
import spock.lang.Specification

class InstitutionRepositoryFileImplSpecification extends Specification{


    def "An InstitutionRepositoryFileImpl can be constructed with a file"(){

        when:
        new InstitutionRepositoryFileImpl(Mock(File))

        then:
        noExceptionThrown()
    }

    def "all institutions can be returned"(){

        given:
        InstitutionRepositoryFileImpl institutionRepositoryFileImpl = new InstitutionRepositoryFileImpl(Mock(File))
        institutionRepositoryFileImpl.institutionRepositoryFileFacade = mockInstitutionRepositoryFileFacade

        when:
        Collection<Institution> institutions = institutionRepositoryFileImpl.findAllInstitutions()

        then:
        institutions.size() == 2
        institutions[0].institutionId == "1"
        institutions[0].name == "Institute One"
        institutions[1].institutionId == "2"
        institutions[1].name == "Institute Two"
    }

    def "an institution can be found by its institutionId"(){

        given:
        InstitutionRepositoryFileImpl institutionRepositoryFileImpl = new InstitutionRepositoryFileImpl(Mock(File))
        institutionRepositoryFileImpl.institutionRepositoryFileFacade = mockInstitutionRepositoryFileFacade

        when:
        Institution institution = institutionRepositoryFileImpl.findInstitution("2")

        then:
        institution.institutionId == "2"
        institution.name == "Institute Two"
    }

    def "an institution can be found by its name"(){
        given:
        InstitutionRepositoryFileImpl institutionRepositoryFileImpl = new InstitutionRepositoryFileImpl(Mock(File))
        institutionRepositoryFileImpl.institutionRepositoryFileFacade = mockInstitutionRepositoryFileFacade

        when:
        Institution institution = institutionRepositoryFileImpl.findInstitutionByName("Institute One")

        then:
        institution.institutionId == "1"
        institution.name == "Institute One"
    }

    def "an institution can be stored"(){

        given:
        InstitutionRepositoryFileImpl institutionRepositoryFileImpl = new InstitutionRepositoryFileImpl(Mock(File))
        institutionRepositoryFileImpl.institutionRepositoryFileFacade = mockInstitutionRepositoryFileFacade
        Institution institution = new Institution(institutionId: "3", name: "Institution Three")

        when:
        institutionRepositoryFileImpl.store(institution)

        then:
        1 * mockInstitutionRepositoryFileFacade.saveInstitutionRepositoryFileDTO(_ as InstitutionRepositoryFileDTO)
    }

    def mockInstitutionRepositoryFileDTO = Mock(InstitutionRepositoryFileDTO){
        getInstitutionByInstitutionId() >> [
                "1" : new Institution(institutionId: "1", name: "Institute One"),
                "2" : new Institution(institutionId: "2", name: "Institute Two"),
        ]
    }

    def mockInstitutionRepositoryFileFacade = Mock(InstitutionRepositoryFileFacade){
        getInstitutionRepositoryFileDTO() >> mockInstitutionRepositoryFileDTO
    }

}
