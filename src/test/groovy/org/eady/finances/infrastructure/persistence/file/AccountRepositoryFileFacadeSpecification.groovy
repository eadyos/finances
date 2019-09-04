package org.eady.finances.infrastructure.persistence.file

import spock.lang.Specification

class AccountRepositoryFileFacadeSpecification extends Specification{

    def "AccountRepositoryFileFacade can be constructed with a file that does  not yet exist"(){

        given:
        File newFile = new File("testFile.txt")
        newFile.delete()
        assert !newFile.exists()
        newFile.deleteOnExit()

        when:
        new AccountRepositoryFileFacade(newFile)

        then:
        newFile.exists()
    }

    def "AccountRepositoryFileFacade can be constructed with a file that already exists and will not overwrite it"(){

        given:
        String sampleText = "xyzzy"
        File existingFile = new File("testFile.txt")
        existingFile.text = sampleText
        existingFile.deleteOnExit()

        when:
        new AccountRepositoryFileFacade(existingFile)

        then:
        existingFile.text == sampleText
    }

}
