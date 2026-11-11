from http.server import BaseHTTPRequestHandler, HTTPServer
import json, random, time
class Handler(BaseHTTPRequestHandler):
  def reply(self, code, body):
    self.send_response(code); self.send_header('Content-Type','application/json'); self.end_headers(); self.wfile.write(json.dumps(body).encode())
  def do_GET(self): self.reply(200, {'service':'vectora-simulator','status':'ready','telemetry':'available','timestamp':time.time()})
  def do_POST(self):
    n=int(self.headers.get('Content-Length','0')); raw=self.rfile.read(n); self.reply(202, {'accepted':True,'eventId':f'SIM-{random.randint(1000,9999)}','payloadBytes':len(raw)})
HTTPServer(('0.0.0.0',8090), Handler).serve_forever()
