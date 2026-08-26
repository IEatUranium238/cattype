$ErrorActionPreference = "Stop"

Remove-Item -Recurse -Force "build", "cattype" -ErrorAction SilentlyContinue

New-Item -ItemType Directory -Force "build/classes" | Out-Null

$sourceFiles = Get-ChildItem -Path "src" -Filter "*.java" -Recurse |
    ForEach-Object { $_.FullName }

javac -d "build/classes" $sourceFiles

native-image `
    --no-fallback `
    -O3 `
    --gc=serial `
    --enable-url-protocols=http,https `
    -cp "build/classes" `
    Main `
    cattype

Remove-Item -Recurse -Force "build"

Write-Host "Built!"
Write-Host "Run with: ./cattype"