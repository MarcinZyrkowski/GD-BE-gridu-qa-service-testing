package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.griddynamics.gridu.qa.payment.steps.PMSteps.arePaymentResponseEqualsRequest;
import static com.griddynamics.gridu.qa.payment.steps.PMSteps.findPaymentInPaymentList;
import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.preparePaymentRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeletePaymentsTest extends PMBaseClass {

    @Test
    @SneakyThrows
    @Transactional
    @Rollback(value = false)
    public void preparePaymentAndUserForDeleteTest() {
        // given
        long userIdForDeleteTest = userIdRange.randomLong();
        System.out.println(userIdForDeleteTest);

        Payment paymentRequest = preparePaymentRequest(userIdForDeleteTest);
        PaymentModel paymentModelRequest = dtoConverter.convertFrom(paymentRequest);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModelRequest)))
                .thenReturn(PAYMENT_GATEWAY_ACCEPT_TOKEN);

        List<PaymentModel> allPaymentsForUserBeforeSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userIdForDeleteTest);

        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModelRequest);
        arePaymentResponseEqualsRequest(paymentModelResponse, paymentModelRequest);

        List<PaymentModel> allPaymentsForUserAfterSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userIdForDeleteTest);

        assertEquals(allPaymentsForUserBeforeSavingNewPayment.size() + 1,
                allPaymentsForUserAfterSavingNewPayment.size());

        Optional<PaymentModel> optionalPaymentModel =
                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterSavingNewPayment);

        assertTrue(optionalPaymentModel.isPresent());

        // when
        paymentManagementService.deleteAllPaymentsForUser(userIdForDeleteTest);

        // then
        List<PaymentModel> allPaymentsForUserAfterDeletingAllPayments =
                paymentManagementService.getAllPaymentsForUser(userIdForDeleteTest);

        assertEquals(0, allPaymentsForUserAfterDeletingAllPayments.size());
    }

}
