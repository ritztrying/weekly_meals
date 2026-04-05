# weekly_meals

# 🍽️ Weekly Meal Planner

A simple full-stack web application to plan, manage, and track weekly meals with support for bulk uploads and automatic weekly archiving.

---

## 🚀 Features

- 📅 Plan meals for each day (Monday–Sunday)
- 🍳 Separate tracking for Breakfast, Lunch, and Dinner
- 📊 View meals in **table view** and **day-wise view**
- 📂 Upload meals in bulk using CSV
- 🔁 Automatic weekly reset using scheduled jobs
- 🗂️ Historical storage of previous weeks' meals
- ⚡ Fast and lightweight UI (plain HTML, CSS, JS)
- 🧠 Backend built with Spring Boot + JPA + MariaDB

---

## ⚙️ Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA
- **Database:** MariaDB
- **Frontend:** HTML, CSS, JavaScript (no framework)
- **Deployment:** Raspberry Pi (self-hosted)

---

## 🔄 How it works

- Meals are stored with a `weekNo` to separate weekly data
- Every Monday at 00:00, a scheduled job:
    - Archives last week's meals into a history table
    - Clears old data while keeping the current week clean

---

## 🌐 Access

The application runs locally and can be accessed via:

Currently it is available on my network only: 
http://<my_pi_local_ip>:8080

---

## 📦 Future Improvements

- Edit meals directly from UI
- Weekly navigation (past/future weeks)
- Nutrition tracking (calories, protein)
- Multi-user support

---

A lightweight and practical solution for managing weekly meal planning, designed for simplicity and real-world usability.