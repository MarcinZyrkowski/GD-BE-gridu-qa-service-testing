package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.steps.GeneralSteps;
import com.griddynamics.gridu.qa.user.tf.steps.UserSteps;
import com.griddynamics.gridu.qa.user.tf.test_data.data_provides.UserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareOtherUserDetails;

@Feature(FEATURE_USER)
@Story(PBI_UPDATE_USER)
public class UpdateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_UPDATE_USER)
    @Description(TC_UPDATE_USER)
    @SneakyThrows
    public void updateBasicUsersData() {
        // given
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        GeneralSteps.verifyAllUsersData(createUserRequest, createUserResponse);

        // when
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        // then
        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);
        UserSteps.verifyUpdatedUserDetails(updateUserRequest, updateUserResponse);
    }

    // change description TODO
    @Test(description = "TC_UPDATE_USER", dataProviderClass = UserDataProvider.class,
            dataProvider = "validUserWithPayments")
    @Description("TC_UPDATE_USER")
    @SneakyThrows
    public void updateUsersPayments(CreateUserRequest createUserRequest) {
        // given
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        GeneralSteps.verifyAllUsersData(createUserRequest, createUserResponse);

        // when
        // updated basic user's details
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();

        // updated payment
        UserDetails.Payments payments = createUserResponse.getUserDetails().getPayments();
        payments.getPayment().get(0).setExpiryYear(LocalDate.now().plusYears(5).getYear());
        payments.getPayment().get(0).setCardholder("Richard Roe");

        userDetailsToUpdate.setPayments(createUserResponse.getUserDetails().getPayments());
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        // then
        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);


        UserSteps.verifyUpdatedUserDetails(updateUserRequest, updateUserResponse);


        // add updated payment verification
    }

    // update addresses


}
