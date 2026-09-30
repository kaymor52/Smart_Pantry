# SMART PANTRY

## Description

Smart Pantry is a Java-based pantry management app that helps keep track of added ingredients, which will also be used to determine which recipes can be made with the available ingredients.

## Features

* Add pantry ingredients
* Edit pantry ingredients
* Delete pantry ingredients
* Store ingredient quantities and units
* Optional expiry dates
* Alerts for ingredients expiring soon
* View available recipes
* Strict recipe ingredient matching
* "Almost There" recipes missing one ingredient
* View recipe details, ingredients and instructions
* Local SQLite database
* Settings screen
* Bottom navigation for easy screen switching

## Database

SQLite was chosen because it allows the application to operate without requiring an internet connection. This makes the app more suitable for managing pantry data locally, while also reducing the complexity of relying on an external database server or network connection.

## Requirements

Before running the application, install:

* Android Studio
* Android SDK
* Java Development Kit (JDK)

## Setup Instructions

1. Clone the Smart Pantry GitHub repository.
2. Open Android Studio.
3. Select **Open** and choose the cloned Smart Pantry project folder.
4. Allow Android Studio to sync and build the Gradle project.
5. Connect an Android device with USB debugging enabled, or create an Android Emulator.
6. Select the device/emulator in Android Studio.
7. Click the **Run** button.