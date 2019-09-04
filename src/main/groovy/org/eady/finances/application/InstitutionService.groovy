package org.eady.finances.application

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.institution.Institution

@CompileStatic
interface InstitutionService {

    Collection<Institution> getAllInstitutions();

    Institution createNewInstitution(String institutionName);
}
