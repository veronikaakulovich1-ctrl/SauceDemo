# README

## Test Suite Overview

This repository contains a series of automated tests for a shopping application.

## Test Checklist

### 1. BurgerMenuTest
- **Check Logout**
    - Verify that the login button is displayed after logging out.

- **Check Transition to All Items Menu**
    - Validate that the title of the products page is "Products" after navigating to the All Items menu.

- **Check Transition to About Page**
    - Confirm that the URL is "https://saucelabs.com/" after navigating to the About page.

### 2. CheckoutCompleteTest
- **END_TO_END Order Test**
    - Ensure that the title "Checkout: Complete!" is displayed after completing an order.

- **Check Transition by Back Home Button**
    - Validate that the title of the products page is "Products" after clicking the back home button post-checkout.

### 3. CheckOutOverviewTest
- **Check Product Price Sum with Total Price**
    - Verify that the calculated total price matches the summary subtotal price during checkout.

### 4. CheckoutYourInformationTest
- **Check Checkout Your Information with Positive Credentials**
    - Confirm that the title "Checkout: Overview" is displayed after entering valid information.

- **Check Checkout Your Information with Empty First Name**
    - Validate that an error message "Error: First Name is required" is displayed when the first name is empty.

- **Check Checkout Your Information with Empty Last Name**
    - Validate that an error message "Error: Last Name is required" is displayed when the last name is empty.

- **Check Checkout Your Information with Empty Zip Code**
    - Validate that an error message "Error: Postal Code is required" is displayed when the zip code is empty.

- **Check Transition from Cancel Button**
    - Ensure that the title "Your Cart" is displayed after cancelling the checkout process.

### 5. LoginTest
- **Check Login with Positive Credentials**
    - Confirm that the title of the products page is "Products" after a successful login.

- **Check Login with Empty Username**
    - Validate that the error message "Epic sadface: Username is required" is displayed when the username is empty.

- **Check Login with Empty Password**
    - Validate that the error message "Epic sadface: Password is required" is displayed when the password is empty.

- **Check Login with Negative Credentials**
    - Validate that the error message "Epic sadface: Username and password do not match any user in this service" is displayed for invalid credentials.

### 6. YourCartTest
- **Check Add to Cart**
    - Ensure that the product is correctly added to the cart and the title "Your Cart" is displayed.

- **Check Remove from Cart**
    - Validate that the product is no longer displayed in the cart after removal.

- **Continue Shopping Button Click**
    - Confirm that the title of the products page is "Products" after clicking the continue shopping button.
