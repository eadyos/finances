package org.eady.finances.infrastructure.persistence.file

import org.eady.finances.domain.model.institution.Institution

class InstitutionRepositoryFileDTO {

    Map<String, Institution> institutionByInstitutionId = [:]

}
