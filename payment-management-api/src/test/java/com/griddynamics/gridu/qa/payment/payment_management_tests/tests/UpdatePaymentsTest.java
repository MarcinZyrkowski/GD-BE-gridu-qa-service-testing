package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.griddynamics.gridu.qa.payment.steps.PMSteps.arePaymentResponseEqualsRequest;
import static com.griddynamics.gridu.qa.payment.steps.PMSteps.findPaymentInPaymentList;
import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.preparePaymentRequest;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdatePaymentsTest extends PMBaseClass {

    // TODO

    @Test
    @SneakyThrows
    @Transactional
    @Rollback(value = false)
    public void updatePayment() {
        // given
        long userId = userIdRange.randomLong();

        Payment paymentRequest = preparePaymentRequest(userId);
        PaymentModel paymentModelRequest = dtoConverter.convertFrom(paymentRequest);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModelRequest)))
                .thenReturn(PAYMENT_GATEWAY_ACCEPT_TOKEN);

        List<PaymentModel> allPaymentsForUserBeforeSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userId);

        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModelRequest);
        arePaymentResponseEqualsRequest(paymentModelResponse, paymentModelRequest);

        List<PaymentModel> allPaymentsForUserAfterSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userId);

        assertEquals(allPaymentsForUserBeforeSavingNewPayment.size() + 1,
                allPaymentsForUserAfterSavingNewPayment.size());

        Optional<PaymentModel> optionalPaymentModel =
                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterSavingNewPayment);
        assertTrue(optionalPaymentModel.isPresent());

        // when
        allPaymentsForUserAfterSavingNewPayment.get(0).setExpiryYear(LocalDate.now().plusYears(123).getYear());


        paymentManagementService.updatePaymentsForUser(userId, allPaymentsForUserAfterSavingNewPayment);


        // TODO
        System.out.println("x");

//        // then
//        List<PaymentModel> allPaymentsForUserAfterUpdatingPayment =
//                paymentManagementService.getAllPaymentsForUser(userId);
//
//        assertEquals(allPaymentsForUserAfterSavingNewPayment.size(), allPaymentsForUserAfterUpdatingPayment.size());
//
//        Optional<PaymentModel> optionalUpdatedPaymentModel =
//                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterUpdatingPayment);
//
//        assertTrue(optionalUpdatedPaymentModel.isPresent());
    }

}
