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
    protected static final String TOKEN = "accepted_bootstrap_token";

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

    public static Payment prepareUpdatedPaymentRequest(PaymentModel paymentModelToUpdate) {
        Payment payment = new Payment();
        payment.setId(paymentModelToUpdate.getId());
        payment.setUserId(paymentModelToUpdate.getUserId());
        payment.setCardNumber(paymentModelToUpdate.getCardNumber());
        payment.setCardHolder(paymentModelToUpdate.getCardholder());
        payment.setExpiryYear(LocalDate.now().plusYears(145).getYear());
        payment.setExpiryMonth(LocalDate.now().plusMonths(2).getMonthValue());
        payment.setCvv(CVV);
        return payment;
    }

    public static PaymentModel bootstrapPaymentModel(long userId) {
        PaymentModel payment = new PaymentModel();
        payment.setUserId(userId);
        payment.setCardNumber(CARD_NUMBER);
        payment.setCardholder(CARD_HOLDER);
        payment.setExpiryYear(LocalDate.now().plusYears(3).getYear());
        payment.setExpiryMonth(LocalDate.now().plusMonths(2).getMonthValue());
        payment.setCvv(CVV);
        payment.setToken(TOKEN);
        return payment;
    }

}
