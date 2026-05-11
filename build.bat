@echo off
echo ====================================="
echo Yahya-AI APK Derleme Baslatliliyor
echo ====================================="

echo.
echo Kontrol: Python3
python --version >nul 2>&1
if errorlevel 1 (
    echo Hata: Python3 bulunamadi
    exit /b 1
)
echo OK

echo.
echo Kontrol: ANDROID_HOME
if "%ANDROID_HOME%"="" (
    echo Hata: ANDROID_HOME ortam degiskeni ayarlanmamis
    exit /b 1
)
echo OK - %ANDROID_HOME%

echo.
echo Android projesi derleniyorç
cd android
call gradlew.bat clean build

if errorlevel 1 (
    echo Hata: Derleme basarisiz
    exit /b 1
)

echo.
echo ====================================="
echo Derleme Basarili!
echo APK Konumu: android\app\build\outputs\apk\release\app-release.apk
echo ====================================="
pause
