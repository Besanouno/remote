#!/opt/flask/bin/python

from flask import Flask
from flask import request

import subprocess


app = Flask(__name__)

@app.route('/remote', methods=['PUT'])
def index():
	volume = request.args.get('volume')
	bashCommand="amixer -D pulse sset Master " + volume + "%"
	process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
	output,error = process.communicate();
	return volume

if __name__ == '__main__':
	app.run(host="0.0.0.0")
