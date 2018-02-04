#!/usr/bin/env python

import re
import subprocess
import pyautogui

from flask import Flask
from flask import request

app = Flask(__name__)


@app.route('/remote/unix/audio/volume', methods=['PUT'])
def set_audio_volume():
    volume = request.args.get('volume')
    bash_command = "amixer -D pulse sset Master " + volume + "%"
    subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    return volume


@app.route('/remote/unix/audio/volume', methods=['GET'])
def get_audio_volume():
    bash_command = "amixer -D pulse get Master"
    process = subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    amixer_stdout = process.communicate()[0].decode()
    volume = re.search('\[([0-9]*)%\]', amixer_stdout).group(1)
    return volume


@app.route('/remote/unix/speakers/status', methods=['GET'])
def get_speakers_status():
    bash_command = "amixer -D pulse get Master"
    process = subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    amixer_stdout = process.communicate()[0].decode()
    status = re.search('\[(on|off)\]', amixer_stdout).group(1)
    return status


@app.route('/remote/unix/speakers/status', methods=['PUT'])
def set_speakers_status():
    status = request.args.get('status')
    bash_command = "amixer -D pulse sset Master " + status
    subprocess.Popen(bash_command.split(), stdout=subprocess.PIPE)
    return status


@app.route('/remote/win/audio/volume/down', methods=['PUT'])
def volume_down():
    pyautogui.press('volumedown')
    return ""


@app.route('/remote/win/audio/volume/up', methods=['PUT'])
def volume_up():
    pyautogui.press('volumeup')
    return ""


@app.route('/remote/audio/next', methods=['PUT'])
def audio_next():
    pyautogui.keyDown('ctrl')
    pyautogui.press('right')
    pyautogui.keyUp('ctrl')
    return ""


@app.route('/remote/audio/prev', methods=['PUT'])
def audio_prev():
    pyautogui.keyDown('ctrl')
    pyautogui.press('left')
    pyautogui.keyUp('ctrl')
    return ""


@app.route('/remote/windows/switch', methods=['PUT'])
def switch_windows():
    status = request.args.get('status')
    if status == 'on':
        pyautogui.keyDown('alt')
        pyautogui.press('tab')
    elif status == 'off':
        pyautogui.keyUp('alt')
    return ""


@app.route('/remote/tabs/switch', methods=['PUT'])
def switch_tab():
    pyautogui.keyDown('ctrl')
    pyautogui.press('tab')
    pyautogui.keyUp('ctrl')
    return ""


@app.route('/remote/move/right', methods=['PUT'])
def right():
    pyautogui.press('right')
    return ""


@app.route('/remote/move/left', methods=['PUT'])
def left():
    pyautogui.press('left')
    return ""


@app.route('/remote/move/up', methods=['PUT'])
def up():
    pyautogui.press('up')
    return ""


@app.route('/remote/move/down', methods=['PUT'])
def down():
    pyautogui.press('down')
    return ""


@app.route('/remote/video', methods=['PUT'])
def play_pause():
    pyautogui.press('space')
    return ""


if __name__ == '__main__':
    app.run(host="0.0.0.0")
