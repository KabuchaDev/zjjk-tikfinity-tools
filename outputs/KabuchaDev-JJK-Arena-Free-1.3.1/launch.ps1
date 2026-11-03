param([ValidateSet('server','bridge','panel','stop','bridge-stop','status','test')][string]$Mode='server')
$ErrorActionPreference='Stop'
Set-Location -LiteralPath $PSScriptRoot
try {
    $settingsFile=Join-Path $PSScriptRoot 'settings.local.json'
    if (!(Test-Path -LiteralPath $settingsFile)) { throw 'Run SETUP.cmd first.' }
    $cfg=Get-Content -LiteralPath $settingsFile -Raw | ConvertFrom-Json
    switch ($Mode) {
        'server' { & $cfg.nodePath (Join-Path $PSScriptRoot 'start-server.mjs') }
        'bridge' { & $cfg.nodePath (Join-Path $PSScriptRoot 'interactions\bridge.mjs') }
        'panel' { Write-Host 'Open http://127.0.0.1:21215/ in your browser.'; & $cfg.nodePath (Join-Path $PSScriptRoot 'test-panel\server.mjs') }
        'stop' {
            & $cfg.nodePath (Join-Path $PSScriptRoot 'interactions\control.mjs') stop
            & $cfg.nodePath (Join-Path $PSScriptRoot 'rcon.mjs') stop
        }
        'status' { & $cfg.nodePath (Join-Path $PSScriptRoot 'interactions\control.mjs') status }
        'bridge-stop' { & $cfg.nodePath (Join-Path $PSScriptRoot 'interactions\control.mjs') stop }
        'test' {
            & $cfg.nodePath (Join-Path $PSScriptRoot 'interactions\test-router.mjs')
            if ($LASTEXITCODE -ne 0) { throw 'Router test failed.' }
            & $cfg.nodePath (Join-Path $PSScriptRoot 'interactions\test-audience.mjs')
        }
    }
    if ($LASTEXITCODE -ne 0) { throw "Command failed: $LASTEXITCODE" }
} catch { Write-Host $_ -ForegroundColor Red; exit 1 }
