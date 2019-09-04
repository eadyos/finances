package org.eady.finances.presentation.util

class DateUtilsSpecification {

    def "A date can be parsed from a string"(){

        given:
        String dateString = "2019-01-15"

        when:
        def date = DateUtils.parseDate(dateString)

        then:
        date
        date instanceof Date
    }
}
