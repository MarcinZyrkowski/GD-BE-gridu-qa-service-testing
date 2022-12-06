package com.griddynamics.gridu.qa.user.all_tests.test_data;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.NewPayment;
import com.griddynamics.gridu.qa.user.utils.NumberRange;
import io.qameta.allure.Step;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.Collections;

public class PaymentTestData extends TestData {

    protected static final int EXPIRY_YEAR = LocalDate.now().plusYears(1).getYear();
    protected static final int EXPIRY_YEAR_2 = LocalDate.now().plusYears(2).getYear();
    protected static final int EXPIRY_MONTH = LocalDate.now().plusYears(1).getMonthValue();
    protected static final int EXPIRY_MONTH_2 = LocalDate.now().plusYears(1).plusMonths(1).getMonthValue();
    protected static final int DIGITS_IN_CVV = 3;
    protected static final String CARD_HOLDER = "John Doe";
    protected static final String CVV = createRandomDigitSequence(DIGITS_IN_CVV);
    protected static final String CVV_2 = createRandomDigitSequence(DIGITS_IN_CVV);
    protected static final int DIGITS_NUMBER_IN_CARD_NUMBER = 16;
    protected static final String CARD_NUMBER = createRandomDigitSequence(DIGITS_NUMBER_IN_CARD_NUMBER);
    protected static final NumberRange numberRange = new NumberRange(1, 10000);

    @Step("Prepare payments")
    public static CreateUserRequest.Payments preparePayments() {
        return OBJECT_FACTORY.createCreateUserRequestPayments();
    }

    @Step("Add new payment")
    public static void addNewPayment(CreateUserRequest.Payments payments, NewPayment newPayment) {
        payments.getPayment().add(newPayment);
    }

    @Step("Prepare new payment")
    public static NewPayment prepareNewPayment(String cardHolder) {
        NewPayment payment = OBJECT_FACTORY.createNewPayment();
        payment.setCardNumber(CARD_NUMBER);
        payment.setExpiryYear(EXPIRY_YEAR);
        payment.setExpiryMonth(EXPIRY_MONTH);
        payment.setCardholder(cardHolder);
        payment.setCvv(CVV);
        return payment;
    }

    @Step("Create payment response")
    public static Payment createPaymentResponse() {
        Payment payment = new Payment();
        payment.setCardHolder(CARD_HOLDER);
        payment.setUserId(numberRange.randomLong());
        payment.setId(numberRange.randomLong());
        payment.setCardNumber(CARD_NUMBER);
        payment.setExpiryYear(EXPIRY_YEAR_2);
        payment.setExpiryMonth(EXPIRY_MONTH_2);
        payment.setCvv(CVV_2);
        payment.setVerified(true);
        return payment;
    }

    @SneakyThrows
    @Step("Create payment response as JSON")
    public static String createPaymentResponseJSON() {
        return objectMapper.writeValueAsString(Collections.singletonList(createPaymentResponse()));
    }

}
