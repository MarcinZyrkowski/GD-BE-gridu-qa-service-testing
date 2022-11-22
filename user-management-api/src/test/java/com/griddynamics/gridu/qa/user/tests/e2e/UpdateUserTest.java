package com.griddynamics.gridu.qa.user.tests.e2e;

import com.griddynamics.gridu.qa.user.*;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.report.UserManagementFeatures.*;
import static com.griddynamics.gridu.qa.user.steps.UserManagementSteps.*;
import static com.griddynamics.gridu.qa.user.tests.test_data.CreateNewUserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.tests.test_data.CreateNewUserTestData.prepareOtherUserDetails;

@Feature(FEATURE_USER)
@Story(PBI_UPDATE_USER)
public class UpdateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_UPDATE_USER)
    @Description(TC_UPDATE_USER)
    @SneakyThrows
    public void updateUserData() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = userManagementClient.createUser(createUserRequest);
        verifyBasicUserData(createUserRequest, createUserResponse);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = userManagementClient.updateUser(updateUserRequest);
        verifyUpdatedUserDetails(updateUserRequest, updateUserResponse);
    }

}
