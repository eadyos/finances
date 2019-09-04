package org.eady.finances.presentation.dto

import org.eady.finances.domain.model.institution.Institution

class InstitutionDTOMapperSpecification {

    def "An institution can be mapped to an InstitionDTO"(){

        given:
        Institution institution = new Institution(name: "My Institution", institutionId: "1")

        when:
        InstitutionDTO institutionDTO = InstitutionDTOMapper.map(institution)

        then:
        institutionDTO.name == "My Institution"
        institutionDTO.id == "1"
    }
}
