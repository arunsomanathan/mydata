package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(GET())
        urlPath('/investments/mutualfunds/transactions/buy')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"       : $(anyPositiveInt()),
                "mfId"     : $(anyPositiveInt()),
                "nav"      : $(anyDouble()),
                "units"    : $(anyDouble()),
                "charge"   : $(anyDouble()),
                "buyDate"  : $(iso8601WithOffset()),
                "soldUnits": $(anyDouble()),
                "isSoldOut": $(anyBoolean())
        ])
        headers {
            contentType(applicationJson())
        }
    }
}