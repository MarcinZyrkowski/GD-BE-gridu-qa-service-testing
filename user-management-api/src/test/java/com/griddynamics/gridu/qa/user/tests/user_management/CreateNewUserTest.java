package com.griddynamics.gridu.qa.user.tests.user_management;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.State;
import com.griddynamics.gridu.qa.user.tests.UserManagementBaseTest;
import com.griddynamics.gridu.qa.user.utils.EnumUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeConfigurationException;

import static com.griddynamics.gridu.qa.user.report.UserManagementFeatures.*;
import static com.griddynamics.gridu.qa.user.tests.user_management.test_data.CreateNewUserTestData.prepareValidCreateUserRequest;

@Feature(FEATURE_USER)
@Story(PBI_CREATE_USER)
public class CreateNewUserTest extends UserManagementBaseTest {

    @Test(description = TC_POSITIVE_SCENARIO)
    @Description(TC_POSITIVE_SCENARIO)
    public void createNewUser() throws DatatypeConfigurationException {

        CreateUserRequest createUserRequest = prepareValidCreateUserRequest();
        userManagementClient.createUser(createUserRequest);
    }

    @Test(description = "Second dummy test")
    @Description("Second test description")
    public void dummyTest2(){
        System.out.println("passed");
        System.out.println(EnumUtils.getRandomElement(State.class));
    }

}
