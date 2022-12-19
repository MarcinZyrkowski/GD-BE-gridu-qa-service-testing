package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import com.griddynamics.gridu.qa.payment.utils.NumberRange;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.griddynamics.gridu.qa.payment.steps.PMSteps.arePaymentResponseEqualsRequest;
import static com.griddynamics.gridu.qa.payment.steps.PMSteps.findPaymentInPaymentList;
import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.preparePaymentRequest;
import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.prepareUpdatedPaymentRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentManagementTest extends PMBaseClass {

    protected final String PAYMENT_GATEWAY_ACCEPT_TOKEN = "accepted_card_token";
    protected NumberRange userIdRange = new NumberRange(1, 99);

    // delete test
    private static long userIdForDeleteTest;
    // update test
    private static long userIdForUpdateTest;
    private static PaymentModel paymentModelRequest;
    private static List<PaymentModel> allPaymentsForUserAfterSavingNewPayment;
    private static PaymentModel paymentModelUpdateRequest;

    @Test
    public void getAllPaymentForUser() {
        // given
        long randomUserID = userIdRange.randomLong();

        // when
        List<PaymentModel> allPaymentsForUser = paymentManagementService.getAllPaymentsForUser(randomUserID);

        // then
        assertNotNull(allPaymentsForUser,
                "Returned payments list for user with ID: " + randomUserID + "%s should not be null");
    }

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


    @Order(1)
    @Test
    @SneakyThrows
    public void preparePaymentAndUserForDeleteTest() {
        // given
        userIdForDeleteTest = userIdRange.randomLong();

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
    }

    @Test
    @Transactional
    @SneakyThrows
    @Order(2)
    public void deleteAllPaymentsForUser() {
        // when
        paymentManagementService.deleteAllPaymentsForUser(userIdForDeleteTest);
    }

    @Test
    @Order(3)
    public void verifyAllPaymentsAreDeletedForUser() {
        // then
        List<PaymentModel> allPaymentsForUserAfterDeletingAllPayments =
                paymentManagementService.getAllPaymentsForUser(userIdForDeleteTest);

        assertEquals(0, allPaymentsForUserAfterDeletingAllPayments.size());
    }

    @Test
    @Order(4)
    @SneakyThrows
    public void preparePaymentAndUserForUpdatePayment() {
        // given
        userIdForUpdateTest = userIdRange.randomLong();

        Payment paymentRequest = preparePaymentRequest(userIdForUpdateTest);
        paymentModelRequest = dtoConverter.convertFrom(paymentRequest);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModelRequest)))
                .thenReturn(PAYMENT_GATEWAY_ACCEPT_TOKEN);

        List<PaymentModel> allPaymentsForUserBeforeSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userIdForUpdateTest);

        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModelRequest);
        arePaymentResponseEqualsRequest(paymentModelResponse, paymentModelRequest);

        allPaymentsForUserAfterSavingNewPayment =
                paymentManagementService.getAllPaymentsForUser(userIdForUpdateTest);

        assertEquals(allPaymentsForUserBeforeSavingNewPayment.size() + 1,
                allPaymentsForUserAfterSavingNewPayment.size());

        Optional<PaymentModel> optionalPaymentModel =
                findPaymentInPaymentList(paymentModelResponse, allPaymentsForUserAfterSavingNewPayment);
        assertTrue(optionalPaymentModel.isPresent());
    }

    @Test
    @Order(5)
    @SneakyThrows
    @Transactional
    public void updatePaymentsForUser() {
        // when
        Payment paymentRequestUpdated = prepareUpdatedPaymentRequest(paymentModelRequest);
        paymentModelUpdateRequest = dtoConverter.convertFrom(paymentRequestUpdated);
        paymentManagementService.updatePaymentsForUser(userIdForUpdateTest,
                Collections.singletonList(paymentModelUpdateRequest));
    }

    @Test
    @Order(6)
    public void verifyUpdatedPayment() {
        // then
        List<PaymentModel> allPaymentsForUserAfterUpdatingPayment =
                paymentManagementService.getAllPaymentsForUser(userIdForUpdateTest);

        assertEquals(allPaymentsForUserAfterSavingNewPayment.size(), allPaymentsForUserAfterUpdatingPayment.size());

        Optional<PaymentModel> optionalUpdatedPaymentModel =
                findPaymentInPaymentList(paymentModelUpdateRequest, allPaymentsForUserAfterUpdatingPayment);

        assertTrue(optionalUpdatedPaymentModel.isPresent());
    }

}
