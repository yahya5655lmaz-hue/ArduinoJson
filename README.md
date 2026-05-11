# 🤖 Yahya-AI

**Akıllı AI Asistan Uygulaması - Android & Python**

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Python](https://img.shields.io/badge/Python-3.8+-yellow)
![Android](https://img.shields.io/badge/Android-API%2024+-orange)

---

## 📋 Özet

Yahya-AI, Python backend ve Android frontend ile oluşturulmuş bir akıllı asistan uygulamasıdır. Kullanıcılarla sohbet edebilen, soruları yanıtlayabilen ve harita arayüzüne sahip modern bir uygulamadır.

### 🎯 Özellikler

- 💬 **Akıllı Chatbot** - Doğal dil işleme ile sohbet
- 🎨 **3D Animasyonlu UI** - Modern ve etkileşimli arayüz
- 🔌 **REST API** - Backend ve Frontend arasında haberleşme
- 📱 **Native Android App** - Kotlin ile yazılmış
- 🐍 **Python Backend** - Flask tabanlı AI sunucusu
- 📦 **Tek Tıkla Derleme** - Otomatik APK oluşturma

---

## 🚀 Hızlı Başlangıç

### Gereksinimler
- Python 3.8+
- Android Studio
- Java JDK 11+
- Git

### 1️⃣ Projeyi Klonla

```bash
git clone https://github.com/yahya5655lmaz-hue/ArduinoJson.git
cd ArduinoJson
```

### 2️⃣ Backend'i Çalıştır

```bash
cd backend
pip install -r requirements.txt
python app.py
```

### 3️⃣ APK'yı Derle

```bash
# Linux/Mac
chmod +x build.sh
./build.sh

# Windows
build.bat
```

### 4️⃣ Cihaza Yükle

```bash
adb install android/app/build/outputs/apk/release/app-release.apk
```

---

## 📁 Proje Yapısı

```
ArduinoJson/
├── backend/
│   ├── app.py                 # Flask API sunucusu
│   ├── requirements.txt       # Python bağımlılıkları
│   └── venv/                  # Virtual environment
├── android/
│   ├── MainActivity.kt        # Ana Android aktivitesi
│   ├── ApiClient.kt          # API istemcisi
│   ├── activity_main.xml     # UI layout
│   ├── AndroidManifest.xml   # Manifest dosyası
│   ├── build.gradle          # Gradle ayarları
│   └── app/
│       └── build/
│           └── outputs/
│               └── apk/      # Derlenmiş APK dosyaları
├── build.sh                  # Linux/Mac derleme script'i
├── build.bat                 # Windows derleme script'i
├── README.md                 # Bu dosya
└── KURULUM.md                # Detaylı kurulum rehberi
```

---

## 🔧 Konfigürasyon

### API URL Değiştirme

`android/ApiClient.kt` dosyasında:

```kotlin
private val baseUrl = "http://192.168.1.100:5000"  // IP'nizi yazın
```

### Backend Portu Değiştirme

`backend/app.py` dosyasında:

```python
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)  # Portu değiştir
```

---

## 📡 API Endpoints

### Health Check
```http
GET /api/health
```

**Response:**
```json
{
  "status": "healthy",
  "timestamp": "2026-05-11T10:30:00",
  "service": "Yahya-AI"
}
```

### Chat
```http
POST /api/chat
Content-Type: application/json

{
  "message": "Merhaba"
}
```

**Response:**
```json
{
  "user_message": "Merhaba",
  "bot_response": "Merhaba! Ben Yahya AI asistanınızım. Size nasıl yardımcı olabilirim?",
  "timestamp": "2026-05-11T10:30:00",
  "status": "success"
}
```

---

## 🐛 Sorun Giderme

### Bağlantı Sorunu
1. Backend'in çalışıyor olduğundan emin olun
2. Firewall'un 5000 portunu açık tuttuğunu kontrol edin
3. IP adresini doğru şekilde ayarlayın

### Derleme Hatası
1. Android SDK'nın yüklü olduğunu kontrol edin
2. `ANDROID_HOME` ortam değişkenini ayarlayın
3. Gradle sürümünü güncelleyin

### Runtime Hatası
1. USB Debugging'i etkinleştirin
2. Cihazda gerekli izinleri onaylayın
3. Loglar için `adb logcat` kullanın

---

## 📞 İletişim

- **Email**: yahya5655lmaz@gmail.com
- **GitHub**: https://github.com/yahya5655lmaz-hue
- **Issues**: https://github.com/yahya5655lmaz-hue/ArduinoJson/issues

---

## 📝 Lisans

MIT License - Detaylar için [LICENSE](LICENSE) dosyasını kontrol edin.

---

## 🙏 Katkılar

Katkılar çok değerlidir! Lütfen bir issue açın veya pull request gönderin.

---

**Made with ❤️ by Yahya**
