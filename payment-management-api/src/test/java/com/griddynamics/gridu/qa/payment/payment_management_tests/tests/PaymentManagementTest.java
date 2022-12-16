package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentManagementTest extends PMBaseClass {

    @Test
    @SneakyThrows
    public void savePayment() {

        Payment payment = new Payment();
        payment.setUserId(1L);
        payment.setCardNumber("1111222233334444");
        payment.setCardHolder("John Doe");
        payment.setExpiryYear(2024);
        payment.setExpiryMonth(2);
        payment.setCvv("342");

        PaymentModel paymentModel = dtoConverter.convertFrom(payment);
        when(cardApi.verifyCard(dtoConverter.convertToCard(paymentModel))).thenReturn("xxc");

        PaymentModel paymentModelResponse = paymentManagementService.saveOrUpdatePayment(paymentModel);


        assertEquals("x", "x");

    }


//    List<PaymentModel> allPaymentsForUser = paymentManagementService.getAllPaymentsForUser(1L);


}
