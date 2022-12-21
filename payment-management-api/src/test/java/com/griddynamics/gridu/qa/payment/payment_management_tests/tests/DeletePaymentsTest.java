package com.griddynamics.gridu.qa.payment.payment_management_tests.tests;

import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.payment_management_tests.PMBaseClass;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.griddynamics.gridu.qa.payment.bootstrap.BootstrapData.BOOTSTRAP_USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeletePaymentsTest extends PMBaseClass {

    @Test
    @SneakyThrows
    @Transactional
    @Rollback(value = false)
    public void deletePayment() {

        // when
        paymentManagementService.deleteAllPaymentsForUser(BOOTSTRAP_USER_ID);

        // then
        List<PaymentModel> allPaymentsForUserAfterDeletingAllPayments =
                paymentManagementService.getAllPaymentsForUser(BOOTSTRAP_USER_ID);

        assertEquals(0, allPaymentsForUserAfterDeletingAllPayments.size());
    }

}
