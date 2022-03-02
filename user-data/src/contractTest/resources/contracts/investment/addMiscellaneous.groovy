package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath('/investment/miscellaneousaccounts')
        body([
                "id"            : null,
                "investmentName": "Investment Name",
                "balance"       : $(anyDouble())
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"            : $(anyPositiveInt()),
                "investmentName": "Investment Name",
                "balance"       : $(anyDouble())
        ])
        headers {
            contentType(applicationJson())
        }
    }
}