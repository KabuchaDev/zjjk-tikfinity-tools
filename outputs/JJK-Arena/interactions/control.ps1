param([ValidateSet('status','reset-counters','stop')][string]$Action='status')
$ErrorActionPreference='Stop'
$settings=Get-Content -LiteralPath (Join-Path $PSScriptRoot '..\..\Zeefski-JJK-3.2.1\settings.local.json') -Raw | ConvertFrom-Json
& $settings.nodePath (Join-Path $PSScriptRoot 'control.mjs') $Action
exit $LASTEXITCODE
