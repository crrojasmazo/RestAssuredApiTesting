# RestAssured Practice
For this practice, we will implement 4 test cases for an API in a development environment that
complies with the following tools and technologies:
- Java
- Maven
- RestAssured
- POJO
- Cucumber

The test cases should be flexible and maintainable so that they can adapt to future changes in
endpoints or test data, and they should run automatically and independently without errors. The
source code should follow coding best practices, including a README, comments, and
JavaDoc documentation where appropriate.

For this practice, we will use mockApi.io, and the base URL will be as follows:<br/>
https://63b6dfe11907f863aa04ff81.mockapi.io<br/>

## Test Case 1: Get the List of Clients
### Description:
Verify that the list of all clients can be retrieved correctly from the "/api/v1/clients" endpoint.
### Conditions:
- The "/api/v1/clients" endpoint exists and is operational.
- There must be at least 3 clients in the system.
### Verifications:
- Verify that the response is equal to an HTTP status code of 200.
- Verify the structure of the response body schema.


## Test Case 2: Get the List of Resources
### Description:
Verify that the list of all resources can be retrieved correctly from the "/api/v1/resources"
endpoint.
### Conditions:
- The "/api/v1/resources" endpoint exists and is operational.
- There must be at least 5 resources in the system.
### Verifications:
- Verify that the response is equal to an HTTP status code of 200.
- Verify the structure of the response body schema.

## Test Case 3: Create a New Client
### Description:
Verify that a new client can be created successfully at the "/api/v1/clients" endpoint.
### Conditions:
● The "/api/v1/clients" endpoint exists and is operational. <br/>
● Data for creating a new client is available with the following details: <br/>
○ Name: alejo <br/>
○ LastName: lopez<br/>
○ Country: Colombia <br/>
○ City: Cali <br/>
○ Email: alejo.lopez@email.com <br/>
○ Phone: 1234567890 <br/>
### Verifications:
● Verify that the response is equal to an HTTP status code of 201. <br/>
● Verify the structure of the response body schema. <br/>
● Verify the response body data. 

## Test Case 4: Update the Last Resource
### Description:
Verify that an existing resource can be updated successfully at the "/api/v1/resources"
endpoint.
### Conditions:
● The "/api/v1/resources" endpoint exists and is operational. <br/>
● There must be at least 5 resources in the system. <br/>
● Data for updating the last resource is as follows: <br/>
○ Name: Thug Pants <br/>
○ Trademark: Colorado jeans <br/>
○ Stock: 1000 <br/>
○ Price: 99.99 <br/> 
○ Description: Blue short jeans <br/>
○ Tags: thug <br/>
○ Is_active: true  <br/>
### Verifications:
● Verify that the response is equal to an HTTP status code of 200. <br/>
● Verify the structure of the response body schema. <br/>
● Verify the response body data. <br/>
