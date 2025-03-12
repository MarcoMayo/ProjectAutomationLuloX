
# LuloX Automation Project 🚀

Automated testing for **LuloX** platform, covering both **Backend (REST API)** and **Frontend (Web Chat)** using **Screenplay Pattern** with **Serenity BDD** and **Cucumber**.

## 📁 Project Structure

📂 LuloX_PruebaAuto  
┣ 📂 src  
┃ ┣ 📂 main  
┃ ┗ 📂 test  
┃   ┣ 📂 java  
┃   ┃ ┣ 📂 models  
┃   ┃ ┣ 📂 runners  
┃   ┃ ┣ 📂 stepdefinitions  
┃   ┃ ┣ 📂 tasks  
┃   ┃ ┃ ┣ 📂 back  
┃   ┃ ┃ ┗ 📂 front  
┃   ┃ ┗ 📂 userinterface  
┃   ┗ 📂 resources  
┃     ┗ 📂 features


---

## 📌 Technologies & Tools

| Layer      | Tools / Frameworks                     |
|------------|----------------------------------------|
| Language   | Java, Gherkin                          |
| Build Tool | Gradle                                 |
| Framework  | Serenity BDD + Screenplay Pattern      |
| API Client | REST Assured (integrated with Serenity) |
| UI Testing | Selenium WebDriver + Serenity BDD      |
| Assertions | Hamcrest                               |
| Reporting  | Serenity Reports (HTML, JSON)          |
| Version Control | Git                                    |

---

## 🚀 Backend - REST API Tests

#### ➡️ Prerequisites
- Java 21
- Gradle 8.x
- Internet connection (to reach the API)

➡️ Run Tests
```bash
./gradlew clean test --tests ObjectsRestApiRunner aggregate
```

➡️ Serenity Report  
After running tests, open:  
LuloX_PruebaAuto/target/site/serenity/index.html

---

## 🚀 Frontend - Web Chat Tests

➡️ Prerequisites
- Java 21
- Gradle 8.x
- Chrome browser (or compatible WebDriver browser)
- ChromeDriver (managed automatically with WebDriverManager)

➡️ Run Tests  
```bash
./gradlew clean test --tests ChatLuloXRunner aggregate
```

➡️ Serenity Report  
After running tests, open:  
LuloX_PruebaAuto/target/site/serenity/index.html

---

## ✉️ Authors
QA Automation Engineer: Marco Antonio Mayo G{omez  
Contact: marco.a.mayo@hotmail.com

## 📝 Notes
- Backend testing uses the public API `https://api.restful-api.dev/objects`
- Frontend testing is **LuloX Web Chat** 
