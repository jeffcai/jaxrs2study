JAX-RS 2.0 Study
-----------------------

To get started with:

- Apache CXF
- Jersey
- Apache Wink: latest version is 1.4.0, it's not maintained any more and not recommended

It will cover:

- Annotations

- Application
	- getClasses vs getSingletons: former one is to create instances per requests, the latter one is to use single instance for all requests

- Security: SecurityContext (inject with filed property, or can get it via ContainerRequestContext which user principal is populated with filter or server container configuring with security info)
	- HTTP Basic + Token
	- Role-based access control with annotation
	- OAuth
	- SAML

- Data binding
	- JacksonJsonProvider
	- JAXB + JacksonJaxbJsonProvider

- Filter and Interceptor
	- AuthenticationFilter
	- Name binding
	- Priorities

>Interceptors share a common API for the server and the client side. Whereas filters are primarily intended to manipulate request and response parameters like HTTP headers, URIs and/or HTTP methods, interceptors are intended to manipulate entities, via manipulating entity input/output streams. 

>Filters and interceptors can be name-bound. Name binding is a concept that allows to say to a JAX-RS runtime that a specific filter or interceptor will be executed only for a specific resource method. When a filter or an interceptor is limited only to a specific resource method we say that it is name-bound. Filters and interceptors that do not have such a limitation are called global. Filter or interceptor can be assigned to a resource method using the @NameBinding annotation. The annotation is used as meta annotation for other user implemented annotations that are applied to a providers and resource methods. See the following example:

- Configuration
	- Application: getClasses vs. getSingletons

- Unit Test
	- WebClient with local transport which protocol is lile: local://myapp/resource/...

- Deployment
	- Embedded Jetty: confgiure web app and https
	- Liberty: configure web app, classpath and https
	- WAS: configure web app, classpath and https?

## Samples

### Apache CXF

It shows: annotations, data binding with JaksonJsonProvider, authentication filter with data binding, unit test with local transport and WebClient, and embedded Jetty with https enabled.

### Apache Wink

Not to recommend using it.

### WADL service with Jersey

It's implemented by referring the blog: http://weli.iteye.com/blog/2305096

## References

- [Configure HTTPS for jetty-maven-plugin 9.0.x](http://juplo.de/configure-https-for-jetty-maven-plugin-9-0-x/)
- [Best practice for REST token-based authentication with JAX-RS and Jersey](http://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey)