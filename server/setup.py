from os import environ
from cx_Freeze import setup, Executable

environ['TCL_LIBRARY'] = r'C:\Users\Marcin\AppData\Local\Programs\Python\Python36-32\tcl\tcl8.6'
environ['TK_LIBRARY'] = r'C:\Users\Marcin\AppData\Local\Programs\Python\Python36-32\tcl\tk8.6'

setup(
    name="Remote",
    version="1.1",
    options={
      'build.exe': {
          'packages': ['encodings', 'asyncio']
      }
    },
    description="Pilot do zdalnego sterowania komputerem",
    executables=[Executable("D:\\Projekty\\remote\\server\\server.py", base="Win32GUI")]
)
