package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath('/investments/loanaccounts')
        body([
                "id"           : null,
                "bankName"     : "Bank Name",
                "branch"       : "Branch Name",
                "accountNumber": "AccountNumber",
                "balance"      : $(regex("-(\\d*\\.\\d+)"))
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"           : $(anyPositiveInt()),
                "bankName"     : "Bank Name",
                "branch"       : "Branch Name",
                "accountNumber": "AccountNumber",
                "balance"      : $(regex("-(\\d*\\.\\d+)"))
        ])
        headers {
            contentType(applicationJson())
        }
    }
}