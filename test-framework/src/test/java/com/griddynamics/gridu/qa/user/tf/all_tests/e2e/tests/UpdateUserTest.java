package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.steps.UserManagementSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareOtherUserDetails;
import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;

@Feature(FEATURE_USER)
@Story(PBI_UPDATE_USER)
public class UpdateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_UPDATE_USER)
    @Description(TC_UPDATE_USER)
    @SneakyThrows
    public void updateUsersData() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        UserManagementSteps.verifyBasicUserData(createUserRequest, createUserResponse);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);
        UserManagementSteps.verifyUpdatedUserDetails(updateUserRequest, updateUserResponse);
    }

}
