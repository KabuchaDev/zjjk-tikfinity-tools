$ErrorActionPreference = 'Stop'
$settings = Get-Content -LiteralPath (Join-Path $PSScriptRoot 'settings.local.json') -Raw | ConvertFrom-Json
$serverDir = Join-Path $PSScriptRoot 'server'
if (-not (Test-Path -LiteralPath $settings.javaPath)) { throw 'Khong tim thay Java 17.' }
$probe = New-Object System.Net.Sockets.TcpClient
try {
    $attempt = $probe.ConnectAsync('127.0.0.1', [int]$settings.serverPort)
    if ($attempt.Wait(1000) -and $probe.Connected) { throw 'Server da chay hoac cong dang duoc su dung.' }
} catch [System.AggregateException] {
    # Connection refused means the port is available.
} finally { $probe.Dispose() }
Write-Host 'Zeefski Jujutsu Kaisen Craft 3.2.1 - Forge 1.20.1' -ForegroundColor Cyan
Write-Host 'Cho den khi thay Done. Vao game voi modpack 3.2.1, dia chi 127.0.0.1:25566.'
Write-Host 'De luu va tat an toan: go stop roi Enter. Khong bam X khi server dang chay.'
Push-Location -LiteralPath $serverDir
try {
    & $settings.javaPath '@user_jvm_args.txt' '@libraries/net/minecraftforge/forge/1.20.1-47.4.3/win_args.txt' nogui
    if ($LASTEXITCODE -ne 0) { throw "Server dung voi ma $LASTEXITCODE. Xem server\logs\latest.log." }
} finally { Pop-Location }
