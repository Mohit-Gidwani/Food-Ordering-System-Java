@echo off
chcp 65001 >nul
echo ============================================
echo     FOOD ORDERING SYSTEM
echo ============================================
echo.

if not exist "FoodOrderingSystem.class" (
    echo ERROR: Application not compiled!
    echo Please run COMPILE.bat first.
    echo.
    pause
    exit /b 1
)

echo Starting Food Ordering System...
echo.
echo Default Login Credentials:
echo   Admin: admin / admin123
echo   User:  user / user123
echo.
echo ============================================
echo.

java FoodOrderingSystem

if %errorlevel% neq 0 (
    echo.
    echo Application closed with errors.
    pause
)
