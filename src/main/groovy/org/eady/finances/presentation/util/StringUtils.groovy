package org.eady.finances.presentation.util

import groovy.transform.CompileStatic

@CompileStatic
class StringUtils {

    static String formatDate(Date date){
        date.format("yyyy-MM-dd")
    }

}
