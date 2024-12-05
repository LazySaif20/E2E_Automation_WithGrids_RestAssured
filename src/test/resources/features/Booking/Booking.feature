Feature: User navigates to booking section

  @testBooking
  Background: Functionality of booking
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login

   @Booking
    Scenario: To validate the Booking section in the Sales Operation
      Given User clicks on the Sales icon
      Then User selects the Sales Operation tab
      When User selects Customer Booking Mgt List under sales Operation
      Then User need to select the enquiry option in the dropdown
      And User enters the mobile number in the text box
      And User selects the mobile number option from the dropdown
      And User passed the start date and end date in the page
      Then User clicks on the search button
     When The enquiry will be populated then user as to select it
     Then User fills the fields in the Customer Booking MGT

