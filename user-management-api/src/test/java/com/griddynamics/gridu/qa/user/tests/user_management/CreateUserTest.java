package com.griddynamics.gridu.qa.user.tests.user_management;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.tests.test_data.data_provides.CreateNewUserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.constants.Constants.ADDRESS_URL_REGEX;
import static com.griddynamics.gridu.qa.user.constants.Constants.PAYMENTS_URL_REGEX;
import static com.griddynamics.gridu.qa.user.report.user_management.UserManagementFeatures.*;
import static com.griddynamics.gridu.qa.user.tests.test_data.AddressTestData.createAddressResponseJSON;
import static com.griddynamics.gridu.qa.user.tests.test_data.PaymentTestData.createPaymentResponseJSON;
import static com.griddynamics.gridu.qa.user.tests.test_data.ServicesTestData.stubForService;
import static com.griddynamics.gridu.qa.user.tests.test_data.ServicesTestData.stubForServiceWithError;

@Feature(UM_FEATURE_USER)
@Story(UM_PBI_CREATE_USER)
public class CreateUserTest extends UserManagementBaseTest {

    @Test(description = UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT,
            expectedExceptions = SoapFaultClientException.class,
            expectedExceptionsMessageRegExp = "Can not save user payments!",
            dataProviderClass = CreateNewUserDataProvider.class, dataProvider = "validUserWithPayments")
    @Description(UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT)
    public void errorOnSavingNewPaymentDuringCreationNewUser(CreateUserRequest createUserRequest) {
        stubForService(wireMockServer, ADDRESS_URL_REGEX, createAddressResponseJSON());
        stubForServiceWithError(wireMockServer, PAYMENTS_URL_REGEX);

        userManagementClient.createUser(createUserRequest);
    }

    @Test(description = UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT,
            expectedExceptions = SoapFaultClientException.class,
            expectedExceptionsMessageRegExp = "Can not save user addresses!",
            dataProviderClass = CreateNewUserDataProvider.class, dataProvider = "validUserWithAddresses")
    @Description(UM_TC_CREATE_USER_ERROR_ON_SAVING_ADDRESS)
    public void errorOnSavingNewAddressDuringCreationNewUser(CreateUserRequest createUserRequest) {
        stubForService(wireMockServer, PAYMENTS_URL_REGEX, createPaymentResponseJSON());
        stubForServiceWithError(wireMockServer, ADDRESS_URL_REGEX);

        userManagementClient.createUser(createUserRequest);
    }

    @Test(description = UM_TC_CREATE_USER_WITH_VALID_PAYMENT_AND_ADDRESS,
            dataProviderClass = CreateNewUserDataProvider.class, dataProvider = "validUserWithAddressesAndPayments")
    @Description(UM_TC_CREATE_USER_WITH_VALID_PAYMENT_AND_ADDRESS)
    @SneakyThrows
    public void createNewUser(CreateUserRequest createUserRequest) {
        stubForService(wireMockServer, ADDRESS_URL_REGEX, createAddressResponseJSON());
        stubForService(wireMockServer, PAYMENTS_URL_REGEX, createPaymentResponseJSON());

        CreateUserResponse createUserResponse = userManagementClient.createUser(createUserRequest);
    }

}
