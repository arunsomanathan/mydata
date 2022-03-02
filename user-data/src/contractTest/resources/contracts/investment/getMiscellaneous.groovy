package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(GET())
        urlPath('/investment/miscellaneousaccounts')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"            : $(anyPositiveInt()),
                "investmentName": $(anyNonBlankString()),
                "balance"       : $(anyDouble())
        ])
        headers {
            contentType(applicationJson())
        }
    }
}