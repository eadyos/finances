package org.eady.finances.infrastructure.persistence.file

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonOutput

class InstitutionRepositoryFileFacade {

    private File institutionRepositoryFile

    InstitutionRepositoryFileFacade(File file) {
        institutionRepositoryFile = file
        if (institutionRepositoryFile.createNewFile()) {
            institutionRepositoryFile.text = JsonOutput.toJson(new InstitutionRepositoryFileDTO())
        }
    }

    InstitutionRepositoryFileDTO getInstitutionRepositoryFileDTO() {
        ObjectMapper objectMapper = new ObjectMapper()
        objectMapper.readValue(institutionRepositoryFile, InstitutionRepositoryFileDTO)
    }

    void saveInstitutionRepositoryFileDTO(InstitutionRepositoryFileDTO institutionRepositoryFileDTO) {
        String json = JsonOutput.toJson(institutionRepositoryFileDTO)
        institutionRepositoryFile.text = json
    }

}
