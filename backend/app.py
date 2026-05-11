from flask import Flask, request, jsonify
from datetime import datetime
import json

app = Flask(__name__)

# Basit AI Chatbot
class YahyaAI:
    def __init__(self):
        self.responses = {
            "merhaba": "Merhaba! Ben Yahya AI asistanınızım. Size nasıl yardımcı olabilirim?",
            "nasılsın": "İyiyim, teşekkür ederim! Sen nasılsın?",
            "kim": "Ben Yahya, sizin kişisel AI asistanınızım.",
            "saat": f"Şu anki saat: {datetime.now().strftime('%H:%M:%S')}",
            "tarih": f"Bugünün tarihi: {datetime.now().strftime('%d.%m.%Y')}",
            "yardım": "Şu konularda yardım edebilirim: tarih, saat, sohbet, bilgi",
        }
    
    def get_response(self, user_message):
        message = user_message.lower().strip()
        
        for keyword, response in self.responses.items():
            if keyword in message:
                return response
        
        return f"'{user_message}' konusunda bilgi bulamadım. Lütfen başka bir şey sormayı deneyin."

# Global AI örneği
ai = YahyaAI()

@app.route('/', methods=['GET'])
def home():
    return jsonify({
        "status": "success",
        "message": "🤖 Yahya-AI Sunucusu Aktif",
        "version": "1.0.0",
        "endpoints": {
            "chat": "/api/chat (POST)",
            "health": "/api/health (GET)"
        }
    })

@app.route('/api/health', methods=['GET'])
def health():
    return jsonify({
        "status": "healthy",
        "timestamp": datetime.now().isoformat(),
        "service": "Yahya-AI"
    })

@app.route('/api/chat', methods=['POST'])
def chat():
    try:
        data = request.get_json()
        user_message = data.get('message', '')
        
        if not user_message:
            return jsonify({
                "error": "Mesaj boş olamaz"
            }), 400
        
        bot_response = ai.get_response(user_message)
        
        return jsonify({
            "user_message": user_message,
            "bot_response": bot_response,
            "timestamp": datetime.now().isoformat(),
            "status": "success"
        })
    
    except Exception as e:
        return jsonify({
            "error": str(e),
            "status": "error"
        }), 500

@app.errorhandler(404)
def not_found(error):
    return jsonify({
        "error": "Endpoint bulunamadı",
        "status": "error"
    }), 404

if __name__ == '__main__':
    print("\n" + "="*50)
    print("🤖 Yahya-AI Sunucusu Başlatılıyor...")
    print("="*50)
    print("📍 http://localhost:5000")
    print("📍 http://0.0.0.0:5000")
    print("\n💡 Ctrl+C ile durdurun\n")
    
    app.run(host='0.0.0.0', port=5000, debug=True)
