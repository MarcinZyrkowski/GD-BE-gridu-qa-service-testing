package com.griddynamics.gridu.qa.user.tf.test_data;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.UserDetails;
import com.griddynamics.gridu.qa.user.tf.utils.EnumUtils;
import com.griddynamics.gridu.qa.user.tf.utils.NumberRange;
import io.qameta.allure.Step;
import lombok.SneakyThrows;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.Month;

public
class UserTestData extends TestData {

    protected final static String NAME = "John";
    protected final static String NAME_2 = "Richard";
    protected final static String SURNAME = "Doe";
    protected final static String SURNAME_2 = "Roe";
    protected final static String EMAIL = "john.doe@email.com";
    protected final static String EMAIL_2 = "richard.roe@email.com";
    // timezone is defined as number of minutes added from -14*60 to 14*60 (-14h to +14h)
    protected static final int TIMEZONE_UTC_PLUS_ONE = 60;
    protected static final NumberRange monthsRange = new NumberRange(1, 12);
    protected final static LocalDate BIRTHDAY =
            LocalDate.of(2000, EnumUtils.getRandomElement(Month.class), monthsRange.randomInt());
    protected final static LocalDate BIRTHDAY_2 =
            LocalDate.of(2000, EnumUtils.getRandomElement(Month.class), monthsRange.randomInt());
    protected static final String NOT_FOUND = "NOT_FOUND";

    @SneakyThrows
    @Step("Prepare basic user request data")
    public static CreateUserRequest prepareBasicCreateUserRequestData() {
        CreateUserRequest createUserRequest = OBJECT_FACTORY.createCreateUserRequest();
        createUserRequest.setName(NAME);
        createUserRequest.setLastName(SURNAME);
        createUserRequest.setEmail(EMAIL);
        XMLGregorianCalendar birthday = DatatypeFactory.newInstance().newXMLGregorianCalendar(BIRTHDAY.toString());
        birthday.setTimezone(TIMEZONE_UTC_PLUS_ONE);
        createUserRequest.setBirthday(birthday);
        return createUserRequest;
    }

    @SneakyThrows
    @Step("Prepare other user details")
    public static UserDetails prepareOtherUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(NAME_2);
        userDetails.setLastName(SURNAME_2);
        userDetails.setEmail(EMAIL_2);
        XMLGregorianCalendar birthday = DatatypeFactory.newInstance().newXMLGregorianCalendar(BIRTHDAY_2.toString());
        birthday.setTimezone(TIMEZONE_UTC_PLUS_ONE);
        userDetails.setBirthday(birthday);
        return userDetails;
    }

    @SneakyThrows
    @Step("Prepare not found user details")
    public static UserDetails prepareNotFoundUserDetails(long userId) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userId);
        userDetails.setName(NOT_FOUND);
        userDetails.setLastName(NOT_FOUND);
        userDetails.setEmail(NOT_FOUND);
        return userDetails;
    }

}
