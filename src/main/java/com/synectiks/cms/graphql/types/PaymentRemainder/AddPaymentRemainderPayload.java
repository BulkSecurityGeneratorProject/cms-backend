package com.synectiks.cms.graphql.types.PaymentRemainder;

import com.synectiks.cms.domain.PaymentRemainder;

public class AddPaymentRemainderPayload extends AbstractPaymentRemainderPayload{
    public AddPaymentRemainderPayload(PaymentRemainder paymentRemainder) {
        super(paymentRemainder);
    }
}
