package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.griddynamics.gridu.qa.payment.steps.PMSteps.arePaymentResponseEqualsRequest;
import static com.griddynamics.gridu.qa.payment.steps.PMSteps.findPaymentInPaymentList;
import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.preparePaymentRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SavePaymentTest extends PMBaseClass {

    @Test
    @SneakyThrows
    public void savePayment() {
        // given
        long userID = userIdRange.randomLong();

        Payment paymentRequest = preparePaymentRequest(userID);
        PaymentModel paymentModelRequest = dtoConverter.convertFrom(paymentRequest);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModelRequest)))
                .thenReturn(PAYMENT_GATEWAY_ACCEPT_TOKEN);

        List<PaymentModel> allPaymentsForUserBeforeSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userID);

        // when
        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModelRequest);

        // then
        arePaymentResponseEqualsRequest(paymentModelResponse, paymentModelRequest);

        List<PaymentModel> allPaymentsForUserAfterSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userID);

        assertEquals(allPaymentsForUserBeforeSavingNewPayment.size() + 1,
                allPaymentsForUserAfterSavingNewPayment.size());

        Optional<PaymentModel> optionalPaymentModel =
                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterSavingNewPayment);

        assertTrue(optionalPaymentModel.isPresent());
    }

    @Test
    @SneakyThrows
    public void invalidTokenOnSavingPayment() {
        // given
        long userID = userIdRange.randomLong();

        Payment paymentRequest = preparePaymentRequest(userID);
        PaymentModel paymentModelRequest = dtoConverter.convertFrom(paymentRequest);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModelRequest)))
                .thenThrow(new RuntimeException("Invalid token"));

        List<PaymentModel> allPaymentsForUserBeforeSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userID);

        // when
        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModelRequest);

        // then
        arePaymentResponseEqualsRequest(paymentModelResponse, paymentModelRequest);

        List<PaymentModel> allPaymentsForUserAfterSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userID);

        assertEquals(allPaymentsForUserBeforeSavingNewPayment.size() + 1,
                allPaymentsForUserAfterSavingNewPayment.size());

        Optional<PaymentModel> optionalPaymentModel =
                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterSavingNewPayment);

        assertTrue(optionalPaymentModel.isPresent());
        assertEquals(paymentModelResponse.getToken(), PAYMENT_GATEWAY_FAILED_TOKEN);
    }

}
