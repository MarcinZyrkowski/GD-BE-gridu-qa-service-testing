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

    @Test(description = TC_CREATE_USER, dataProviderClass = CreateNewUserDataProvider.class,
            dataProvider = "validUserWithAddressesAndPayments")
    @Description(TC_CREATE_USER)
    public void createNewUser(CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        verifyBasicUserData(createUserRequest, createUserResponse);
        verifyAddressesList(createUserResponse.getUserDetails().getAddresses().getAddress(),
                createUserRequest.getAddresses().getAddress());
        verifyPaymentsList(createUserResponse.getUserDetails().getPayments().getPayment(),
                createUserRequest.getPayments().getPayment());
    }

}
