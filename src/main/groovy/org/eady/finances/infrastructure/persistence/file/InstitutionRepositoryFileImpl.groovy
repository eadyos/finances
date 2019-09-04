package org.eady.finances.infrastructure.persistence.file

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.domain.model.institution.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@CompileStatic
@Repository
class InstitutionRepositoryFileImpl implements InstitutionRepository{

    private InstitutionRepositoryFileFacade institutionRepositoryFileFacade

    @Autowired
    InstitutionRepositoryFileImpl(File institutionRepositoryFile){
        institutionRepositoryFileFacade = new InstitutionRepositoryFileFacade(institutionRepositoryFile)
    }

    @Override
    Collection<Institution> findAllInstitutions() {
        institutionRepositoryFileFacade.institutionRepositoryFileDTO.institutionByInstitutionId.values()
    }

    @Override
    void store(Institution institution) {
        InstitutionRepositoryFileDTO existingFileContent = institutionRepositoryFileFacade.institutionRepositoryFileDTO
        existingFileContent.institutionByInstitutionId[institution.institutionId] = institution
        institutionRepositoryFileFacade.saveInstitutionRepositoryFileDTO(existingFileContent)
    }

    @Override
    Institution findInstitution(String institutionId) {
        institutionRepositoryFileFacade.institutionRepositoryFileDTO.institutionByInstitutionId[institutionId]
    }

    @Override
    Institution findInstitutionByName(String institutionName) {
        institutionRepositoryFileFacade.institutionRepositoryFileDTO.institutionByInstitutionId.values().find { institution ->
            institution.name == institutionName
        }
    }
}
