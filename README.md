# Shuffle Service

A simple Spring Boot application that provides an API to generate a shuffled array of numbers and log user actions.

## Features
- Generate a shuffled array of integers.
- Log data using an external HTTP logging service.
- Built with Spring Boot.

---

## Prerequisites

1. **Java:** Install Java 17 or higher.
2. **Maven:** Ensure you have Maven installed.
3. **IDE:** IntelliJ IDEA (Ultimate or Community Edition).

---

## Steps to Run the Application in IntelliJ IDEA

1. **Clone the repository** or download the project:
    ```bash
    git clone <repository-url>
    cd <project-directory>
    ```

2. **Open the project in IntelliJ IDEA:**
   - Open IntelliJ IDEA.
   - Click `File -> Open`.
   - Select the project's folder and open it.

3. **Configure Application Properties:**
   - Ensure the `application.yml` file in `src/main/resources` is configured correctly. For example:
     ```yaml
     service:
       log:
         url: http://localhost:8081/api/log
     ```

4. **Build the project:**
   - Open the Maven tool window on the right side of IntelliJ (`View -> Tool Windows -> Maven`).
   - Click on `Lifecycle -> clean` and `Lifecycle -> install`.

5. **Run the application:**
   - Navigate to the `ServiceShuffleApplication.java` file in the `src/main/java` directory.
   - Right-click on the `ServiceShuffleApplication` class and select `Run 'ServiceShuffleApplication.main()'`.

6. **Test the application:**
   - The application will start on port `8080` by default.
   - Test the endpoint using tools like `curl`, Postman, or a browser:
     ```
     POST http://localhost:8080/api/shuffle
     Body: 10
     Content-Type: application/json
     ```

---

## Running Tests
Run the test cases using IntelliJ:
1. Navigate to `ShuffleControllerTest.java` in the `src/test/java` directory.
2. Right-click the file and select `Run 'ShuffleControllerTest'`.

---

### Additional Notes
- Ensure the logging service URL (`service.log.url`) is reachable.
- You can change the default server port by editing the `application.yml` file:
  ```yaml
  server:
    port: 9090
  ```

---
