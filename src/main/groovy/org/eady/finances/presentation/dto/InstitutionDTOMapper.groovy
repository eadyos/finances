package org.eady.finances.presentation.dto

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.institution.Institution

@CompileStatic
class InstitutionDTOMapper {

    static InstitutionDTO map(Institution institution){
        new InstitutionDTO(name: institution.name, id: institution.institutionId)
    }
}
