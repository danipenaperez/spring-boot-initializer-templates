En esta version encontramos un proyecto de Spring Boot con :

Build :
-------
* Gradle
* SpringBoot 2.0.2

Run:
----
* to build > ./gradlew build
* to Run ./gradle bootRun
* to Run on Eclipse, rigthClick on TestersApplication.java > Run As Java Application (or Debug As) 

Features
--------
* Web based on @RestController
* Basic security (son spring-boot-security-starter)
	* Support for JSR250 , @Security, @RollesAllowed, @PreAuthorize and @PostAuthorize
	* Predefined Users InMemoryStore 3 diferent user with 3 diferent roles
* Manage perfectly Semantic Error between 401, and 403 (Authentication and authorization)
* Test. Integration test to Rest Endpoints. that ups the server on phase test (mvn test). And if serverport is modified is added to @AutoconfigureMockMvc
	* @SpringBootTest
	* @AutoConfigureMockMvc
	* @RunWith(SpringJUnit4ClassRunner.class)


TEST
-------
** Automation Test **
Execute on Eclipse , Controller1Test.java Run as JUnit Test.

** Web Based Test **
* to Run ./gradle bootRun
* to Run on Eclipse, rigthClick on TestersApplication.java > Run As Java Application (or Debug As)

then the default root endpoint does not exist so http://localhost:8080 will return 404
```
TEST:
dpena@ ~ () $ curl -X GET -i http://localhost:8080/controller1/nonSecured_endpoint
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 31
Date: Tue, 16 Oct 2018 07:26:18 GMT

Hello from nonsecured endpoint!dpena
```

```
dpena@ ~ () $ curl -X GET -i http://localhost:8080/controller1/nonSecuredJSR250_endpoint
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 37
Date: Tue, 16 Oct 2018 07:26:27 GMT

Hello from nonSecuredJSR250 endpoint!
```

```
dpena@ ~ () $ curl -X GET -i http://localhost:8080/controller1/nonSecuredPrePost_endpoint
HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 38
Date: Tue, 16 Oct 2018 07:26:37 GMT

Hello from nonSecuredPrePost endpoint!dpena@ ~ () $ 

Nota: Si sale error  404 Fallback, suele ser porque se ha definido como @Controller en vez de @RestController  (tener tambien en cuenta el slash en @GetMapping
```

