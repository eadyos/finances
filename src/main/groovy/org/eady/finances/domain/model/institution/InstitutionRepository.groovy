package org.eady.finances.domain.model.institution

import groovy.transform.CompileStatic

@CompileStatic
interface InstitutionRepository {

    Collection<Institution> findAllInstitutions()

    void store(Institution institution)

    Institution findInstitution(String institutionId)

    Institution findInstitutionByName(String institutionName)
}
