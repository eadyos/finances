package org.eady.finances.infrastructure.persistence.file

import org.eady.finances.domain.model.account.Account
import org.eady.finances.domain.model.account.Balance
import spock.lang.Specification

class AccountRepositoryFileImplSpecification extends Specification{

    def "An AccountRepositoryFileImpl can be constructed with a file"(){

        when:
        new AccountRepositoryFileImpl(Mock(File))

        then:
        noExceptionThrown()
    }

    def "all accounts can be retrieved"(){

        given:
        AccountRepositoryFileImpl accountRepositoryFileImpl = new AccountRepositoryFileImpl(Mock(File))
        accountRepositoryFileImpl.accountRepositoryFileFacade = accountRepositoryFileFacade

        when:
        Collection<Account> accounts = accountRepositoryFileImpl.findAllAccounts()

        then:
        accounts.size() == 2
        accounts[0].accountId == "1"
        accounts[0].name == "Account One"
        accounts[0].currentBalance.amount == 1.23
        accounts[1].accountId == "2"
    }

    def "an account can be retrieved by accountId"(){

        given:
        AccountRepositoryFileImpl accountRepositoryFileImpl = new AccountRepositoryFileImpl(Mock(File))
        accountRepositoryFileImpl.accountRepositoryFileFacade = accountRepositoryFileFacade

        when:
        Account account = accountRepositoryFileImpl.findAccount("2")

        then:
        account.accountId == "2"
        account.name == "Account Two"
        account.currentBalance.amount == 2.34
    }

    def "a new account can be stored"(){

        given:
        AccountRepositoryFileImpl accountRepositoryFileImpl = new AccountRepositoryFileImpl(Mock(File))
        accountRepositoryFileImpl.accountRepositoryFileFacade = accountRepositoryFileFacade
        Account newAccount = new Account(
                accountId: "3",
                name: "Account Three",
                currentBalance: new Balance(amount: 5.55, date: new Date())
        )

        when:
        accountRepositoryFileImpl.store(newAccount)

        then:
        1 * accountRepositoryFileFacade.saveAccountRepositoryFileDTO(_ as AccountRepositoryFileDTO)
    }

    def "an existing account can be updated"(){

        given:
        AccountRepositoryFileImpl accountRepositoryFileImpl = new AccountRepositoryFileImpl(Mock(File))
        accountRepositoryFileImpl.accountRepositoryFileFacade = accountRepositoryFileFacade
        Balance newBalance = new Balance(amount: 7.89, date: new Date())

        when:
        Account account = accountRepositoryFileImpl.findAccount("1")
        account.currentBalance = newBalance
        accountRepositoryFileImpl.store(account)

        then:
        1 * accountRepositoryFileFacade.saveAccountRepositoryFileDTO(_ as AccountRepositoryFileDTO)
    }

    def mockAccountRepositoryFileDTO = new AccountRepositoryFileDTO(
            accountByAccountId: [
                    "1": new Account(
                            accountId: "1",
                            currentBalance: new Balance(amount: 1.23, date: new Date()),
                            name: "Account One"
                    ),
                    "2": new Account(
                            accountId: "2",
                            currentBalance: new Balance(amount: 2.34, date: new Date()),
                            name: "Account Two"
                    )
            ],
            balancesByAccountId: [
                    "1" : [
                            new Balance(amount: 1.23, date: new Date()),
                            new Balance(amount: 1.11, date: new Date() - 1)
                    ],
                    "2" : [
                            new Balance(amount: 2.34, date: new Date()  )
                    ]
            ]
    )

    def accountRepositoryFileFacade = Mock(AccountRepositoryFileFacade){
        getAccountRepositoryFileDTO() >> mockAccountRepositoryFileDTO
    }

}
