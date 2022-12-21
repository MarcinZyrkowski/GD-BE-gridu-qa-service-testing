package com.griddynamics.gridu.qa.payment.bootstrap;

import com.griddynamics.gridu.qa.payment.db.dao.PaymentRepository;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.griddynamics.gridu.qa.payment.test_data.PaymentManagementData.bootstrapPaymentModel;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    public static final long BOOTSTRAP_USER_ID = 0L;

    @Autowired
    protected PaymentRepository paymentRepository;

    @Override
    public void run(String... args) {
        PaymentModel payment = bootstrapPaymentModel(BOOTSTRAP_USER_ID);

        paymentRepository.save(payment);
    }
}
