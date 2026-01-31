# File-Based Database Management System (Java)

**Semester Project – 2nd Semester, PF**

This is a command-line database management system simulator built in Java as part of my 2nd-semester Programming Fundamentals project. I implemented this entirely myself and focused on understanding file handling, command parsing, and core CRUD logic.

---

## Features

- **CREATE TABLE** – Define tables with column names  
- **INSERT INTO** – Add rows of data  
- **SELECT FROM TABLE** – Retrieve data with optional filtering (`HAVING`) and sorting (`SORT BY`)  
- **UPDATE** – Modify existing rows based on a condition  
- **DELETE** – Remove rows based on a condition  
- **DROP TABLE** – Delete entire tables  
- **SHOW ALL** – List all tables  
- **Input validation** – Checks table/column names and command syntax  
- **Temporary file handling** – Safely updates and deletes data  

---

## Technical Details

- **Language:** Java  
- **Data Storage:** Tab-separated text files simulating tables  
- **Design:** Single-file CLI application, procedural with OOP concepts  
- **Concepts Practiced:**  
  - File I/O (BufferedReader, BufferedWriter, File handling)  
  - String parsing and regular expressions  
  - Command-line interface and user input handling  
  - CRUD operations and basic query logic  

---

## How to Run

1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/File-Based-DBMS-Java.git
   ```
2. Compile the Java file:  
   ```bash
   javac database_management_system.java
   ```
3. Run the program:  
   ```bash
   java database_management_system
   ```
4. Type `help` to see available commands.

---

## Notes

- This project was built as part of a university course in my 2nd semester.  
- The focus was on **correctness and learning**, not efficiency or production-ready architecture.  
- If I were to redo it today, I would refactor it into multiple classes, reduce redundant logic, and improve parsing efficiency.  

---

## Acknowledgements

Built entirely by me as a student project. No external libraries were used.

