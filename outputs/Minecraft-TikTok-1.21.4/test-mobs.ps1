param([ValidateSet('menu','status','zombie','skeleton','creeper','spider','cleanup')][string]$Action = 'menu')
$ErrorActionPreference = 'Stop'
$settings = Get-Content -LiteralPath (Join-Path $PSScriptRoot 'settings.local.json') -Raw | ConvertFrom-Json
$baseUrl = 'http://127.0.0.1:' + $settings.serverTapPort
$headers = @{key=$settings.serverTapKey}
try {
    $serverInfo = Invoke-RestMethod -Uri "$baseUrl/v1/server" -Headers $headers -TimeoutSec 8
    Write-Host ('Da ket noi: ' + $serverInfo.version) -ForegroundColor Green
    if ($Action -eq 'status') { exit 0 }
    $players = @(Invoke-RestMethod -Uri "$baseUrl/v1/players" -Headers $headers -TimeoutSec 8)
    if ($players.Count -eq 0) { throw 'Hay vao Minecraft 1.21.4 > Multiplayer > Direct Connection > 127.0.0.1 truoc.' }
    if ($players.Count -gt 1) { throw 'Bo thu nay chi danh cho may chu 1 nguoi choi.' }
    do {
        $choice = $Action
        if ($Action -eq 'menu') {
            Write-Host ''
            Write-Host '1: Zombie | 2: Bo xuong | 3: Creeper | 4: Nhen | 5: Don quai TikTok | 0: Thoat'
            $selection = Read-Host 'Chon'
            $choice = switch ($selection) {
                '1' { 'zombie' }
                '2' { 'skeleton' }
                '3' { 'creeper' }
                '4' { 'spider' }
                '5' { 'cleanup' }
                '0' { 'exit' }
                default { 'invalid' }
            }
        }
        if ($choice -eq 'exit') { break }
        if ($choice -eq 'invalid') { continue }
        $command = if ($choice -eq 'cleanup') {
            'execute at @a[limit=1] run kill @e[tag=tiktok_mob]'
        } else {
            'execute at @a[limit=1] run summon minecraft:' + $choice + ' ~3 ~1 ~ {Tags:["tiktok_mob"]}'
        }
        $result = Invoke-RestMethod -Method Post -Uri "$baseUrl/v1/server/exec" -Headers $headers -Body @{command=$command} -TimeoutSec 8
        $result | ConvertTo-Json -Depth 5
    } while ($Action -eq 'menu')
} catch {
    Write-Host $_.Exception.Message -ForegroundColor Red
    Write-Host 'Kiem tra BAT-MAY-CHU.cmd da chay den Done va ServerTap da duoc nap.'
    exit 1
}
