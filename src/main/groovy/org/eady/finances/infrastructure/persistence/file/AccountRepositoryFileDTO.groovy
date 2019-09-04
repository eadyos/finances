package org.eady.finances.infrastructure.persistence.file

import groovy.transform.CompileStatic
import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.Balance

@CompileStatic
class AccountRepositoryFileDTO {

    Map<String, Account> accountByAccountId = [:]
    Map<String, Collection<Balance>> balancesByAccountId = [:]
}
