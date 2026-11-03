$ErrorActionPreference = 'Stop'
$settings = Get-Content -LiteralPath (Join-Path $PSScriptRoot 'settings.local.json') -Raw | ConvertFrom-Json
$baseUrl = 'http://127.0.0.1:' + $settings.serverTapPort
$headers = @{key=$settings.serverTapKey}
try {
    $null = Invoke-RestMethod -Uri "$baseUrl/v1/server" -Headers $headers -TimeoutSec 5
} catch {
    Write-Host 'Khong ket noi duoc may chu. May chu co the da tat.'
    exit 1
}
Write-Host 'Dang yeu cau luu the gioi va tat may chu...'
try {
    $null = Invoke-RestMethod -Method Post -Uri "$baseUrl/v1/server/exec" -Headers $headers -Body @{command='stop'} -TimeoutSec 5
} catch {
    # The HTTP listener can close before returning the stop response.
}
for ($i = 0; $i -lt 20; $i++) {
    Start-Sleep -Seconds 1
    $client = New-Object System.Net.Sockets.TcpClient
    try {
        $connection = $client.ConnectAsync('127.0.0.1', [int]$settings.serverPort)
        $connected = $connection.Wait(500) -and $client.Connected
    } catch { $connected = $false } finally { $client.Dispose() }
    if (-not $connected) {
        Write-Host 'Cong game da dong. Doi log bao All dimensions are saved truoc khi tat may.' -ForegroundColor Green
        exit 0
    }
}
Write-Host 'May chu van dang tat. Kiem tra server\logs\latest.log; khong tat cuong buc.' -ForegroundColor Yellow
exit 1
