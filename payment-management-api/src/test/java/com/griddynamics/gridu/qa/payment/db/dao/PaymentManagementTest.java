package com.griddynamics.gridu.qa.payment.db.dao;

import com.griddynamics.gridu.qa.gateway.api.CardApi;
import com.griddynamics.gridu.qa.payment.api.model.Payment;
import com.griddynamics.gridu.qa.payment.db.model.PaymentModel;
import com.griddynamics.gridu.qa.payment.service.DtoConverter;
import com.griddynamics.gridu.qa.payment.service.PaymentManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentManagementTest {

    public PaymentManagementService paymentManagementService;

    @Autowired
    protected PaymentRepository paymentRepository;
    @Mock
    protected CardApi cardApi;
    protected final DtoConverter dtoConverter = new DtoConverter();

    @Test
    public void savePayment() throws Exception {

        MockitoAnnotations.openMocks(this);
        paymentManagementService = new PaymentManagementService(paymentRepository, cardApi, dtoConverter);

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

        List<PaymentModel> allPaymentsForUser = paymentManagementService.getAllPaymentsForUser(1L);

        System.out.println("y");

    }


}
