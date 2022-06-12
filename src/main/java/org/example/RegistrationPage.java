package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class RegistrationPage extends Utils {

    By _radioMaleRegister = By.id("gender-male");
    By _firstNameField =  By.xpath("//input[@name='FirstName']");
    By _lastnameField = By.id("LastName");
    By _birthdate = By.name("DateOfBirthDay");
    By _birthMonth =By.name("DateOfBirthMonth");
    By _birthYear = By.name("DateOfBirthYear");
    By _emailAddress = By.id("Email");
    By _passwordField = By.id("Password");
    By _confPassword = By.id("ConfirmPassword");
    By _clickOnRegister = By.id("register-button");
    LoadProp loadProp = new LoadProp();


    public void verifyRegistrationPageURL(){
        //Verify the URL
        driverWaitUrl("https://demo.nopcommerce.com/register?returnUrl=%2F",5);
    }


    public void enterAllRegistrationDetails(){

        //Select Male Radio button----------------------------------------------------------------Radio button
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(_radioMaleRegister).click();

        //Enter First name
        driverSendKeys((_firstNameField), loadProp.getProperty("firstName"));

        //Enter Last name
        driverSendKeys(_lastnameField, loadProp.getProperty("lastname"));


        //Birth Day-----------------------------------------------------------------------------Birthdate & All
        Select selectDay = new Select(driver.findElement(_birthdate));
        selectDay.selectByValue(loadProp.getProperty("dobDay"));
        //Month
        Select selectMonth = new Select(driver.findElement(_birthMonth));
        selectMonth.selectByValue(loadProp.getProperty("dobMonth"));
        //Year
        Select selectYear = new Select(driver.findElement(_birthYear));
        selectYear.selectByVisibleText(loadProp.getProperty("dobYear"));

        //Enter Email and Printout Time
        driverSendKeys(_emailAddress, loadProp.getProperty("emailPArt1")+ rendomdate() + loadProp.getProperty("emailPart2"));
        System.out.println(rendomdate());

        //Enter password
        driverSendKeys(_passwordField,loadProp.getProperty("password"));

        //Enter Confirmed Password
        driverSendKeys(_confPassword, loadProp.getProperty("confirmPassword"));

        //Click on Register button
        driverWaitsUntilClick(_clickOnRegister,5);
    }
}
