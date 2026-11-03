param([ValidateSet('start','pause','resume','reset','status','watch','leave','red','blue','redboss','blueboss','bridge')][string]$Action='status')
$ErrorActionPreference='Stop'
$settings=Get-Content -LiteralPath (Join-Path $PSScriptRoot '..\Zeefski-JJK-3.2.1\settings.local.json') -Raw | ConvertFrom-Json
if($Action -eq 'bridge') { & $settings.nodePath (Join-Path $PSScriptRoot 'interactions\bridge.mjs') }
else { & $settings.nodePath (Join-Path $PSScriptRoot 'control.mjs') $Action }
exit $LASTEXITCODE
