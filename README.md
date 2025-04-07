# jchain – Blockchain Private Network

A prototype private blockchain system built with Java 21 and Spring Boot. This project was created as an experimental environment to explore blockchain technology and its potential applications in business contexts.

## 🚀 Current Features

### 1. Employee Registration
- **Create Employees**: Register new employees with attributes such as name, email, department, position, and joining date.
- **Unique Key**: Each employee receives a unique key generated using a cryptographic hash based on their ID and creation timestamp.

### 2. Attendance Tracking
- **Check-in API**: Employees can record their check-ins using a REST API.
- **Key Validation**: The employee key is validated to ensure authenticity.
- **Timestamped Records**: Each entry includes date and time of attendance.

### 3. Blockchain Visualization
- **Block View**: API endpoints to visualize all blocks in the blockchain.
- **Attendance History**: List all check-ins by employee key.

## 🧱 Data Persistence

The blockchain data is temporarily persisted in encrypted JSON files to prevent data loss. This approach is planned to evolve into a more decentralized and distributed system in future iterations.

## 🔮 Upcoming Features

- **Supply Chain Management** – Product tracking throughout the entire supply chain.  
- **Document Management** – Secure storage and validation of documents.  
- **Voting Systems** – Implementation of a transparent and secure digital voting system.  
- **Smart Contracts** – Automation of legal agreements and business rules.  
- **Identity Management** – Secure handling of digital identities.  
- **Asset Monitoring** – Real-time tracking of physical and digital assets.

## 🛠 Technologies Used

- Java 21  
- Spring Boot  
- Lombok  
- Jackson  
- Postman (API testing)

## ▶️ How to Run

### Requirements
- Java 21
- Maven

### Steps
```bash
git clone https://github.com/sbrunomello/jchain.git
cd jchain
mvn spring-boot:run
```

## 📡 Available Endpoints

### ➕ Create Employee
```http
POST /api/employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "department": "IT",
  "position": "Developer",
  "joiningDate": "2023-06-01"
}
```

### 🕒 Register Attendance
```http
POST /api/attendances
Content-Type: application/json

{
  "employeeKey": "employee-unique-key",
  "attendanceTime": "2024-06-09T08:00:00"
}
```

### 📋 List Employee Attendances
```http
GET /api/attendances/{employeeKey}
```

### 🔗 View Blockchain
```http
GET /api/blockchain
```

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## 📬 Contact

- [Instagram](https://www.instagram.com/)
- [LinkedIn](https://www.linkedin.com/)

Developed with ❤️ by Mello.
