/**********************
release-0.0.1
*********************

En la version 1:
Creamos un proyecto con dependencia de Web y Security 
Como tambien queremos probar las anotaciones de jsr-250 , añadimos la dependencia asi:
implementation('javax.annotation:jsr250-api:1.0')

Ahora creamos nuestra primera configuracion de Seguridad:
/config/SecurityConfig.java


Creamos unos usuarios por defecto en memoria, para poder probar la seguridad
administrator - 1234 que tiene rol de ADMINISTARTOR
user - 1234  que tiene rol de USER
backend - 1234  que tiene rol de BACKEND
Es importante en este punto añadir que las passwords se guarden codificadas, por defecto Spring security 5 no permite guardar las pass en abierto
asi que es necesario meter esto 
PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //por defecto nos da un BCrypt 
    auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("1234")).roles("USER")

Si no hubieramos guardado la pass encriptada habriamos obtenido un error :
java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"

 
 De todos modos, al haber añadido la dependencia de web-security, por defecto todos los endpoints son seguros (aunque para el ejemplo especificamos queno lo sean)
 y genra un usuario password por defecto en arranque: dice la traza Using generated security password: eb0b1d7f-0d44-4ef0-a6ee-05f863aade68
 Y estas seran las credenciales Basic
 user/eb0b1d7f-0d44-4ef0-a6ee-05f863aade68

Con eso ya esta preparado para securizar cualquier parte de la aplicacion
VAmos a empezar con un controller:

Usar la anotacion @PreAuthorize y @PostAuthorize es mejor porque soporta evaluaciones con expresiones Spring Expression Language (SpEL) and provide expression-based access control

Construimos con ./gradlew build
Arrancamos con ./gradle bootRun 

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

-----------------------------------------------------------
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

------------------------------------------------------------
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



Vamos a generar unos test automaticos que prueben nuestra app:
en la carpeta test : Controller1Test.java
Marcarlos con 
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)

Para usar los metodos estaticos get(), httpBasic() ...hay que poner los imports

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
etc...

podemos dar boton derecho encima en eclipse y Run as JUnitTest


/************************************
Vamos a securizar otros endpoints de los 3 modos que ya conocemos
En controller1 añadimos los metodos con las anotaciones y cambiamos el contenido de la respuesta

Añadimos los Test para probar los securizados.
Probamos que NO se puede entrar y da error 401 si intentamos entrar sin credenciales
Probamos que NO se puede entrar y da error 401 si intentamos entrar con credenciales correctas pero no es el rol permitido
Probamos que SI se puede entrar y da error 401 si intentamos entrar con credenciales correctas y con el ROL

ejecutamos ./gradle build y correra los test.
Tambien podemos seguir haciendo boton derecho y run as JUnit test en Eclipse.



