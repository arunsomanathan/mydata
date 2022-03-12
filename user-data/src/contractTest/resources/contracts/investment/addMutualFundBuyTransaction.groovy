package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath('/investments/mutualfunds/transactions/buy')
        body([
                "id"       : $(anyPositiveInt()),
                "mfId"     : 1,
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
    response {
        status(OK())
        body([
                "id"       : $(anyPositiveInt()),
                "mfId"     : 1,
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