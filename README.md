# HaMivchanon - Android Study Management App 📱🎓

## About The Project
"HaMivchanon" is a native Android application developed as a 5-unit Computer Science final project. The app is designed to help students optimize their learning process by providing smart study reminders, time management tools, and a centralized workspace for study materials (documents and images).

*Note: This repository contains the comprehensive Project Portfolio (PDF) which includes the system architecture, UML diagrams, data models, and UI/UX flows. The original source code is no longer available, but the documentation fully demonstrates the technical implementation, planning, and design patterns used.*

## Core Features
*   **Smart Study Reminders:** Scheduled local push notifications utilizing `WorkManager` and `BroadcastReceiver` to manage ongoing alerts based on user-defined date ranges and specific times.
*   **Centralized Workspace ("My Class"):** A dedicated area for each subject to upload and manage relevant study materials directly from the device.
*   **Media & Document Integration:** Users can capture photos via the device camera, select images from the gallery, or upload PDF documents from local storage.
*   **Cloud Synchronization:** Real-time data storage and media hosting using Google Firebase (Realtime Database & Storage).
*   **User Authentication:** Simple email-based identification system managed locally via `SharedPreferences`.

## Built With
*   **Language:** Java
*   **Environment:** Android Studio
*   **Backend / Database:** Firebase Realtime Database (NoSQL)
*   **Cloud Storage:** Firebase Storage
*   **Android Components:** WorkManager, BroadcastReceiver, RecyclerView, Adapters, Intents, Dialogs.

## System Architecture & Documentation
The attached PDF portfolio contains deep technical documentation including:
*   Use Case and Sequence Diagrams
*   Class Diagrams (UML) for activities, adapters, and base classes
*   Database structure and JSON data models
*   Top-Down level design
