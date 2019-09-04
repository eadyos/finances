package org.eady.finances.infrastructure.persistence.file

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonOutput
import groovy.transform.CompileStatic

@CompileStatic
class AccountRepositoryFileFacade {

    private File accountRepositoryFile

    AccountRepositoryFileFacade(File file) {
        accountRepositoryFile = file
        if (accountRepositoryFile.createNewFile()) {
            accountRepositoryFile.text = JsonOutput.toJson(new AccountRepositoryFileDTO())
        }
    }

    AccountRepositoryFileDTO getAccountRepositoryFileDTO() {
        ObjectMapper mapper = new ObjectMapper()
        AccountRepositoryFileDTO accountRepositoryFileDTO = mapper.readValue(accountRepositoryFile, AccountRepositoryFileDTO)
        accountRepositoryFileDTO
    }

    void saveAccountRepositoryFileDTO(AccountRepositoryFileDTO accountRepositoryFileDTO) {
        String json = JsonOutput.toJson(accountRepositoryFileDTO)
        accountRepositoryFile.text = json
    }
}
