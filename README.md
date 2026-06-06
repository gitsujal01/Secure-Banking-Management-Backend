🏦 FinVault - Secure Banking Management System(Backend)

FinVault is a full-stack secure banking application built using Spring Boot, React, TypeScript, JWT Authentication, and MySQL. It provides a modern banking experience with secure authentication, account management, money transfers, transaction history, admin management, and employee operations.

---

🚀 Features

👤 Customer Features

- Secure Login & Registration
- JWT-based Authentication
- Account Dashboard
- View Account Balance
- Deposit Money
- Withdraw Money
- Transfer Funds
- Transaction History
- Profile Management
- Forgot Password with OTP Verification

👨‍💼 Admin Features

- Admin Dashboard
- View All Users
- View All Employees
- View All Transactions
- Monitor Banking Statistics
- Manage Customer Accounts

👨‍💻 Employee Features

- Employee Dashboard
- Customer Support Operations
- Account Monitoring

🔒 Security Features

- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authorization
- OTP-based Password Recovery
- Secure REST APIs

---

🛠️ Tech Stack

Frontend

- React
- TypeScript
- TanStack Router
- Tailwind CSS
- Shadcn UI
- Lucide Icons
- Vite

Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT Authentication
- Hibernate

Database

- MySQL

---

📸 Application Screenshots

Login Page

"Login Page" (screenshots/login.png)
![image alt](https://github.com/gitsujal01/secure-financial-hub/blob/5e8c3e5d74c0781bc3ffa71f9b4c923cca772564/Screenshot%202026-06-06%20232948.png)

---

Customer Dashboard
![image alt](https://github.com/gitsujal01/secure-financial-hub/blob/048a02f4ab7fd79bdefb7e075f57194a83ed9224/Screenshot%202026-06-06%20233608.png)
"Customer Dashboard" (screenshots/customer-dashboard.png)

---

Admin Dashboard
![image alt](https://github.com/gitsujal01/secure-financial-hub/blob/87efb45773676069b52e696c0f6320e98f791ffe/Screenshot%202026-06-06%20233338.png)
"Admin Dashboard" (screenshots/admin-dashboard.png)

---

Forgot Password (OTP Verification)
![image alt](https://github.com/gitsujal01/secure-financial-hub/blob/441666608d0bcf4f940dec48648a84af2df8e25a/Screenshot%202026-06-06%20233217.png)
"Forgot Password" (screenshots/forgot-password.png)

---

📂 Project Structure

FinVault
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── security
│   └── config
│
├── frontend
│   ├── routes
│   ├── components
│   ├── lib
│   ├── hooks
│   └── ui
│
└── database

---

⚙️ Installation

Clone Repository

git clone https://github.com/gitsujal01/secure-financial-hub.git
cd secure-financial-hub

---

Backend Setup

Configure MySQL

Update:

application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/finvault
spring.datasource.username=root
spring.datasource.password=your_password

Run Backend

mvn spring-boot:run

Backend runs on:

http://localhost:8081

---

Frontend Setup

Install dependencies:

npm install

Run application:

npm run dev

Frontend runs on:

http://localhost:8080

---

API Endpoints

Authentication

POST /api/auth/register
POST /api/auth/login
POST /api/auth/forgot-password/send-otp
POST /api/auth/forgot-password/reset

Account

GET  /account
POST /deposit
POST /withdraw
POST /transfer

Admin

GET /admin/dashboard
GET /admin/users
GET /admin/employees
GET /admin/transactions

---

Future Enhancements

- Email Notifications
- Account Statements PDF
- Loan Management
- Investment Dashboard
- Two-Factor Authentication
- Audit Logging
- Docker Deployment
- Cloud Deployment

---

Author

Sujal Lokhande

GitHub:
https://github.com/gitsujal01

LinkedIn:
https://linkedin.com/in/sujal-lokhande

---

License

This project is developed for educational and portfolio purposes.
© 2026 FinVault.
