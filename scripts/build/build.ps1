$ErrorActionPreference = "Stop"

Remove-Item -Recurse -Force "build" -ErrorAction SilentlyContinue

New-Item -ItemType Directory -Force "build/classes" | Out-Null

$files = Get-ChildItem "src" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }

javac -d "build/classes" $files

jar cfe "cattype.jar" "Main" -C "build/classes" .

Remove-Item -Recurse -Force "build"

Write-Host "Built!"

Write-Host "Run with: java -jar cattype.jar"