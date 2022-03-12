package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(GET())
        urlPath('/investments/stocks')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"           : $(anyPositiveInt()),
                "stockCode"    : $(anyNonBlankString()),
                "stockName"    : $(anyNonBlankString()),
                "stockExchange": $(anyNonBlankString()),
                "broker"       : $(anyNonBlankString())
        ])
        headers {
            contentType(applicationJson())
        }
    }
}