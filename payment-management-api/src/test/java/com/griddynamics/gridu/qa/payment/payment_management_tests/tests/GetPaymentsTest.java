package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.griddynamics.gridu.qa.payment.bootstrap.BootstrapData.BOOTSTRAP_USER_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GetPaymentsTest extends PMBaseClass {

    @Test
    public void getAllPaymentForUser() {
        // when
        List<PaymentModel> allPaymentsForUser = paymentManagementService.getAllPaymentsForUser(BOOTSTRAP_USER_ID);

        // then
        assertNotNull(allPaymentsForUser,
                "Returned payments list for user with ID: " + BOOTSTRAP_USER_ID + "%s should not be null");
    }

}
