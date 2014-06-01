mule-camunda-24
===============

Showcase using Mule and camunda BPM for a simple order process

Before you can execute the project, you have to provide credentials for pop3 and smtp. Add a file mail.properties to the classpath (e.g. to src/main/resources) with the following properties:

```
mail.pop.host=pop3.web.de
mail.pop.port=995
mail.pop.user=your-pop3-user
mail.pop.password=your-pop3-password

mail.smtp.host=mail.gmx.net
mail.smtp.port=465
mail.smtp.user=your-smtp-user
mail.smtp.password=your-smtp-password
```

The project can be build with maven: mvn package

The war has to be deployed into a Tomcat instance with the camunda workflow engine. You can download a prepared Tomcat from http://www.camunda.com
