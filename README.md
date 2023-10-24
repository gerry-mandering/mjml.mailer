# MJML Mailer Library

This library provides an efficient way to send MJML emails. It integrates with JavaMailSender to send out emails and utilizes the MJML to HTML conversion provided by the `mjml.converter` library.

## Table of Contents

- [Installation](#installation)
  - [Gradle](#gradle)
  - [Maven](#maven)
- [Configuration](#configuration)
- [Usage](#usage)
- [License](#license)

## Installation

### Gradle

Add the following to your `build.gradle`:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.gerry-mandering:mjml.mailer:1.0.1'
}
```

### Maven

Add the following to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.gerry-mandering</groupId>
    <artifactId>mjml.mailer</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Configuration

To properly use the library, ensure you've set up the following configurations:

### JavaMailSender Configuration

For sending emails, configure the JavaMailSender settings suitable for your mail provider:

```yml
spring:
  mail:
    host: smtp.your-provider.com
    port: 587
    protocol: smtp
    default-encoding: UTF-8
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
    smtp:
      start-tls-enable: true
      auth: true
```

### MJML API Authentication

To properly use the library, ensure you've set up the MJML API authentication:

- `mjml.app-id`: Your MJML API App ID.
- `mjml.app-secret`: Your MJML API App Secret.

You can configure these in your `application.properties` or `application.yml` file:

```yml
mjml:
  app-id: YOUR_APP_ID
  app-secret: YOUR_APP_SECRET
```

Replace placeholders like `smtp.your-provider.com`, `YOUR_DOMAIN`, `YOUR_PASSWORD`, `YOUR_APP_ID`, and `YOUR_APP_SECRET` your actual values. Consult your email service provider's documentation for the correct SMTP settings.

## Usage

By default, the library expects your MJML templates to be located in the `resources/templates` directory of your project.

### Injecting the Service

To use the `mjml.mailer` library in your Spring application, first inject the `MjmlMailer` by using the `@Autowired` annotation:

```java
@Autowired
private MjmlMailer mjmlMailer;
```

### Sending MJML Emails

1. **With Parameters**:

Compile the MJML templates with dynamic content using the Mustache templating system. This allows you to replace placeholders (keys) in the template with specific values. 

```java
Map<String, Object> params = new HashMap<>();
params.put("key1", "value1");
params.put("key2", "value2");
// Add more parameters as needed...

// This will replace {{key1}} and {{key2}} in the "your-template-name.mjml" with "value1" and "value2" respectively.
MjmlEmail mjmlEmail = MjmlEmail.builder()
                              .to("recipient@example.com")
                              .subject("Sample Subject")
                              .params(params)
                              .templateName("your-template-name.mjml")
                              .build();

mjmlMailer.send(mjmlEmail);
```

2. **Without Parameters**:

For MJML templates that don't require dynamic content substitution:

```java
MjmlEmail mjmlEmail = MjmlEmail.builder()
                              .to("recipient@example.com")
                              .subject("Sample Subject")
                              .templateName("your-template-name.mjml")
                              .build();

mjmlMailer.send(mjmlEmail);
```

Replace placeholders like `recipient@example.com`, `Sample Subject`, `key`, `value`, and `your-template-name.mjml` with your actual values. Adjust the instructions as needed based on your specific use case.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
