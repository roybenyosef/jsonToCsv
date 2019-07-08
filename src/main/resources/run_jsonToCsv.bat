@echo off
set JSON_FILE=%1
set OUTPUT_FILES=%2
bin\java.exe -jar JsonToCsv.main.jar %JSON_FILE% %OUTPUT_FILES%