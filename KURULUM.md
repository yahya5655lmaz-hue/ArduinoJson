# 📖 Detaylı Kurulum Rehberi

Bu rehber, **Yahya-AI**'yi adım adım kurmanıza yardımcı olacak.

---

## 🔧 Ön Koşullar

### Windows
1. [Git](https://git-scm.com/download/win) indirin ve yükleyin
2. [Python 3.8+](https://www.python.org/downloads/) indirin ve yükleyin
3. [Android Studio](https://developer.android.com/studio) indirin ve yükleyin
4. [Java JDK 11+](https://www.oracle.com/java/technologies/downloads/) indirin ve yükleyin

### macOS
```bash
# Homebrew kullanarak
brew install git python@3.11 openjdk@11

# Android Studio'yu manuel indirin: https://developer.android.com/studio
```

### Linux (Ubuntu/Debian)
```bash
sudo apt-get update
sudo apt-get install git python3 python3-pip openjdk-11-jdk
```

---

## 📥 Adım 1: Projeyi Klonla

```bash
git clone https://github.com/yahya5655lmaz-hue/ArduinoJson.git
cd ArduinoJson
```

---

## 🐍 Adım 2: Backend Kurulumu

### Windows
```bash
cd backend
python -m venv venv
venv\Scripts\activate
pip install -r requirements.txt
python app.py
```

### macOS/Linux
```bash
cd backend
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt
python3 app.py
```

**Başarılı ise göreceksiniz:**
```
🤖 Yahya-AI Sunucusu başlatılıyor...
 * Running on http://0.0.0.0:5000
 * Debug mode: on
```

---

## 📱 Adım 3: Android Ortamı Kurulumu

### 3.1 Android SDK Yükle

**Android Studio'da:**
1. Android Studio'yu aç
2. Tools → SDK Manager
3. SDK Platforms: Android 13 (API 33) seç
4. SDK Tools: 
   - Android SDK Platform-Tools
   - Android SDK Build-Tools
   - Gradle seç

### 3.2 Ortam Değişkenlerini Ayarla

**Windows:**
1. Sistem Özellikleri → Ortam Değişkenleri
2. Yeni Sistem Değişkeni:
   - **Ad**: `ANDROID_HOME`
   - **Değer**: `C:\Users\YourUsername\AppData\Local\Android\sdk`

**macOS/Linux:**
```bash
# ~/.bash_profile veya ~/.zshrc'ye ekle
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### 3.3 JAVA_HOME Ayarla

**Windows:**
```bash
set JAVA_HOME=C:\Program Files\Java\jdk-11.0.x
```

**macOS/Linux:**
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
```

---

## 🔨 Adım 4: APK Derleme

### Otomatik Derleme (Önerilen)

**Windows:**
```bash
build.bat
```

**macOS/Linux:**
```bash
chmod +x build.sh
./build.sh
```

### Manuel Derleme

```bash
cd android
./gradlew clean build  # macOS/Linux
gradlew.bat clean build  # Windows
```

**APK'nın konumu:**
```
android/app/build/outputs/apk/release/app-release.apk
```

---

## 📲 Adım 5: Cihaza Yükle

### USB Debugging'i Etkinleştir

1. Android telefonunun Ayarlarını aç
2. Geliştirici Seçenekleri'ni bul (Bunu bulmak için Sürüm Numarasına 7 kez tıkla)
3. USB Debugging'i etkinleştir
4. Telefonunu bilgisayara USB ile bağla

### APK'yı Yükle

```bash
# Cihazların listesini gör
adb devices

# APK'yı yükle
adb install -r android/app/build/outputs/apk/release/app-release.apk

# Uygulamayı başlat
adb shell am start -n com.yahya.ai/.MainActivity
```

---

## ✅ Test

### Backend Testi

```bash
# Terminal'de
python app.py

# Başka bir terminal'de
curl -X POST http://localhost:5000/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Merhaba"}'
```

**Beklenen Cevap:**
```json
{
  "user_message": "Merhaba",
  "bot_response": "Merhaba! Ben Yahya AI asistanınızım. Size nasıl yardımcı olabilirim?",
  "timestamp": "2026-05-11T10:30:00"
}
```

### Android Testi

1. Uygulamayı aç
2. Backend'in çalışıyor olduğundan emin ol
3. Bir mesaj yazıp gönder
4. Cevap alıp alamadığını kontrol et

---

## 🐛 Sorun Giderme

### Sorun: "Python bulunamadı"
```bash
# Python'un yolu doğru mu kontrol et
python --version
# veya
python3 --version
```

### Sorun: "ANDROID_HOME bulunamadı"
```bash
# Ortam değişkenini kontrol et
echo %ANDROID_HOME%  # Windows
echo $ANDROID_HOME   # macOS/Linux

# Yenisini ayarla
export ANDROID_HOME=/path/to/android/sdk
```

### Sorun: "Gradle bulunamadı"
```bash
cd android
gradle wrapper --gradle-version 7.6
./gradlew clean build
```

### Sorun: "APK yüklenemiyor"
```bash
# Cihazı resetle
adb kill-server
adb start-server

# USB Debugging'i tekrar kontrol et
adb devices
```

### Sorun: "Backend'e bağlanılamadı"
```bash
# Backend'in çalışıyor olduğundan emin ol
python app.py

# IP adresini kontrol et (192.168.x.x olmalı)
ipconfig  # Windows
ifconfig  # macOS/Linux
```

---

## 🚀 İleri Seçenekler

### Custom API URL

`android/app/src/main/java/com/yahya/ai/network/ApiClient.kt`'de değiştir:

```kotlin
companion object {
    private const val BASE_URL = "http://YOUR_IP:5000"
}
```

### Backend Portu Değiştir

`backend/app.py`'de değiştir:

```python
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)  # 5000'den 8000'e
```

### APK İmzalama

```bash
keytool -genkey -v -keystore yahya-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias yahya-key

# build.gradle'a ekle
signingConfigs {
    release {
        storeFile file("yahya-release-key.jks")
        storePassword 'sifre'
        keyAlias 'yahya-key'
        keyPassword 'sifre'
    }
}
```

---

## 📞 Yardıma İhtiyacınız mı?

- GitHub Issues: https://github.com/yahya5655lmaz-hue/ArduinoJson/issues
- E-mail: yahya5655lmaz@gmail.com

---

**Başarılar!** 🎉
