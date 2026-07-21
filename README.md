<p align="center">
  <img src="images/banner.png" alt="Physical Calculator banner">
</p>

# Physical Calculator

Android application developed in Java to support physics learning through calculators, interactive quizzes, user profiles and progress tracking.

## About the project

Physical Calculator was developed as an academic Android project for students studying Physics. The application combines calculation tools, quizzes and user management in a single mobile interface.

The project includes separate features for students and teachers, with information stored locally using SQLite.

## Features

- User registration and login
- Student profile management
- Physics formula calculator
- Interactive quizzes
- Score history
- Local data persistence with SQLite
- Teacher administration panel
- User administration
- Question administration
- Quiz administration
- Share and contact options

## Physics calculator

The calculator includes formulas related to electromagnetism, such as:

- Ampere's law
- Force on a straight conductor
- Lorentz force
- Faraday's law

## Technologies

- Java
- Android Studio
- SQLite
- XML
- Android Fragments
- Navigation Drawer
- Gradle

## Screenshots

<p align="center">
  <img src="images/login.png" width="220" alt="Login screen">
  <img src="images/register.png" width="220" alt="Registration screen">
  <img src="images/calculator.png" width="220" alt="Calculator screen">
</p>

<p align="center">
  <img src="images/quiz.png" width="220" alt="Quiz screen">
  <img src="images/scores.png" width="220" alt="Score history">
  <img src="images/admin.png" width="220" alt="Administration panel">
</p>

## Project structure

```text
app/
├── src/main/java/
│   └── com/example/proyecto_fisica/
│       ├── fragmentos/
│       ├── ui/
│       ├── AdministrarSQLite.java
│       ├── CalculadoraActivity.java
│       ├── MainActivity.java
│       └── ResultadoCalcuActivity.java
└── src/main/res/
    ├── drawable/
    ├── layout/
    ├── menu/
    ├── navigation/
    └── values/
Run the project
Clone the repository:
git clone https://github.com/LopezMigue01/android-physics-calculator.git
Open the project in Android Studio.
Wait for Gradle synchronization to finish.
Select an Android emulator or connected device.
Run the application.
Academic project

This application was originally developed as an academic team project.

This repository contains the version published and maintained by Miguel Ángel López Hernández as part of his software development portfolio.

Author

Miguel Ángel López Hernández

GitHub: LopezMigue01
