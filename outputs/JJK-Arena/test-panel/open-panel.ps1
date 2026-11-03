$ErrorActionPreference='Stop'
$url='http://127.0.0.1:21215'
$ready=$false
try {$ready=(Invoke-RestMethod ($url+'/health') -TimeoutSec 2).app -eq 'jjk-test-panel'} catch {}
if(!$ready){
    $settings=Get-Content -LiteralPath (Join-Path $PSScriptRoot '..\..\Zeefski-JJK-3.2.1\settings.local.json') -Raw | ConvertFrom-Json
    $process=Start-Process -FilePath $settings.nodePath -ArgumentList 'server.mjs' -WorkingDirectory $PSScriptRoot -WindowStyle Hidden -RedirectStandardOutput (Join-Path $PSScriptRoot 'console.log') -RedirectStandardError (Join-Path $PSScriptRoot 'errors.log') -PassThru
    $process.Id | Set-Content (Join-Path $PSScriptRoot 'process-id.txt')
    for($attempt=0;$attempt -lt 20;$attempt++){
        Start-Sleep -Milliseconds 250
        try {$ready=(Invoke-RestMethod ($url+'/health') -TimeoutSec 1).app -eq 'jjk-test-panel'} catch {}
        if($ready){break}
    }
}
if(!$ready){throw 'Khong mo duoc bang thu quan. Xem test-panel\errors.log.'}
Start-Process $url
