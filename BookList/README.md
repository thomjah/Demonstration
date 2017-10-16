BookList is a simple project of a web app that reads a file of Books bundled in the app. It returns the file as XML, which a HTML client reads and prints to a page.

It is built with Maven.

It is a WAR that can be deployed to Tomcat (or other Servlet container) without any additional configuration. If you add the built file as "BookList-1.0.SNAPSHOT.war" to Tomcat's "webapp" folder, it might be deployed as "localhost:8080/BookList-1.0.SNAPSHOT/", despite the context.xml telling it should be on "/BookList".

There are two HTML files - one using server-side filtering, the other just using Angular filtering on the client.
In this simple example, the Angular filter is a better solution, but the project has server filtering for demonstration purposes.
