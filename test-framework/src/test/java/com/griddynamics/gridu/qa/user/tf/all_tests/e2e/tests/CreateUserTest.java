package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;


import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.test_data.data_provides.CreateNewUserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.tf.steps.UserManagementSteps.*;

@Feature(FEATURE_USER)
@Story(PBI_CREATE_USER)
public class CreateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT, dataProviderClass = CreateNewUserDataProvider.class,
            dataProvider = "validUserWithAddressAndPayment")
    @Description(TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT)
    public void createNewUserWithAddressAndPayment(CreateUserRequest createUserRequest) {
        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyBasicUserData(createUserRequest, createUserResponse);
        verifyAddressLists(createUserResponse, createUserRequest);
        verifyPaymentLists(createUserResponse, createUserRequest);
    }

    @Test(description = TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT, dataProviderClass = CreateNewUserDataProvider.class,
            dataProvider = "validUserWithPayments")
    @Description(TC_CREATE_USER_WITH_PAYMENT)
    public void createNewUserWithPayment(CreateUserRequest createUserRequest) {
        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyBasicUserData(createUserRequest, createUserResponse);
        verifyAddressLists(createUserResponse, createUserRequest);
        verifyPaymentLists(createUserResponse, createUserRequest);
    }

    @Test(description = TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT, dataProviderClass = CreateNewUserDataProvider.class,
            dataProvider = "validUserWithAddresses")
    @Description(TC_CREATE_USER_WITH_ADDRESS)
    public void createNewUserWithAddress(CreateUserRequest createUserRequest) {
        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyBasicUserData(createUserRequest, createUserResponse);
        verifyAddressLists(createUserResponse, createUserRequest);
        verifyPaymentLists(createUserResponse, createUserRequest);
    }

    // TODO
    // basic
    // mandatory data only

}
