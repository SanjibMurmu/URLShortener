# 🔗 URL Shortener

A clean, efficient, and data-driven URL shortener built with **Spring Boot** and **H2 Database**. Create custom-aliased short links, track real-time click analytics, and monitor engagement over time — all through a modern, responsive UI.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Database](https://img.shields.io/badge/Database-H2-blue)
![License](https://img.shields.io/badge/License-Educational-lightgrey)

---

## ✨ Features

- **Custom Aliases** — Create personalized short URLs (e.g., `sanju.io/your-link`)
- **Persistent Storage** — Built-in support for file-based persistence, so your links survive restarts
- **Click Analytics** — Track the exact number of hits on each shortened link
- **Timestamp Tracking** — Monitor the "Last Clicked" date and time for every link
- **Modern UI** — A clean, responsive, card-based interface for managing links and viewing stats
- **Safety Checks** — Built-in protection against broken URLs, redirect loops, and duplicate links

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17+
- Maven

### Installation

**1. Clone the repository**
```bash
git clone https://github.com/SanjibMurmu/URLShortener.git
cd YOUR_REPO_NAME
```

**2. Configure storage**

Ensure `src/main/resources/application.properties` is configured for your environment. By default, it's set up for local file-based persistence.

**3. Run the application**
```bash
./mvnw spring-boot:run
```

**4. Access the app**

Open your browser to:
```
http://localhost:8080/index.html
```

---

## 📁 Project Structure

```
shortener/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── data/
│   └── urldb.mv.db                                  # H2 persistent database file
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/sanju/shortener/
│   │   │       ├── ShortenerApplication.java         # Main entry point
│   │   │       ├── controller/
│   │   │       │   └── UrlController.java            # REST endpoints
│   │   │       ├── dto/
│   │   │       │   ├── UrlRequest.java                # Request payload (realUrl, customAlias)
│   │   │       │   └── UrlStatsResponse.java           # Stats response payload
│   │   │       ├── entity/
│   │   │       │   └── UrlMapping.java                 # JPA entity
│   │   │       ├── repository/
│   │   │       │   └── UrlRepository.java              # Spring Data JPA repository
│   │   │       └── service/
│   │   │           └── UrlService.java                 # Business logic
│   │   └── resources/
│   │       ├── META-INF/
│   │       │   └── additional-spring-configuration-metadata.json
│   │       ├── static/
│   │       │   └── index.html                          # Frontend UI
│   │       ├── templates/
│   │       └── application.properties                  # DB config (H2 file-based storage)
│   └── test/
│       └── java/
│           └── com/sanju/shortener/
│               └── ShortenerApplicationTests.java
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw / mvnw.cmd                                      # Maven wrapper scripts
└── pom.xml                                              # Maven dependencies
```

---

## 📚 API Documentation

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/short` | Create a new short link (JSON body: `realUrl`, `customAlias`) |
| `GET` | `/{shortCode}` | Redirects to the original URL and tracks the click |
| `GET` | `/api/{shortCode}/stats` | View click counts and last-clicked timestamp |

**Example request** — creating a short link:
```bash
curl -X POST http://localhost:8080/api/short \
  -H "Content-Type: application/json" \
  -d '{"realUrl": "https://example.com/some/long/path", "customAlias": "my-link"}'
```

---

## 🛠️ Built With

**Backend**
- Spring Boot
- Spring Data JPA

**Database**
- H2 Database (file-based)

**Frontend**
- HTML5, CSS3
- JavaScript (Fetch API)

---

## 📄 License

This project is open-source and available for educational purposes.
