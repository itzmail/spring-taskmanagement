# Task Management API

TESTING n8n

Aplikasi REST API untuk manajemen tugas yang dibangun menggunakan Spring Boot dengan fitur autentikasi JWT dan integrasi database PostgreSQL.

## 🚀 Fitur Utama

- **Manajemen Tugas**: Create, Read, Update, Delete (CRUD) tasks
- **Autentikasi JWT**: Keamanan API dengan JSON Web Token
- **Database PostgreSQL**: Penyimpanan data persisten dengan JPA/Hibernate
- **Dokumentasi API**: Dokumentasi interaktif dengan Swagger UI
- **Validasi Request**: Validasi input dengan Spring Boot Validation
- **Keamanan**: Integrasi Spring Security
- **Status Task**: TO_DO, IN_PROGRESS, DONE

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **JWT (JSON Web Tokens)**
- **Maven**
- **Lombok**
- **SpringDoc OpenAPI (Swagger)**

## 📋 Prerequisites

Pastikan Anda telah menginstall:

- Java 17 atau yang lebih baru
- Maven 3.6+
- PostgreSQL database
- Git

## ⚙️ Setup & Konfigurasi

### 1. Clone Repository

```bash
git clone <repository-url>
cd task-management
```

### 2. Setup Database

Buat database PostgreSQL baru:

```sql
CREATE DATABASE task_management;
CREATE USER task_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE task_management TO task_user;
```

### 3. Konfigurasi Application Properties

Salin file konfigurasi contoh dan sesuaikan dengan kredensial database Anda:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=task-management

# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/task_management
spring.datasource.username=task_user
spring.datasource.password=your_password

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Swagger Documentation
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.info.title=Task Management API
springdoc.info.version=1.0.0
springdoc.info.description=REST API for managing tasks for the Fullstack Test.
```

## 🚀 Menjalankan Aplikasi

### Menggunakan Maven Wrapper (Recommended)

```bash
# Clean dan compile
./mvnw clean compile

# Menjalankan aplikasi
./mvnw spring-boot:run
```

### Menggunakan JAR File

```bash
# Build JAR file
./mvnw clean package

# Jalankan JAR file
java -jar target/task-management-0.0.1-SNAPSHOT.jar
```

### Untuk Windows

```cmd
# Menggunakan Maven Wrapper di Windows
mvnw.cmd clean compile
mvnw.cmd spring-boot:run
```

Aplikasi akan berjalan di `http://localhost:8080`

## 📚 Dokumentasi API

Setelah aplikasi berjalan, akses dokumentasi API interaktif di:

**Swagger UI**: http://localhost:8080/swagger-ui.html

## 🔗 API Endpoints

### Authentication
- `POST /api/auth/login` - Login pengguna

### Task Management
- `GET /api/tasks` - Mendapatkan semua task
- `POST /api/tasks` - Membuat task baru
- `GET /api/tasks/{id}` - Mendapatkan task berdasarkan ID
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Hapus task

## 📝 Contoh Request/Response

### Login
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
```

Response:
```json
{
  "status": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer"
  }
}
```

### Membuat Task Baru
```bash
POST /api/tasks
Authorization: Bearer <your-token>
Content-Type: application/json

{
  "title": "Implementasi fitur login",
  "description": "Membuat sistem autentikasi dengan JWT",
  "status": "TO_DO"
}
```

### Mendapatkan Semua Task
```bash
GET /api/tasks
Authorization: Bearer <your-token>
```

Response:
```json
{
  "status": true,
  "message": "Success get data",
  "data": [
    {
      "id": 1,
      "title": "Implementasi fitur login",
      "description": "Membuat sistem autentikasi dengan JWT",
      "status": "TO_DO"
    }
  ]
}
```

## 🔐 Autentikasi

API ini menggunakan JWT (JSON Web Token) untuk autentikasi. Untuk mengakses endpoint yang dilindungi:

1. Login menggunakan endpoint `/api/auth/login`
2. Sertakan JWT token dalam Authorization header: `Bearer <your-token>`

## 📊 Status Task

Task memiliki 3 status:
- `TO_DO` - Belum dikerjakan
- `IN_PROGRESS` - Sedang dikerjakan  
- `DONE` - Selesai

## 🧪 Testing

Jalankan test menggunakan Maven:

```bash
# Menjalankan semua test
./mvnw test

# Menjalankan test dengan coverage
./mvnw test jacoco:report
```

## 📁 Struktur Project

```
task-management/
├── src/
│   ├── main/
│   │   ├── java/com/aidecetest/task_management/
│   │   │   ├── config/          # Konfigurasi aplikasi
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── entity/         # JPA entities
│   │   │   ├── exception/      # Exception handling
│   │   │   ├── repository/     # Data repositories
│   │   │   ├── security/       # Konfigurasi security
│   │   │   ├── service/        # Business logic
│   │   │   └── TaskManagementApplication.java
│   │   └── resources/
│   │       ├── application.properties.example
│   │       └── application.properties
│   └── test/                   # Test classes
├── .mvn/                      # Maven wrapper
├── target/                    # Build output
├── mvnw                       # Maven wrapper (Unix)
├── mvnw.cmd                   # Maven wrapper (Windows)
├── pom.xml                    # Maven configuration
└── README.md
```

## 🔧 Troubleshooting

### Database Connection Issues
```bash
# Periksa apakah PostgreSQL berjalan
sudo systemctl status postgresql

# Restart PostgreSQL jika diperlukan
sudo systemctl restart postgresql
```

### Port Already in Use
```bash
# Cek process yang menggunakan port 8080
lsof -i :8080

# Kill process jika diperlukan
kill -9 <PID>

# Atau ubah port di application.properties
server.port=8081
```

### Java Version Issues
```bash
# Periksa versi Java
java -version

# Pastikan menggunakan Java 17
export JAVA_HOME=/path/to/java17
```

## 🐳 Docker Support (Opsional)

Untuk menjalankan dengan Docker:

```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim
COPY target/task-management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

```bash
# Build image
docker build -t task-management .

# Run container
docker run -p 8080:8080 task-management
```

## 📈 Performance Tips

1. **Database Indexing**: Tambahkan index pada kolom yang sering di-query
2. **Connection Pooling**: Konfigurasi HikariCP untuk performa optimal
3. **Caching**: Implementasi Redis untuk caching data yang sering diakses
4. **Pagination**: Gunakan pagination untuk list task yang banyak

## 🤝 Contributing

1. Fork repository
2. Buat feature branch (`git checkout -b feature/amazing-feature`)
3. Commit perubahan (`git commit -m 'Add some amazing feature'`)
4. Push ke branch (`git push origin feature/amazing-feature`)
5. Buka Pull Request

## 📄 License

Project ini dibuat untuk keperluan demonstrasi dan pembelajaran.

## 📞 Support

Jika mengalami masalah atau memiliki pertanyaan:

1. Periksa bagian Troubleshooting di atas
2. Buka issue di repository
3. Hubungi tim development

---

**⚠️ Penting**: Pastikan untuk mengupdate kredensial database di `application.properties` sebelum menjalankan aplikasi.

**📝 Catatan**: Aplikasi ini menggunakan `spring.jpa.hibernate.ddl-auto=update` yang akan otomatis membuat/update tabel database sesuai dengan entity yang didefinisikan.
