## Example projects using Java Servlet

1. basic - basic servlet
2. jsp   - JSP (Java Servlet Page) example
3. mvc   - combine Servlet and JSP using MVC (Model-View-Controller)
4. login - Example login system using MVC

## To run
```bash
cd basic
mvn package
./target/bin/webapp
```

## Basic Webapp
It contains simple webapp which two servlets, i.e. `HomeServlet` and `AdminServet`. Once run, you will be able to access `HomeServet` through http://localhost:8082/ and  `AdminServet` through http://localhost:8082/admin .

The `AdminServlet` contains two overriden methods for GET and POST requests. To access the GET method, you can simply enter the URL on the web browser. To access the POST method, you will need to make a POST request using curl command or Postman plugin for your web browser.

## JSP Webapp
The example illustrates the use of JSP (Java Servlet Page) technology. JSP is a a technology that helps software developers create dynamically generated web pages based on HTML, XML, or other document types. In the examples, it contains two jsp files, which correspond to  http://localhost:8082/home.jsp and  http://localhost:8082/admin.jsp

## MVC Webapp
We illustrate use of MVC (Model–view–controller) pattern -- a software architectural pattern commonly used for developing user interfaces. The codes combine the standard servlet codes and JSP to achieve MVC.

## Login Webapp
An example of software architectural design in a simple login page.
