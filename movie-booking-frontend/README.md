# CineVerse — React movie-booking frontend

A React + Vite frontend designed around the uploaded Spring Boot MovieBookingApplication controllers and DTOs.

## 1. Requirements
- Node.js 20 or newer recommended
- Your Spring Boot backend running at `http://localhost:8080`
- Existing database seeded with movies, theaters and shows to populate the customer pages

## 2. Run the frontend
Open a terminal in this folder:

```bash
npm install
npm run dev
```

Open the Vite URL, normally `http://localhost:5173`.

Optional: copy `.env.example` to `.env` and set `VITE_API_BASE_URL` to your backend URL. Restart Vite after changing `.env`.

## 3. Backend endpoints used
- `POST /api/auth/registernormaluser`
- `POST /api/auth/login`
- `GET /api/movies/getallmovies`
- `POST /api/movies/addmovie`
- `DELETE /api/movies/deletemovie/{id}`
- `GET /api/show/getallshows`
- `GET /api/show/getshowsbymovie/{id}`
- `POST /api/booking/createbokking`
- `GET /api/booking/getuserbookings/{id}`
- `PUT /api/booking/{id}/cancel`

Axios adds `Authorization: Bearer <jwtToken>` automatically after login. The backend CORS configuration you supplied allows `http://localhost:5173`.

## 4. Important backend gap: current user ID
Your login response DTO contains `jwtToken`, `username`, and `role`, but does not include the database user's `id`. Your booking DTO requires `userId`, and the booking history endpoint requires the user ID too. The frontend cannot reliably determine that ID from the current login response.

**Recommended fix:** add an authenticated endpoint such as `GET /api/users/me` that returns the current user's `id`, `username`, and `email`. Resolve the user from Spring Security's authenticated principal, not from a user ID supplied by the browser. After login, call this endpoint and store the returned ID in the frontend's user state. The current frontend has a temporary convenience fallback: if the user registered in this same browser, it can use the ID returned by your register endpoint. This is not a substitute for `/api/users/me` for existing users or production use.

## 5. Important seat availability gap
The uploaded backend checks seat conflicts on booking creation, but has no endpoint to list occupied seats for a particular show. The seat map is interactive, but it cannot accurately mark previously booked seats until the backend exposes an endpoint returning occupied seat numbers for a show. The server remains authoritative and can reject duplicate seats.

## 6. Booking status
Creating a booking in your service creates a `PENDING` booking. The UI reports that state. Your confirm endpoint exists, but no payment integration is implemented here; do not present a booking as paid/confirmed until the backend's payment/confirmation flow actually confirms it.

## 7. Admin
The admin page uses the existing `POST /api/movies/addmovie` and `DELETE /api/movies/deletemovie/{id}` endpoints. It also includes a role guard based on the `role` set returned by your login DTO. Your backend requires `ROLE_ADMIN` for admin routes. Do not expose admin registration to public users; keep it restricted on the server.

## 8. Real-world production checklist
Before public deployment, add `/api/users/me`, occupied-seat availability, transactional/locking protection against simultaneous seat booking, server-side validation, payment integration and webhook verification, booking confirmation emails, rate limiting, and environment-specific CORS. Use HTTPS and an appropriately strong JWT secret. The decorative poster artwork in this starter is generated with CSS; real poster images require a poster URL field/API.
