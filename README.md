# SauceDemo Automation

This project automates login and product data scraping from [SauceDemo](https://www.saucedemo.com/) using Selenium in Java.

---

## 🚀 Features

- Logs in using credentials from an external config file
- Extracts product name, description, and price
- Writes scraped data into an Excel sheet
- Organized modular structure with reusable utility classes

---

## 📁 Folder Structure

<pre> ## 📁 Folder Structure <code>```text
/SauceDemoAutomation
│
├── /src
│   ├── /loginClass
│   ├── /uiAutomation
│   ├── /UtilityClasses
│   └── Main1.java
│
├── /Configuration
│   └── config.properties
│
├── .gitignore
└── README.md ```</code> </pre>

- **`loginClass`**: Initializes the WebDriver and handles the login process for saucedemo.com
- **`uiAutomation`**: Contains logic to scrape product data
- **`UtilityClasses`**: Includes `ReadConfigFile` to read credentials from the `config.properties` file

> 🔐 **Note:** The `config.properties` file contains your username and password. Make sure to exclude it from version control using `.gitignore`.

> 📄 **Excel Requirement:**
>
> - Create an empty Excel file named `Automation.xlsx` before running the script.
> - Update the read/write path in the code as per your local setup.

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Ninaaddd/SauceDemo-scraper.git
   ```
2. Open the project in Eclipse
3. Add your own config.properties file in the /Configuration directory:
   username=yourUsername
   password=yourPassword
4. Ensure Automation.xlsx exists in the expected path
5. Run the Main1.java file
