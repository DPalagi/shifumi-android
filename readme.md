# Shifumi - Android app

Application coded and tested in the scope of test recruitment from MyCoach Sport company.

## Requirements

- The app is running on Android 5.0 (API 21) minimum and is compatible with higher versions.
- No special proguard rules are applied, like minification etc... so code (breakpoints) and debug logs can be accessible at runtime.
- Git repo contains only 2 branches : `master` and `develop` as I'm working alone on the project.
- Source code is actually at version `1.0`, if further code is added to it, git `tags` will keep tracks of it.

## Get the code
You can clone the code from this repository directly with the `git clone` command. If you want to provide access to your team for review, just gave me their e-mail addresses and I will add them as redactor of the project.

## Run instructions
Once the code is downloaded on your local machine you have 2 ways to deploy it on an Android device (including simulator if physical device is not available).

### With Android Studio
- Open Android Studio and select `Open Project`
- Select source code folder
- Project is now loaded, you can click on the "green arrow" (DC Comics©) at top of the screen
- Select the target you want to deploy on (simulator or device)
- Enjoy the app!

### With command lines
- Go to source code folder
- Make sure to have Gradle on the machine `gradle -v`
- Make sure to have ADB on the machine `adb --version`
- Build APK `./gradlew assembleDebug`
- Deploy to target `adb -d install path/to/your_app.apk`
- Enjoy the app!

## About tests
- I've removed the end to end tests parts, as I don't think they can be useful for this coding puzzle.
- Developpment was not test driven, like for unicorns so far I haven't seen it in real life but I'm excited to do so (if you do it in MyCoach).
- Game rules are ensured by the JUnit part, so we can easily extends and add more actions (Lizard, Spock...) to the game and be sure to keep the same logic.

## About design patterns
- I've really hesitated between `polymorphism` and `genericity` (as I finally did it).
- I usually keep `poly`(short name, I know him well) for bigger structures like complete models etc...
- Inheritance, between `Player` and `IAPlayer` (yes, random is a kind of IA... I guess!).

## Android specific elements
- Currently using some `Fragment` in the app to avoid useless `Activity`
- Custom animations are used, in both `XML` and `ObjectAnimator` way
- Strings are referenced in the correct file if internationnalization is a requirement
- UI elements style are referenced in the appropriate file in order to be re-usable
