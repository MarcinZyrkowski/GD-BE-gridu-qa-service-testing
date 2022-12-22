package com.griddynamics.gridu.qa.user.tf.steps;

import com.griddynamics.gridu.qa.user.*;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

public class PaymentSteps {

    @Step("Verify payment lists")
    public static void verifyUpdatedPaymentLists(CreateUserResponse responseWithActualPayments,
            CreateUserRequest requestWithExpectedPayments) {

        if (requestWithExpectedPayments.getPayments() == null ||
                requestWithExpectedPayments.getPayments().getPayment() == null) {

            Assertions.assertThat(responseWithActualPayments.getUserDetails().getPayments().getPayment().isEmpty())
                    .isTrue();
        } else {
            comparePaymentLists(responseWithActualPayments.getUserDetails().getPayments().getPayment(),
                    requestWithExpectedPayments.getPayments().getPayment());
        }
    }

    @Step("Verify updated payment lists")
    public static void verifyUpdatedPaymentLists(UserDetails userDetailsWithActualPayments,
            UserDetails userDetailsWithExpectedPayments) {

        if (userDetailsWithExpectedPayments.getPayments() == null ||
                userDetailsWithExpectedPayments.getPayments().getPayment() == null) {

            Assertions.assertThat(userDetailsWithActualPayments.getPayments().getPayment().isEmpty())
                    .isTrue();
        } else {
            compareExistingPaymentLists(userDetailsWithActualPayments.getPayments().getPayment(),
                    userDetailsWithExpectedPayments.getPayments().getPayment());
        }
    }

    @Step("Compare payment lists")
    public static void comparePaymentLists(List<ExistingPayment> actualExistingPaymentsList,
            List<NewPayment> expectedNewPaymentsList) {

        actualExistingPaymentsList.forEach(payment -> Assertions.assertThat(payment.getId())
                .as("Created payment should have generated id value")
                .isNotNull());

        expectedNewPaymentsList.forEach(payment ->
                Assertions.assertThat(
                                findCorrespondingExistingPaymentByNewPayment(actualExistingPaymentsList, payment).isPresent())
                        .as("Existing payment corresponding to new payment should be found")
                        .isTrue());

        Assertions.assertThat(actualExistingPaymentsList.size())
                .as("New payment list size and Existing payment list size should be the same")
                .isEqualTo(expectedNewPaymentsList.size());
    }

    @Step("Find corresponding existing payment by new payment")
    public static Optional<ExistingPayment> findCorrespondingExistingPaymentByNewPayment(
            List<ExistingPayment> existingPaymentList, NewPayment newPayment) {
        return existingPaymentList.stream()
                .filter(existingPayment -> existingPayment.getCardNumber().equals(newPayment.getCardNumber()))
                .filter(existingPayment -> existingPayment.getExpiryYear() == newPayment.getExpiryYear())
                .filter(existingPayment -> existingPayment.getExpiryMonth() == newPayment.getExpiryMonth())
                .filter(existingPayment -> existingPayment.getCardholder().equals(newPayment.getCardholder()))
                .filter(existingPayment -> existingPayment.getCvv().equals(newPayment.getCvv()))
                .findAny();
    }

    @Step("Compare existing payment lists")
    public static void compareExistingPaymentLists(List<ExistingPayment> actualExistingPaymentsList,
            List<ExistingPayment> expectedPaymentDataList) {

        actualExistingPaymentsList.forEach(payment -> Assertions.assertThat(payment.getId())
                .as("Existing payment should have generated id value")
                .isNotNull());

        expectedPaymentDataList.forEach(payment ->
                Assertions.assertThat(findCorrespondingExistingPaymentByNewPayment(actualExistingPaymentsList,
                        payment).isPresent())
                        .as("Existing payment corresponding to new payment should be found")
                        .isTrue());

        Assertions.assertThat(actualExistingPaymentsList.size())
                .as("New payment list size and Existing payment list size should be the same")
                .isEqualTo(expectedPaymentDataList.size());
    }

}
