package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GetPaymentsTest extends PMBaseClass {

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

}
