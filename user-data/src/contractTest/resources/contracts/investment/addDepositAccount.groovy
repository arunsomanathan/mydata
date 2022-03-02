package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath('/investment/depositaccounts')
        body([
                "id"           : null,
                "bankName"     : "Bank Name",
                "branch"       : "Branch Name",
                "accountNumber": "AccountNumber",
                "balance"      : $(anyDouble()),
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
                "balance"      : $(anyDouble()),
        ])
        headers {
            contentType(applicationJson())
        }
    }
}