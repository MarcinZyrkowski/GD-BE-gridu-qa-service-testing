package com.griddynamics.gridu.qa.user.tests.e2e;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.steps.UserManagementSteps;
import com.griddynamics.gridu.qa.user.tests.test_data.data_provides.CreateNewUserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.report.UserManagementFeatures.*;

@Feature(FEATURE_USER)
@Story(PBI_CREATE_USER)
public class CreateNewUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_POSITIVE_SCENARIO, dataProviderClass = CreateNewUserDataProvider.class,
            dataProvider = "validUserWithAddressesAndPayments")
    @Description(TC_POSITIVE_SCENARIO)
    public void createNewUser(CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = userManagementClient.createUser(createUserRequest);

        UserManagementSteps.verifyBasicUserData(createUserRequest, createUserResponse);
        UserManagementSteps.verifyAddressesList(createUserRequest, createUserResponse);
        UserManagementSteps.verifyPaymentsList(createUserRequest, createUserResponse);
    }

    @Test(description = "Second dummy test")
    @Description("Second test description")
    public void dummyTest2() {
        System.out.println("passed");
    }

}
