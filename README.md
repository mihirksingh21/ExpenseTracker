~~~~bash
Directory Structure
Directory structure:
└── mihirksingh21-ExpenseTracker/
    ├── gradle.properties
    ├── gradlew.bat
    ├── gradle/
    │   ├── libs.versions.toml
    │   └── wrapper/
    │       └── gradle-wrapper.properties
    ├── settings.gradle.kts
    ├── gradlew
    ├── build.gradle.kts
    └── app/
        ├── .gitignore
        ├── proguard-rules.pro
        ├── build.gradle.kts
        └── src/
            ├── main/
            │   ├── AndroidManifest.xml
            │   ├── java/
            │   │   └── com/
            │   │       └── example/
            │   │           └── expensetracker/
            │   │               ├── di/
            │   │               │   ├── PreferencesModule.kt
            │   │               │   └── DatabaseModule.kt
            │   │               ├── data/
            │   │               │   ├── preferences/
            │   │               │   │   └── UserPreferences.kt
            │   │               │   ├── local/
            │   │               │   │   ├── Converters.kt
            │   │               │   │   ├── entity/
            │   │               │   │   │   ├── ExpenseEntity.kt
            │   │               │   │   │   └── UserEntity.kt
            │   │               │   │   ├── dao/
            │   │               │   │   │   ├── ExpenseDao.kt
            │   │               │   │   │   └── UserDao.kt
            │   │               │   │   └── ExpenseDatabase.kt
            │   │               │   ├── models/
            │   │               │   │   ├── User.kt
            │   │               │   │   └── Expense.kt
            │   │               │   └── repository/
            │   │               │       ├── UserRepository.kt
            │   │               │       └── ExpenseRepository.kt
            │   │               ├── ui/
            │   │               │   ├── viewmodel/
            │   │               │   │   ├── AddExpenseViewModel.kt
            │   │               │   │   ├── RegistrationViewModel.kt
            │   │               │   │   ├── LoginViewModel.kt
            │   │               │   │   ├── ThemeViewModel.kt
            │   │               │   │   ├── HomeViewModel.kt
            │   │               │   │   └── SettingsViewModel.kt
            │   │               │   ├── screens/
            │   │               │   │   ├── HomeScreen.kt
            │   │               │   │   ├── RegistrationScreen.kt
            │   │               │   │   ├── AddExpenseScreen.kt
            │   │               │   │   ├── SettingsScreen.kt
            │   │               │   │   └── LoginScreen.kt
            │   │               │   └── theme/
            │   │               │       ├── Theme.kt
            │   │               │       ├── Type.kt
            │   │               │       └── Color.kt
            │   │               ├── MainActivity.kt
            │   │               └── ExpenseTrackerApp.kt
            │   └── res/
            │       ├── xml/
            │       │   ├── backup_rules.xml
            │       │   └── data_extraction_rules.xml
            │       ├── mipmap-mdpi/
            │       │   ├── ic_launcher.webp
            │       │   └── ic_launcher_round.webp
            │       ├── mipmap-xxxhdpi/
            │       │   ├── ic_launcher.webp
            │       │   └── ic_launcher_round.webp
            │       ├── mipmap-hdpi/
            │       │   ├── ic_launcher.webp
            │       │   └── ic_launcher_round.webp
            │       ├── mipmap-xxhdpi/
            │       │   ├── ic_launcher.webp
            │       │   └── ic_launcher_round.webp
            │       ├── drawable/
            │       │   ├── ic_launcher_background.xml
            │       │   └── ic_launcher_foreground.xml
            │       ├── mipmap-xhdpi/
            │       │   ├── ic_launcher.webp
            │       │   └── ic_launcher_round.webp
            │       ├── mipmap-anydpi-v26/
            │       │   ├── ic_launcher.xml
            │       │   └── ic_launcher_round.xml
            │       └── values/
            │           ├── strings.xml
            │           ├── themes.xml
            │           └── colors.xml
            ├── test/
            │   └── java/
            │       └── com/
            │           └── example/
            │               └── expensetracker/
            │                   └── ExampleUnitTest.kt
            └── androidTest/
                └── java/
                    └── com/
                        └── example/
                            └── expensetracker/
                                └── ExampleInstrumentedTest.kt
~~~~
