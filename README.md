JAX-RS 2.0 Study
-----------------------

To get started with:

- Apache CXF
- Jersey
- Apache Wink: latest version is 1.4.0, it's not maintained any more and not recommended

It will cover:

- Annotations
	- inject	

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

> Interceptors share a common API for the server and the client side. Whereas filters are primarily intended to manipulate request and response parameters like HTTP headers, URIs and/or HTTP methods, interceptors are intended to manipulate entities, via manipulating entity input/output streams. 

> Filters and interceptors can be name-bound. Name binding is a concept that allows to say to a JAX-RS runtime that a specific filter or interceptor will be executed only for a specific resource method. When a filter or an interceptor is limited only to a specific resource method we say that it is name-bound. Filters and interceptors that do not have such a limitation are called global. Filter or interceptor can be assigned to a resource method using the @NameBinding annotation. The annotation is used as meta annotation for other user implemented annotations that are applied to a providers and resource methods. See the following example:

- Configuration
	- Application: getClasses vs. getSingletons

- Expires, Cache-Control, Revalidation with Last-Modified & ETag for scaling 
	- Expires: timestamp will be present on response header, set it with the ResponseBuilder.expires API
	- Cache-Control: private, public, no-cache, no-store, no-transform, max-age, s-max-age, set it with the ResponseBuilder.cacheControl API
	- Revalidation
		- Last-Modified along with response/If-Modified-Since coming from request, set it with the ResponseBuilder.lastModified API
		- ETag along with response/If-None-Match coming from request: generate EntityTag and validate with the Request.evaluatePreconditions API, no matching then proceed to send new etag back to client with the ResponseBuilder.tag API
	- Concurrency 
	
	> If these values do not match the values within the If-Match and If-Unmodified-Since headers sent with the request, evaluatePreconditions() sends back an instance of a ResponseBuilder initialized with the error code 412, “Precondition Failed.” A Response object is built and sent back to the client. If the preconditions are met, the service performs the update and sends back a success code of 204, “No Content.”

- Asynchronous 
	- client invoker: Futures or callbacks?	

> In general, use futures if you need to join a set of requests you’ve invoked asynchronously. By join, I mean you need to know when each of the requests has finished and you need to perform another task after all the asynchronous requests are complete. For example, maybe you are gathering information from a bunch of different web services to build a larger aggregated document (a mashup). Use callbacks when each invocation is its own distinct unit and you do not have to do any coordination or mashing up.

	- server async response

> In 2009, the Servlet 3.0 specification introduced asynchronous HTTP. With the Servlet 3.0 API, you can suspend the current server-side request and have a separate thread, other than the calling thread, handle sending back a response to the client. For a server push app, you could then have a small handful of threads manage sending responses back to polling clients and avoid all the overhead of the “one thread per connection” model. 

> Server-side async response processing is only meant for a specific small subset of applications. Asynchronous doesn’t necessarily mean automatic scalability. For the typical web app, using server asynchronous response processing will only complicate your code and make it harder to maintain. It may even hurt performance.

- Client API and Unit Test
	- WebClient with local transport which protocol is lile: local://myapp/resource/...

- Performance
	- Improvement tips
	- Comparison

- WADL

- Deployment
	- Embedded Jetty: confgiure web app and https
	- Liberty: configure web app, classpath and https
	- WAS: configure web app, classpath and https?


## Samples

### Apache CXF

It shows: annotations, data binding with JaksonJsonProvider, authentication filter with data binding, unit test with local transport and WebClient, and embedded Jetty with https enabled.

Question: How to make tokens expire? with Guava cache or use another scheduled thread to evict expired tokens?

### Apache Wink

Not to recommend using it.

### WADL service with Jersey

It's implemented by referring the blog: http://weli.iteye.com/blog/2305096

## References

- The book "RESTful Java with JAX-RS 2.0"
- [Configure HTTPS for jetty-maven-plugin 9.0.x](http://juplo.de/configure-https-for-jetty-maven-plugin-9-0-x/)
- [Best practice for REST token-based authentication with JAX-RS and Jersey](http://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey)