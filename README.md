# RestServiceAutomation
This is a small piece of application written by me to automate Rest API testing. API responses check against predefined Excel sheet data and produce a detail report regarding each API. Report format and style is similar to JUnit test report. Report shows which calls has been success and which are not. If an operation failed or not returns the expected values, report highlights exact issue or expected value and actual value return.

Since test data is managed through an Excel sheet, even none technical person can create and maintain test data.


Technologies used.
----------------
* Java 1.6
* Spring 3.0.6 with RestTemplate
* JUnit 4
* Apache POI
* FreeMarker
