package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import com.griddynamics.gridu.qa.payment.utils.NumberRange;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.griddynamics.gridu.qa.payment.steps.PMSteps.arePaymentResponseEqualsRequest;
import static com.griddynamics.gridu.qa.payment.steps.PMSteps.findPaymentInPaymentList;
import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.preparePaymentRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentManagementTest extends PMBaseClass {

    protected final String PAYMENT_GATEWAY_ACCEPT_TOKEN = "accepted_card_token";
    protected NumberRange userIdRange = new NumberRange(1, 99);

    @Test
    public void getAllPaymentForUser() {
        long randomUserID = userIdRange.randomLong();

        List<PaymentModel> allPaymentsForUser = paymentManagementService.getAllPaymentsForUser(randomUserID);

        assertNotNull(allPaymentsForUser,
                "Returned payments list for user with ID: " + randomUserID + "%s should not be null");
    }

    @Test
    @SneakyThrows
    public void savePayment() {
        long userID = userIdRange.randomLong();

        Payment paymentRequest = preparePaymentRequest(userID);
        PaymentModel paymentModelRequest = dtoConverter.convertFrom(paymentRequest);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModelRequest))).thenReturn(
                PAYMENT_GATEWAY_ACCEPT_TOKEN);

        List<PaymentModel> allPaymentsForUserBeforeSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userID);

        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModelRequest);
        arePaymentResponseEqualsRequest(paymentModelResponse, paymentModelRequest);

        List<PaymentModel> allPaymentsForUserAfterSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userID);

        assertEquals(allPaymentsForUserBeforeSavingNewPayment.size() + 1,
                allPaymentsForUserAfterSavingNewPayment.size());


        Optional<PaymentModel> optionalPaymentModel =
                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterSavingNewPayment);

        assertTrue(optionalPaymentModel.isPresent());
    }

}
