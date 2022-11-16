package com.griddynamics.gridu.qa.user.tests.user_management.test_data;

import com.griddynamics.gridu.qa.user.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.time.Month;

import static com.griddynamics.gridu.qa.user.utils.EnumUtils.getRandomElement;

public class CreateNewUserTestData {

    protected final static ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    protected final static LocalDate BIRTHDAY = LocalDate.of(2000, getRandomElement(Month.class), 1);
    protected final static String NAME = "John";
    protected final static String SURNAME = "Doe";
    protected final static String EMAIL = "john.doe@email.com";
    protected final static State STATE = getRandomElement(State.class);
    protected final static String CITY = "city";
    protected final static String ZIP = "zip";
    protected final static String LINE_1 = "line1";
    protected final static String LINE_2 = "line2";
    protected final static String CARD_NUMBER = "4242 4242 4242 4242";
    protected final static int EXPIRY_YEAR = LocalDate.now().plusYears(1).getYear();
    protected final static int EXPIRY_MONTH = LocalDate.now().plusYears(1).getMonthValue();
    protected final static String CARD_HOLDER = NAME + " " + SURNAME;
    protected final static String CVV = "123";

    public static CreateUserRequest prepareValidCreateUserRequest() throws DatatypeConfigurationException {
        CreateUserRequest createUserRequest = OBJECT_FACTORY.createCreateUserRequest();
        createUserRequest.setName(NAME);
        createUserRequest.setLastName(SURNAME);
        createUserRequest.setLastName(EMAIL);
        createUserRequest.setBirthday(DatatypeFactory.newInstance().newXMLGregorianCalendar(BIRTHDAY.toString()));

        CreateUserRequest.Addresses addresses = OBJECT_FACTORY.createCreateUserRequestAddresses();
        addresses.getAddress().add(prepareValidNewAddress());

        CreateUserRequest.Payments payments = OBJECT_FACTORY.createCreateUserRequestPayments();
        payments.getPayment().add(prepareValidNewPayment());

        return createUserRequest;
    }

    public static NewAddress prepareValidNewAddress() {
        NewAddress address = OBJECT_FACTORY.createNewAddress();
        address.setState(STATE);
        address.setCity(CITY);
        address.setZip(ZIP);
        address.setLine1(LINE_1);
        address.setLine2(LINE_2);
        return address;
    }

    public static NewPayment prepareValidNewPayment() {
        NewPayment payment = OBJECT_FACTORY.createNewPayment();
        payment.setCardNumber(CARD_NUMBER);
        payment.setExpiryYear(EXPIRY_YEAR);
        payment.setExpiryMonth(EXPIRY_MONTH);
        payment.setCardholder(CARD_HOLDER);
        payment.setCvv(CVV);
        return payment;
    }

}
