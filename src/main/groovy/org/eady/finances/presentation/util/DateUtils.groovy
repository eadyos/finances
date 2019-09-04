package org.eady.finances.presentation.util

import groovy.transform.CompileStatic

@CompileStatic
class DateUtils {

    static Date parseDate(String date){
        Date.parse("yyyy-MM-dd", date)
    }
}
