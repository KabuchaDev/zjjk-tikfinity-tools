$ErrorActionPreference = 'Stop'
$settings = Get-Content -LiteralPath (Join-Path $PSScriptRoot 'settings.local.json') -Raw | ConvertFrom-Json
& $settings.nodePath (Join-Path $PSScriptRoot 'rcon.mjs') stop
if ($LASTEXITCODE -ne 0) { throw 'Khong gui duoc lenh stop. Neu server dang chay, go stop vao cua so server.' }
Write-Host 'Da gui lenh luu va tat. Dang cho server dong...'
for ($i=0; $i -lt 60; $i++) {
    Start-Sleep -Seconds 1
    $probe = New-Object System.Net.Sockets.TcpClient
    try { $probe.Connect('127.0.0.1', [int]$settings.serverPort) }
    catch { Write-Host 'Server da dong cong. Cho cua so server bao luu xong truoc khi tat may.'; exit 0 }
    finally { $probe.Dispose() }
}
Write-Warning 'Server van dang dung. Xem server\logs\latest.log; khong tat cuong buc.'
