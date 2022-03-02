package contracts.investment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method(GET())
        urlPath('/investment/mutualfunds')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status(OK())
        body([
                "id"            : $(anyPositiveInt()),
                "mfCode": $(anyNonBlankString()),
                "mfName": $(anyNonBlankString()),
                "amc"   : $(anyNonBlankString()),
                "type"  : $(anyNonBlankString())
        ])
        headers {
            contentType(applicationJson())
        }
    }
}