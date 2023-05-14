call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Compile error
goto fail

:runbrowser
start chrome http://localhost:8080/crud/v1/tasks
goto end

:fail
echo.
echo Breaking work

:end
echo.
echo Work is finished.