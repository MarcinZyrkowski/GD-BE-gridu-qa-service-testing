package com.griddynamics.gridu.qa.user.tests.test_data.data_provides;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import org.testng.annotations.DataProvider;

import javax.xml.datatype.DatatypeConfigurationException;

import static com.griddynamics.gridu.qa.user.tests.test_data.CreateNewUserTestData.*;
import static com.griddynamics.gridu.qa.user.tests.test_data.CreateNewUserTestData.prepareNewAddress;

public class CreateNewUserDataProvider {

    @DataProvider
    public static Object[][] validUserWithAddressesAndPayments() throws DatatypeConfigurationException {
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
