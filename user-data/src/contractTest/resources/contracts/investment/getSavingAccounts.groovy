package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(GET())
        urlPath('/investment/savingaccounts')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([[
                "id"           : $(anyPositiveInt()),
                "bankName"     : $(anyNonBlankString()),
                "branch"       : $(anyNonBlankString()),
                "accountNumber": $(anyNonBlankString()),
                "balance"      : $(anyDouble()),
        ]])
        headers {
            contentType(applicationJson())
        }
    }
}