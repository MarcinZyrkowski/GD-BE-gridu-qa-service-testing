package com.griddynamics.gridu.qa.payment.payment_management_tests;

import com.griddynamics.gridu.qa.gateway.api.CardApi;
import com.griddynamics.gridu.qa.payment.db.dao.PaymentRepository;
import com.griddynamics.gridu.qa.payment.service.DtoConverter;
import com.griddynamics.gridu.qa.payment.service.PaymentManagementService;
import com.griddynamics.gridu.qa.payment.utils.NumberRange;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.griddynamics.gridu.qa.payment.bootstrap.BootstrapData.BOOTSTRAP_USER_ID;

public class PMBaseClass {

    protected static final String PAYMENT_GATEWAY_ACCEPT_TOKEN = "accepted_card_token";
    protected static final String PAYMENT_GATEWAY_FAILED_TOKEN = "FAILED";
    protected static NumberRange userIdRange = new NumberRange(1, 99);

    protected final DtoConverter dtoConverter = new DtoConverter();
    public PaymentManagementService paymentManagementService;

    @Autowired
    protected PaymentRepository paymentRepository;
    @Mock
    protected CardApi cardApi;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentManagementService = new PaymentManagementService(paymentRepository, cardApi, dtoConverter);
    }

//    @AfterAll
//    @Transactional
//    @Rollback(value = false)
//    public static void tearDown(){
//        PMBaseClass pmBaseClass = new PMBaseClass();
//
//        pmBaseClass.paymentRepository.deletePaymentModelByUserId(BOOTSTRAP_USER_ID);
//    }

}
