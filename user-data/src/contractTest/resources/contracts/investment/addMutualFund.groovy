package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(POST())
        urlPath('/investment/mutualfunds')
        body([
                "id"    : null,
                "mfCode": "Mutual Fund Code",
                "mfName": "Mutual Fund Name",
                "amc"   : "Mutual Fund AMC",
                "type"  : "Mutual Fund type"
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"            : $(anyPositiveInt()),
                "mfCode": "Mutual Fund Code",
                "mfName": "Mutual Fund Name",
                "amc"   : "Mutual Fund AMC",
                "type"  : "Mutual Fund type"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}