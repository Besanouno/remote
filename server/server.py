from flask import Flask
from flask import request

import pyautogui
import subprocess
import re

app = Flask(__name__)


@app.route('/remote/audio/volume', methods=['PUT'])
def set_audio_volume():
    volume = request.args.get('volume')
    bash_command = "amixer -D pulse sset Master " + volume + "%"
    subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    return volume


@app.route('/remote/audio/volume', methods=['GET'])
def get_audio_volume():
    bash_command = "amixer -D pulse get Master"
    process = subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    amixer_stdout = process.communicate()[0].decode()
    volume = re.search('\[([0-9]*)%\]', amixer_stdout).group(1)
    return volume


@app.route('/remote/speakers/status', methods=['GET'])
def get_speakers_status():
    bash_command = "amixer -D pulse get Master"
    process = subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    amixer_stdout = process.communicate()[0].decode()
    status = re.search('\[(on|off)\]', amixer_stdout).group(1)
    return status


@app.route('/remote/speakers/status', methods=['PUT'])
def set_speakers_status():
    status = request.args.get('status')
    bash_command = "amixer -D pulse sset Master " + status
    subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    return status


@app.route('/remote/audio/volume/down', methods=['PUT'])
def volume_down():
    pyautogui.press('volumedown')
    return ""


@app.route('/remote/audio/volume/up', methods=['PUT'])
def volume_up():
    pyautogui.press('volumeup')
    return ""


@app.route('/remote/windows/switch', methods=['PUT'])
def switch_windows():
    if request.args.get('status') is 'on':
        pyautogui.keyDown('alt')
        pyautogui.press('tab')
    else:
        pyautogui.keyUp('alt')
    return ""


@app.route('/remote/video/playpause', methods=['PUT'])
def play_pause():
    pyautogui.press('space')
    return ""


if __name__ == '__main__':
    app.run(host="0.0.0.0")
