package org.eady.finances.presentation.util

import spock.lang.Specification

class StringUtilsSpecification extends Specification{

    def "java.util.Date can be formatted to a yyyy-MM-dd date string"(){

        given:
        Date date = Date.parse("yyyy-MM-dd", "2019-01-02")

        when:
        String formattedDate = StringUtils.formatDate(date)

        then:
        formattedDate == "2019-01-02"
    }
}
