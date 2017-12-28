from flask import Flask
from flask import request

import subprocess

app = Flask(__name__)


@app.route('/remote', methods=['PUT'])
def set_audio_volume():
    volume = request.args.get('volume')
    bash_command = "amixer -D pulse sset Master " + volume + "%"
    process = subprocess.Popen(bash_command.split())
    output,error = process.communicate()
    return volume


if __name__ == '__main__':
    app.run(host="0.0.0.0")