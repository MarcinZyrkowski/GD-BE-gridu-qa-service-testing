package com.griddynamics.gridu.qa.user.tests.user_management;

import com.griddynamics.gridu.qa.user.tests.UserManagementBaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class UserManagementTest extends UserManagementBaseTest {

    @Test(description = "First dummy test")
    @Description("Dummy test description")
    public void dummyTest(){
        System.out.println("passed");
    }

    @Test(description = "Second dummy test")
    @Description("Second test description")
    public void dummyTest2(){
        System.out.println("passed");
    }

}
