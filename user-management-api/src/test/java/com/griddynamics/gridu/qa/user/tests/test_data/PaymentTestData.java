package com.griddynamics.gridu.qa.user.tests.test_data;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.NewPayment;
import com.griddynamics.gridu.qa.user.utils.NumberRange;

import java.time.LocalDate;

public class PaymentTestData extends TestData {

    protected static final int EXPIRY_YEAR = LocalDate.now().plusYears(1).getYear();
    protected static final int EXPIRY_MONTH = LocalDate.now().plusYears(1).getMonthValue();
    protected static final int DIGITS_IN_CVV = 3;
    protected static final String CVV = createRandomDigitSequence(DIGITS_IN_CVV);
    protected static final int DIGITS_NUMBER_IN_CARD_NUMBER = 16;
    protected static final String CARD_NUMBER = createRandomDigitSequence(DIGITS_NUMBER_IN_CARD_NUMBER);
    protected static final NumberRange numberRange = new NumberRange(1, 10000);

    public static CreateUserRequest.Payments preparePayments() {
        return OBJECT_FACTORY.createCreateUserRequestPayments();
    }

    public static void addNewPayment(CreateUserRequest.Payments payments, NewPayment newPayment) {
        payments.getPayment().add(newPayment);
    }

    public static NewPayment prepareNewPayment(String cardHolder) {
        NewPayment payment = OBJECT_FACTORY.createNewPayment();
        payment.setCardNumber(CARD_NUMBER);
        payment.setExpiryYear(EXPIRY_YEAR);
        payment.setExpiryMonth(EXPIRY_MONTH);
        payment.setCardholder(cardHolder);
        payment.setCvv(CVV);
        return payment;
    }

    public static Payment createPaymentResponse() {
        Payment payment = new Payment();
        payment.setCardHolder("CARD_HOLDER");
        payment.setUserId(numberRange.randomLong());
        payment.setId(numberRange.randomLong());
        payment.setCardNumber(CARD_NUMBER);
        payment.setExpiryYear(EXPIRY_YEAR);
        payment.setExpiryMonth(EXPIRY_MONTH);
        payment.setCvv(CVV);
        payment.setVerified(true);
        return payment;
    }

}
