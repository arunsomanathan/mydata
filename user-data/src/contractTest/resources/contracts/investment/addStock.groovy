package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath('/investment/stocks')
        body([
                "id"           : null,
                "stockCode"    : "Stock Code",
                "stockName"    : "Stock Name",
                "stockExchange": "Stock Exchange",
                "broker"       : "Stock Broker"
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"            : $(anyPositiveInt()),
                "stockCode"    : "Stock Code",
                "stockName"    : "Stock Name",
                "stockExchange": "Stock Exchange",
                "broker"       : "Stock Broker"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}