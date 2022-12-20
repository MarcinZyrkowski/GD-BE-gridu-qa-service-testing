package com.griddynamics.gridu.qa.payment.test_data;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;

import java.time.LocalDate;

public class PaymentManagementData extends TestData {

    protected static final String CARD_HOLDER = "Jane Roe";
    protected static final int DIGITS_IN_CVV = 3;
    protected static final String CVV = createRandomDigitSequence(DIGITS_IN_CVV);
    protected static final int DIGITS_NUMBER_IN_CARD_NUMBER = 16;
    protected static final String CARD_NUMBER = createRandomDigitSequence(DIGITS_NUMBER_IN_CARD_NUMBER);

    public static Payment preparePaymentRequest(long userId) {
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setCardNumber(CARD_NUMBER);
        payment.setCardHolder(CARD_HOLDER);
        payment.setExpiryYear(LocalDate.now().plusYears(2).getYear());
        payment.setExpiryMonth(LocalDate.now().getMonthValue());
        payment.setCvv(CVV);
        return payment;
    }

    public static PaymentModel prepareUpdatedPaymentRequest(PaymentModel paymentModelToUpdate) {
        PaymentModel payment = new PaymentModel();
        payment.setId(payment.getId());
        payment.setUserId(paymentModelToUpdate.getUserId());
        payment.setCardNumber(paymentModelToUpdate.getCardNumber());
        payment.setCardholder(paymentModelToUpdate.getCardholder());
        payment.setExpiryYear(LocalDate.now().plusYears(3).getYear());
        payment.setExpiryMonth(LocalDate.now().plusMonths(2).getMonthValue());
        payment.setCvv(CVV);
        payment.setToken(paymentModelToUpdate.getToken());
        return payment;
    }

}
