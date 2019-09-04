package org.eady.finances.presentation.controller

import groovy.transform.CompileStatic
import org.eady.finances.application.InstitutionService
import org.eady.finances.domain.model.institution.Institution
import org.eady.finances.presentation.dto.InstitutionDTO
import org.eady.finances.presentation.dto.InstitutionDTOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("resources/institutions")
@CompileStatic
class InstitutionRestController {

    @Autowired
    InstitutionService institutionService

    @GetMapping("/")
    Collection<InstitutionDTO> getAllInstitutions() {
        institutionService.getAllInstitutions().collect { institution ->
            InstitutionDTOMapper.map(institution)
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    InstitutionDTO createAccount(@RequestBody InstitutionDTO institutionDTO) {
        Institution institution = institutionService.createNewInstitution(institutionDTO.name)
        InstitutionDTOMapper.map(institution)
    }

}
