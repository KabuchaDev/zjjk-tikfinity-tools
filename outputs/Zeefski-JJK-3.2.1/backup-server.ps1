$ErrorActionPreference = 'Stop'
$serverDir = Join-Path $PSScriptRoot 'server'
$lock = $null
try {
    # Minecraft holds this lock for the complete lifetime of the open world.
    $lockPath = Join-Path $serverDir 'world-jujutsu\session.lock'
    if (Test-Path -LiteralPath $lockPath) {
        try { $lock = [IO.File]::Open($lockPath, 'Open', 'ReadWrite', 'None') }
        catch { throw 'Hay tat server bang TAT-SERVER va cho luu xong truoc khi sao luu.' }
    }
    $backupDir = Join-Path $PSScriptRoot 'backups'
    New-Item -ItemType Directory -Force -Path $backupDir | Out-Null
    $dest = Join-Path $backupDir ('JJK-' + (Get-Date -Format 'yyyy-MM-dd-HHmmss') + '.zip')
    Add-Type -AssemblyName System.IO.Compression.FileSystem
    $archive = [IO.Compression.ZipFile]::Open($dest, 'Create')
    try {
        $files = @(Get-ChildItem -LiteralPath (Join-Path $serverDir 'world-jujutsu') -File -Recurse)
        $files += @(Get-ChildItem -LiteralPath (Join-Path $serverDir 'config'), (Join-Path $serverDir 'defaultconfigs') -File -Recurse)
        $files += @(Get-ChildItem -LiteralPath $serverDir -File | Where-Object Name -in 'server.properties','ops.json','whitelist.json','banned-players.json','banned-ips.json')
        foreach ($file in $files) {
            if ($file.Name -eq 'session.lock') { continue }
            $entry = $file.FullName.Substring($serverDir.Length + 1).Replace('\', '/')
            [IO.Compression.ZipFileExtensions]::CreateEntryFromFile($archive, $file.FullName, $entry, 'Optimal') | Out-Null
        }
    } finally { $archive.Dispose() }
    Write-Host "Da sao luu the gioi va cau hinh: $dest" -ForegroundColor Green
} finally { if ($lock) { $lock.Dispose() } }
