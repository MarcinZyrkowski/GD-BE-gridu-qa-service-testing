package com.griddynamics.gridu.qa.user.tests.user_management;

import com.griddynamics.gridu.qa.user.tests.UserManagementBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.report.UserManagementFeatures.*;

@Feature(FEATURE_USER)
@Story(PBI_CREATE_USER)
public class CreateNewUserTest extends UserManagementBaseTest {

    @Test(description = TC_POSITIVE_SCENARIO)
    @Description(TC_POSITIVE_SCENARIO)
    public void createNewUser(){
        System.out.println("passed");
    }

    @Test(description = "Second dummy test")
    @Description("Second test description")
    public void dummyTest2(){
        System.out.println("passed");
    }

}