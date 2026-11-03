$ErrorActionPreference = 'Stop'
$settings = Get-Content -LiteralPath (Join-Path $PSScriptRoot 'settings.local.json') -Raw | ConvertFrom-Json
$serverDir = Join-Path $PSScriptRoot 'server'
$eulaPath = Join-Path $serverDir 'eula.txt'
if (-not (Test-Path -LiteralPath $settings.javaPath)) {
    throw 'Khong tim thay Java 21. Kiem tra javaPath trong settings.local.json.'
}
if ((Get-Content -LiteralPath $eulaPath -Raw) -notmatch '(?m)^eula=true\s*$') {
    Write-Host 'Minecraft yeu cau ban dong y EULA truoc khi chay may chu.' -ForegroundColor Yellow
    Write-Host 'Doc dieu khoan tai: https://www.minecraft.net/eula'
    $answer = Read-Host 'Neu dong y, nhap DONG Y. Nhap gia tri khac de huy'
    if ($answer.Trim() -cne 'DONG Y') { exit 0 }
    Set-Content -LiteralPath $eulaPath -Value "# Accepted by user at startup`r`neula=true" -Encoding ASCII
}
$probe = New-Object System.Net.Sockets.TcpClient
try {
    $attempt = $probe.ConnectAsync($settings.serverAddress, [int]$settings.serverPort)
    if ($attempt.Wait(1000) -and $probe.Connected) {
        throw 'Cong 25565 da duoc su dung. May chu co the dang chay; khong mo hai ban cung luc.'
    }
} catch [System.AggregateException] {
    # Connection refused means the port is available.
} finally {
    $probe.Dispose()
}
Write-Host 'Cho den khi thay Done. Trong Minecraft 1.21.4, ket noi 127.0.0.1.' -ForegroundColor Cyan
Write-Host 'De tat may chu va luu the gioi: nhap stop roi Enter vao cua so nay.'
Push-Location -LiteralPath $serverDir
try {
    & $settings.javaPath -Xms512M -Xmx2G '-Dterminal.jline=false' '-Dterminal.ansi=false' -jar paper.jar --nogui
    if ($LASTEXITCODE -ne 0) { throw "May chu dung voi ma $LASTEXITCODE. Xem server\logs\latest.log." }
} finally {
    Pop-Location
}
