package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;


import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.test_data.data_provides.UserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.tf.steps.GeneralSteps.verifyAllUsersData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;

@Feature(FEATURE_USER)
@Story(PBI_CREATE_USER)
public class CreateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT, dataProviderClass = UserDataProvider.class,
            dataProvider = "validUserWithAddressAndPayment")
    @Description(TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT)
    public void createNewUserWithAddressAndPayment(CreateUserRequest createUserRequest) {
        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyAllUsersData(createUserRequest, createUserResponse);
    }

    @Test(description = TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT, dataProviderClass = UserDataProvider.class,
            dataProvider = "validUserWithPayments")
    @Description(TC_CREATE_USER_WITH_PAYMENT)
    public void createNewUserWithPayment(CreateUserRequest createUserRequest) {
        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyAllUsersData(createUserRequest, createUserResponse);
    }

    @Test(description = TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT, dataProviderClass = UserDataProvider.class,
            dataProvider = "validUserWithAddresses")
    @Description(TC_CREATE_USER_WITH_ADDRESS)
    public void createNewUserWithAddress(CreateUserRequest createUserRequest) {
        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyAllUsersData(createUserRequest, createUserResponse);
    }

    @Test(description = TC_CREATE_USER_WITH_BASIC_DATA)
    @Description(TC_CREATE_USER_WITH_BASIC_DATA)
    public void createNewUserWithBasicData() {
        // given
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        // when
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        // then
        verifyAllUsersData(createUserRequest, createUserResponse);
    }

}
