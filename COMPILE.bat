@echo off
chcp 65001 >nul
echo ============================================
echo     FOOD ORDERING SYSTEM - COMPILER
echo ============================================
echo.

if not exist "models" (
    echo ERROR: models folder not found!
    echo Please run this batch file from the project root folder.
    pause
    exit /b 1
)

echo Cleaning old class files...
if exist "models\*.class" del /q models\*.class
if exist "utils\*.class" del /q utils\*.class
if exist "services\*.class" del /q services\*.class
if exist "gui\*.class" del /q gui\*.class
if exist "*.class" del /q *.class
echo Done cleaning.
echo.

echo Compiling Java files...
javac -encoding UTF-8 -d . models\*.java utils\*.java services\*.java gui\*.java FoodOrderingSystem.java

if %errorlevel% neq 0 (
    echo.
    echo ============================================
    echo COMPILATION FAILED!
    echo ============================================
    pause
    exit /b 1
)

echo.
echo ============================================
echo COMPILATION SUCCESSFUL!
echo ============================================
echo.
echo You can now run the application using run.bat
echo.
pause
