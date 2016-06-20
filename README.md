JAX-RS 2.0 Study
-----------------------

To get started with:

- Apache CXF
- Jersey
	- WADL service implemented by referring the blog: http://weli.iteye.com/blog/2305096
- Apache Wink, latest version is 1.4.0, it's not maintained any more and not recommended

It will cover:

- Annotations
- Security
	- HTTP Basic
	- Token based
- Data binding
	- JacksonJsonProvider
- Filter and Interceptor
- Configuration
	- Application: getClasses vs. getSingletons
- Unit Test
	- WebClient with local transport which protocol is lile: local://myapp/resource/...
- ... more to be added

## References

- [Configure HTTPS for jetty-maven-plugin 9.0.x](http://juplo.de/configure-https-for-jetty-maven-plugin-9-0-x/)
- [Best practice for REST token-based authentication with JAX-RS and Jersey](http://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey)