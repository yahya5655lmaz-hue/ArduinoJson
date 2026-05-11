#!/bin/bash

echo "====================================="
echo "🤖 Yahya-AI APK Derleme Başlatılıyor"
echo "====================================="

# Backend kontrol
echo "\n📌 Backend kontrol ediliyor..."
if ! command -v python3 &> /dev/null; then
    echo "❌ Python3 bulunamadı"
    exit 1
fi
echo "✅ Python3 bulundu"

# Android SDK kontrol
if [ -z "$ANDROID_HOME" ]; then
    echo "❌ ANDROID_HOME ortam değişkeni ayarlanmamış"
    exit 1
fi
echo "✅ ANDROID_HOME: $ANDROID_HOME"

# Gradle derleme
echo "\n📌 Android projesi derleniyorç"
cd android
./gradlew clean build

if [ $? -eq 0 ]; then
    echo "\n✅ Derleme başarılı!"
    echo "📍 APK konumu: android/app/build/outputs/apk/release/app-release.apk"
else
    echo "\n❌ Derleme başarısız"
    exit 1
fi

echo "\n====================================="
echo "✨ İşlem tamamlandı!"
echo "====================================="
