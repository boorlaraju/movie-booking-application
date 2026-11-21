
# 🎬 CineVerse – Movie Booking Application

A full-stack Movie Ticket Booking Application built using **React, Spring Boot, Spring Security, JWT Authentication, and MySQL**.

CineVerse provides a seamless platform where users can explore movies, view available shows, book movie tickets, and manage their bookings. It also supports administrative movie management.

---

## 🚀 Features

### 👤 User Features
- User Registration and Login
- Secure JWT-based Authentication
- Browse Available Movies
- View Movie Details
- Explore Available Shows
- Book Movie Tickets
- View Booking History
- Cancel Bookings

### 🔐 Admin Features
- Secure Admin Authentication
- Add New Movies
- View All Movies
- Delete Movies
- Manage Movie Information

### ⚡ Additional Features
- RESTful API Integration
- Responsive React User Interface
- Spring Security Authentication
- MySQL Database Integration
- Axios API Communication
- Protected Routes

---

## 🛠️ Technology Stack

| Category | Technologies |
|---|---|
| Frontend | React.js, Vite, JavaScript |
| Styling | CSS, Bootstrap |
| Backend | Java, Spring Boot |
| Security | Spring Security, JWT |
| Database | MySQL |
| API | REST APIs |
| HTTP Client | Axios |
| Build Tool | Maven |
| Version Control | Git, GitHub |

---

## 📂 Project Structure

```text
movie-booking-application/
│
├── movie-booking-frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── context/
│   │   └── App.jsx
│   ├── package.json
│   └── .env.example
│
├── movie_booking_backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
└── README.md
```

---

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/boorlaraju/movie-booking-application.git

cd movie-booking-application
```

### 2. Database Configuration

1. Install MySQL Server.
2. Create a database for the application.
3. Configure your database credentials in the Spring Boot `application.properties` file.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movie_booking_system
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Use the database name and configuration expected by your backend.

### 3. Run the Backend

Navigate to the backend folder:

```bash
cd movie_booking_backend
```

Build the application:

```bash
mvn clean install
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

Backend URL:

```text
http://localhost:8080
```

### 4. Run the Frontend

Open another terminal:

```bash
cd movie-booking-frontend
```

Install dependencies:

```bash
npm install
```

Create a `.env` file:

```env
VITE_API_BASE_URL=http://localhost:8080
```

Start the React development server:

```bash
npm run dev
```

Frontend URL:

```text
http://localhost:5173
```

---

## 🔗 Backend API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/registernormaluser` | Register User |
| POST | `/api/auth/login` | User Login |
| GET | `/api/movies/getallmovies` | Get All Movies |
| POST | `/api/movies/addmovie` | Add Movie |
| DELETE | `/api/movies/deletemovie/{id}` | Delete Movie |
| GET | `/api/show/getallshows` | Get All Shows |
| GET | `/api/show/getshowsbymovie/{id}` | Get Shows By Movie |
| POST | `/api/booking/createbokking` | Create Booking |
| GET | `/api/booking/getuserbookings/{id}` | Get User Bookings |
| PUT | `/api/booking/{id}/cancel` | Cancel Booking |

---

## 🔒 Authentication

The application uses JWT (JSON Web Token) authentication.

- Users authenticate using their credentials.
- The backend generates a JWT token after successful login.
- The frontend stores the authentication token.
- Axios sends the token with protected API requests.
- Spring Security validates the token before granting access.

---

## 🎯 Application Workflow

1. Users register or log in.
2. Users browse available movies.
3. Users select a movie.
4. Users explore available shows.
5. Users book movie tickets.
6. Users view their booking history.
7. Users can cancel eligible bookings.

---

## 🔮 Future Enhancements

- Online Payment Gateway Integration
- Seat Selection System
- Email Booking Confirmation
- Movie Reviews and Ratings
- Real-Time Seat Availability
- Movie Search and Filtering
- Admin Dashboard Analytics

---

## 👨‍💻 Developer

**Boorla Raju**

- GitHub: [@boorlaraju](https://github.com/boorlaraju)
- Project: [Movie Booking Application](https://github.com/boorlaraju/movie-booking-application)

---

## 📜 License

This project is developed for educational and portfolio purposes.

Feel free to explore and learn from the source code.

---

⭐ If you find this project useful, consider giving it a star!
