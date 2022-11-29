package com.griddynamics.gridu.qa.user.tests.e2e;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.tests.test_data.data_provides.CreateNewUserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.steps.UserManagementSteps.*;

@Feature(FEATURE_USER)
@Story(PBI_CREATE_USER)
public class CreateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_CREATE_USER, dataProviderClass = CreateNewUserDataProvider.class,
            dataProvider = "validUserWithAddressesAndPayments")
    @Description(TC_CREATE_USER)
    public void createNewUser(CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = userManagementClient.createUser(createUserRequest);

        verifyBasicUserData(createUserRequest, createUserResponse);
        verifyAddressesList(createUserResponse.getUserDetails().getAddresses().getAddress(),
                createUserRequest.getAddresses().getAddress());
        verifyPaymentsList(createUserResponse.getUserDetails().getPayments().getPayment(),
                createUserRequest.getPayments().getPayment());
    }

}
