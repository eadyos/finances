package org.eady.finances.infrastructure.persistence.file

import spock.lang.Specification

class InstitutionRepositoryFileFacadeSpecification extends Specification {

    def "InstitutionRepositoryFileFacade can be constructed with a file that does  not yet exist"(){

        given:
        File newFile = new File("institutionRepo.txt")
        newFile.delete()
        assert !newFile.exists()
        newFile.deleteOnExit()

        when:
        new InstitutionRepositoryFileFacade(newFile)

        then:
        newFile.exists()
    }

    def "InstitutionRepositoryFileFacade can be constructed with a file that already exists and will not overwrite it"(){

        given:
        String sampleText = "xyzzy"
        File existingFile = new File("institutionRepo.txt")
        existingFile.text = sampleText
        existingFile.deleteOnExit()

        when:
        new InstitutionRepositoryFileFacade(existingFile)

        then:
        existingFile.text == sampleText
    }
}
