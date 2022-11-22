package com.griddynamics.gridu.qa.user.tests.test_data.data_provides;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import static com.griddynamics.gridu.qa.user.tests.test_data.CreateNewUserTestData.*;

public class CreateNewUserDataProvider {

    @DataProvider
    @SneakyThrows
    public static Object[][] validUserWithAddressesAndPayments() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        CreateUserRequest.Addresses addresses = prepareAddresses();
        addNewAddress(addresses, prepareNewAddress());

        CreateUserRequest.Payments payments = preparePayments();
        addNewPayment(payments, prepareNewPayment());

        createUserRequest.setAddresses(addresses);
        createUserRequest.setPayments(payments);

        return new Object[][]{
                {
                        createUserRequest
                }
        };
    }

}
