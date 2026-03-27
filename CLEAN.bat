@echo off
echo ============================================
echo     FOOD ORDERING SYSTEM - CLEANUP
echo ============================================
echo.
echo Removing all compiled class files...
echo.

if exist "models\*.class" (
    del /q models\*.class
    echo [OK] Cleaned models\*.class
)

if exist "utils\*.class" (
    del /q utils\*.class
    echo [OK] Cleaned utils\*.class
)

if exist "services\*.class" (
    del /q services\*.class
    echo [OK] Cleaned services\*.class
)

if exist "gui\*.class" (
    del /q gui\*.class
    echo [OK] Cleaned gui\*.class
)

if exist "*.class" (
    del /q *.class
    echo [OK] Cleaned root *.class
)

echo.
echo ============================================
echo CLEANUP COMPLETE!
echo ============================================
pause
