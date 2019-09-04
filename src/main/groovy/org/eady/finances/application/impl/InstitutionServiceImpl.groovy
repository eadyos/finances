package org.eady.finances.application.impl

import groovy.transform.CompileStatic
import org.eady.finances.application.InstitutionService
import org.eady.finances.application.util.IDGenerator
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.domain.model.institution.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@CompileStatic
@Service
class InstitutionServiceImpl implements InstitutionService{

    @Autowired
    private InstitutionRepository institutionRepository

    @Override
    Collection<Institution> getAllInstitutions() {
        institutionRepository.findAllInstitutions()
    }

    @Override
    Institution createNewInstitution(String institutionName) {
        Institution newInstitution =  new Institution(
                institutionId: IDGenerator.generateInstitutionId(),
                name: institutionName
        )
        institutionRepository.store(newInstitution)
        newInstitution
    }
}
