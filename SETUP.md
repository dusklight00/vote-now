# VoteNow Setup Guide

This document provides step-by-step instructions for setting up the VoteNow Online Voting System.

## Prerequisites

1. Java Development Kit (JDK) 17 or higher
2. MySQL 8.0 or higher
3. Maven 3.6 or higher
4. Git

## Installation Steps

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/votingsystem.git
cd votingsystem
```

### 2. Configure the Database

1. Log in to MySQL:

   ```bash
   mysql -u root -p
   ```

2. Run the database setup script:

   ```bash
   mysql -u root -p < database-setup.sql
   ```

3. Configure database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/votingsystem
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Configure Email Service

Update the email configuration in `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> **Note**: For Gmail, you need to use an App Password. Follow [these instructions](https://support.google.com/accounts/answer/185833) to generate one.

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will be available at http://localhost:8080

### 6. Initial Login

Access the admin panel at http://localhost:8080/admin-login with these default credentials:

- Username: admin
- Password: password

**Important**: Change the default password immediately after first login!

## System Configuration

### Setting Up Elections

1. Log in as an administrator
2. Navigate to "Create Election"
3. Fill in the election details:
   - Title
   - Description
   - Start date and time
   - End date and time
4. Add candidates with their details:
   - Name
   - Party
   - Position
   - Profile picture (optional)
   - Manifesto (optional)

### Managing Voters

1. Navigate to "Manage Voters"
2. Add voters manually or import from CSV
3. Set verification method (Email, SMS, or both)
4. Send invitations to register

## Troubleshooting

### Database Connection Issues

- Verify MySQL service is running
- Check connection credentials in `application.properties`
- Ensure the database and tables exist

### Email Sending Issues

- Verify SMTP settings
- Check if your email provider requires specific configuration
- Ensure you're using the correct app password for Gmail

### Application Startup Issues

- Check port availability (default 8080)
- Verify Java version (must be 17+)
- Check log files for specific errors

## Additional Resources

- [Developer Documentation](docs/developer.md)
- [User Manual](docs/user-manual.md)
- [API Documentation](docs/api.md)
- [Security Guidelines](docs/security.md)
