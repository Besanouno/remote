from flask import Flask
from flask import request

import subprocess
import re

app = Flask(__name__)


@app.route('/remote', methods=['PUT'])
def set_audio_volume():
    volume = request.args.get('volume')
    bash_command = "amixer -D pulse sset Master " + volume + "%"
    subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    return volume


@app.route('/remote', methods=['GET'])
def get_audio_volume():
    bash_command = "amixer -D pulse get Master"
    process = subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    amixer_stdout = process.communicate()[0]
    volume = re.search('\[([0-9]*)%\]', amixer_stdout).group(1)
    return volume


@app.route('/remote/speakers/status', methods=['GET'])
def get_speakers_status():
    bash_command = "amixer -D pulse get Master"
    process = subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    amixer_stdout = process.communicate()[0]
    status = re.search('\[(on|off)\]', amixer_stdout).group(1)
    return status


@app.route('/remote/speakers/status', methods=['PUT'])
def set_speakers_status():
    status = request.args.get('status')
    bash_command = "amixer -D pulse sset Master " + status
    subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    return status


if __name__ == '__main__':
    app.run(host="0.0.0.0")