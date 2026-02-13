# CV Automation Feature Guide

## Overview
This Android application has been transformed into a comprehensive CV (Curriculum Vitae) Builder using Jetpack Compose. The app allows users to create, edit, and preview their professional CV in a modern, user-friendly interface.

## Features

### 1. Personal Information Section
- Full Name
- Email Address
- Phone Number
- Physical Address
- Professional Summary

### 2. Education Section
- Add multiple education entries
- Degree/Qualification
- Institution Name
- Year/Duration
- GPA/Grade
- Remove individual entries

### 3. Work Experience Section
- Add multiple work experiences
- Position/Job Title
- Company Name
- Duration
- Job Description
- Remove individual entries

### 4. Skills Section
- Add multiple skills
- Skill Name
- Proficiency Level
- Remove individual skills

### 5. CV Preview
- Professional CV layout
- Clean, organized display
- Easy navigation back to edit mode

## How to Use

### Building Your CV

1. **Launch the App**: Upon opening, you'll see the CV Input Screen with sample data pre-populated.

2. **Edit Personal Information**:
   - Fill in your name, email, phone, address
   - Add a professional summary highlighting your expertise

3. **Add Education**:
   - Click "+ Add Education" to add education entries
   - Fill in degree, institution, year, and GPA
   - Use "Remove" button to delete unwanted entries

4. **Add Work Experience**:
   - Click "+ Add Experience" to add work experiences
   - Fill in position, company, duration, and description
   - Use "Remove" button to delete entries

5. **Add Skills**:
   - Click "+ Add Skill" to add skills
   - Specify skill name and proficiency level
   - Use "×" button to remove skills

6. **Preview Your CV**:
   - Click "Preview CV" button at the bottom
   - View your formatted CV
   - Click "← Back to Edit" to make changes

## Technical Implementation

### Data Models
- `CVData`: Main data class containing all CV information
- `PersonalInfo`: Personal details
- `Education`: Education entries
- `Experience`: Work experience entries
- `Skill`: Individual skills

### UI Components
- `CVInputScreen`: Main editing interface
- `CVPreviewScreen`: Professional CV display
- `EducationItem`: Card-based education entry editor
- `ExperienceItem`: Card-based experience entry editor
- `SkillItem`: Inline skill editor

### Navigation
- Uses Jetpack Navigation Compose
- Two main routes: "home" (edit) and "preview"
- State is preserved during navigation

## Sample Data
The app includes sample data for Richard Giansanto (Student Number: 00000076941) to demonstrate the CV format:
- Name: Richard Giansanto
- Email: richard.giansanto@example.com
- Education: Bachelor of Computer Science
- Skills: Kotlin, Jetpack Compose, Android Development
- Experience: Android Developer at Tech Company

## Building the Project

### Prerequisites
- Android Studio
- Android SDK 24 or higher
- Kotlin 2.0.0
- Android Gradle Plugin 8.5.0

### Build Instructions
```bash
./gradlew assembleDebug
```

### Run on Emulator/Device
```bash
./gradlew installDebug
```

## Technologies Used
- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit
- **Material 3**: Design system
- **Navigation Compose**: Navigation framework
- **State Management**: remember and mutableStateOf

## Future Enhancements
- Export CV as PDF
- Multiple CV templates
- Save/Load CV data locally
- Share CV via email or social media
- Add profile photo support
- Support for multiple languages
- Cloud backup integration

## Code Structure
```
app/src/main/java/com/example/lab_week_09/
├── MainActivity.kt       # Main activity and UI screens
├── CVData.kt            # Data models
└── ui/theme/            # Theme and styling
    ├── Theme.kt
    ├── Color.kt
    ├── Type.kt
    └── Elements.kt
```

## License
This project is part of LAB_WEEK_09 coursework focusing on Building User Interfaces using Jetpack Compose.
