# VoteNow - Online Voting System

This is an implementation of an Online Voting System using the following technology stack:

- **Spring Boot**: Backend framework for building RESTful APIs.
- **HTML/CSS/JavaScript**: Frontend for creating responsive and interactive user interfaces.
- **Bootstrap**: Frontend framework for building responsive designs.
- **MySQL**: Relational database to store and manage election and voter details.

## Features

- **Election Management**: Admins can create, manage, and monitor elections.
- **Voter Registration**: Users can register as voters with their details.
- **Secure Voting**: Authenticated voters can cast their votes securely.
- **Election Results**: Real-time calculation and display of election results.
- **User Authentication**: Separate authentication for voters and administrators.
- **Email Notifications**: The system sends email notifications for registration, voting confirmation, and other important updates.

## Endpoints

The application provides several RESTful endpoints for interacting with the voting system:

- **/api/elections**: Create, manage, and view elections.
- **/api/candidates**: Add and manage candidates for elections.
- **/api/vote**: Cast and manage votes.
- **/api/users**: Manages user registration and login.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MySQL

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/train-booking-app.git
   cd train-booking-app
   ```

2. **Configure MySQL:**

   - Create a database named `train_booking`.
   - Update the `application.properties` file with your MySQL credentials.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/train_booking
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. **Build the application:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

5. **Access the application:**

   - The app will be accessible at `http://localhost:8080`.

## Usage

- **Train Search**: Go to the `/trains` endpoint and enter your journey details to search for available trains.
- **Booking Tickets**: Use the `/bookings` endpoint to book your tickets.
- **Manage Bookings**: View or cancel your bookings via the `/bookings` endpoint.
- **User Login**: Register or log in using the `/users` endpoint to manage your bookings securely.

## Contributing

If you would like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## License

This project is open-source and available under the [MIT License](LICENSE).
