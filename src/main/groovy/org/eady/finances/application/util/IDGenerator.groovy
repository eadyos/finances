package org.eady.finances.application.util

import groovy.transform.CompileStatic

@CompileStatic
class IDGenerator {

    static String generateAccountId(){
        getRandomId()
    }

    static String generateInstitutionId(){
        getRandomId()
    }

    static private String getRandomId(){
        UUID.randomUUID().toString()
    }
}
