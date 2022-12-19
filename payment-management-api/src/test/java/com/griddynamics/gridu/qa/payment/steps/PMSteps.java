package com.griddynamics.gridu.qa.payment.steps;

import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;

import java.util.List;
import java.util.Optional;

public class PMSteps {

    public static Optional<PaymentModel> findPaymentInPaymentList(PaymentModel payment,
            List<PaymentModel> paymentList) {
        return paymentList.stream()
                .filter(paymentItem -> paymentItem.getId().equals(payment.getId()))
                .filter(paymentItem -> paymentItem.getUserId().equals(payment.getUserId()))
                .filter(paymentItem -> paymentItem.getCardNumber().equals(payment.getCardNumber()))
                .filter(paymentItem -> paymentItem.getCardholder().equals(payment.getCardholder()))
                .filter(paymentItem -> paymentItem.getExpiryYear().equals(payment.getExpiryYear()))
                .filter(paymentItem -> paymentItem.getExpiryMonth().equals(payment.getExpiryMonth()))
                .filter(paymentItem -> paymentItem.getCvv().equals(payment.getCvv()))
                .filter(paymentItem -> paymentItem.getToken().equals(payment.getToken()))
                .findAny();
    }

    public static boolean arePaymentResponseEqualsRequest(PaymentModel paymentResponse, PaymentModel paymentRequest) {

        return paymentResponse.getUserId().equals(paymentRequest.getUserId()) &&
                paymentResponse.getCardNumber().equals(paymentRequest.getCardNumber()) &&
                paymentResponse.getCardholder().equals(paymentRequest.getCardholder()) &&
                paymentResponse.getExpiryMonth().equals(paymentRequest.getExpiryYear()) &&
                paymentResponse.getExpiryMonth().equals(paymentRequest.getExpiryMonth()) &&
                paymentResponse.getCvv().equals(paymentRequest.getCvv());
    }

}
