# Android build logic convention gradle plugin

## Prerequisites

- This plugin is intended to be used **only in Android projects** (e.g., app or library modules using the Android Gradle Plugin).
- Android project must have configured `libs.versions.toml` file with next required content:

```toml
[versions]
# region android-build-logic
compileSdk = "35"
minSdk = "24"
targetSdk = "35"
applicationId = "io.github.aleksrychkov.example"
versionCode = "1"
versionName = "0.0.1"
# endregion

kotlin = "2.2.0"
agp = "8.11.1"


[libraries]
# region android-build-logic
android-gradle-plugin = { module = "com.android.tools.build:gradle", version.ref = "agp" }
kotlin-gradle-plugin = { module = "org.jetbrains.kotlin:kotlin-gradle-plugin", version.ref = "kotlin" }
# endregion

```

## Setup

- Navigate to root folder.
- Initialize a Git repository if one hasn’t been created already.
- Run command:
```shell
git submodule add git@github.com:AleksRychkov/android-build-logic.git android-build-logic
```
- Check for `.gitmodules` file and `/android-build-logic` folder.
- Add convention plugin:
  - Open `settings.gradle[.kts]`
  - Add `includeBuild("android-build-logic")`
  ```kotlin
  pluginManagement {
    includeBuild("android-build-logic")
  }
  ```
- Add build logic plugin to project:
  - For android application (`build.gradle.kts`):
  ```kotlin
  plugins {
    id("build-logic.application")
  }
  ```
  - For android library (`build.gradle.kts`):
  ```kotlin
  plugins {
    id("build-logic.android-library")
  }
  ```
  - For java/kotlin library (`build.gradle.kts`):
  ```kotlin
  plugins {
    id("build-logic.library")
  }
  ```
- Run gradle sync

### Project Structure Example
```text
root/
├── gradle/
│   └── libs.versions.toml
├── settings.gradle.kts
├── app/
│   └── build.gradle.kts
└── android-build-logic/    
```
