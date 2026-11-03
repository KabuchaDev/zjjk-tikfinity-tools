@echo off
cd /d "%~dp0"
powershell.exe -NoProfile -ExecutionPolicy Bypass -File "%~dp0..\Zeefski-JJK-3.2.1\stop-server.ps1"
pause
