$ErrorActionPreference='Stop'
$mutex=[Threading.Mutex]::new($false,'Local\JJK-Arena-Server-25566')
$held=$false
try {
    $held=$mutex.WaitOne(0)
    if(!$held){Write-Host 'Cua so khoi dong khac dang chay. Hay doi cua so do hoan tat.';exit 0}
    $settings=Get-Content -LiteralPath (Join-Path $PSScriptRoot '..\Zeefski-JJK-3.2.1\settings.local.json') -Raw | ConvertFrom-Json
    $serverDir=[IO.Path]::GetFullPath((Join-Path $PSScriptRoot '..\Zeefski-JJK-3.2.1\server'))
    function Test-LocalPort([int]$Port){
        $client=[Net.Sockets.TcpClient]::new()
        try {$task=$client.ConnectAsync('127.0.0.1',$Port);return ($task.Wait(500) -and $client.Connected)}
        catch {return $false}
        finally {$client.Dispose()}
    }
    $alreadyListening=Test-LocalPort ([int]$settings.serverPort)
    $worldBusy=$false
    $lockPath=Join-Path $serverDir 'world-jujutsu\session.lock'
    if(Test-Path -LiteralPath $lockPath){
        try {$probe=[IO.File]::Open($lockPath,'Open','ReadWrite','None');$probe.Dispose()}
        catch {$worldBusy=$true}
    }
    if(!$alreadyListening -and !$worldBusy){
        if(!(Test-Path -LiteralPath $settings.javaPath)){throw 'Khong tim thay Java cua server.'}
        Write-Host 'Dang khoi dong server JJK. Thuong can 30-90 giay...' -ForegroundColor Cyan
        $process=Start-Process -FilePath $settings.javaPath -ArgumentList '@user_jvm_args.txt','@libraries/net/minecraftforge/forge/1.20.1-47.4.3/win_args.txt','nogui' -WorkingDirectory $serverDir -WindowStyle Hidden -RedirectStandardOutput (Join-Path $serverDir 'console-output.log') -RedirectStandardError (Join-Path $serverDir 'console-errors.log') -PassThru
        $process.Id | Set-Content (Join-Path $serverDir 'test-process-id.txt')
    }else {Write-Host 'Server dang chay hoac dang nap the gioi. Dang kiem tra...'}
    $ready=$false
    for($attempt=0;$attempt -lt 90;$attempt++){
        if(Test-LocalPort ([int]$settings.rconPort)){
            & $settings.nodePath (Join-Path $PSScriptRoot 'start-arena.mjs') check | Out-Null
            if($LASTEXITCODE -eq 0){$ready=$true;break}
        }
        if($process -and $process.HasExited){throw 'Server dung khi khoi dong. Xem server\logs\latest.log.'}
        Start-Sleep -Seconds 2
    }
    if(!$ready){throw 'Server chua san sang sau 3 phut. Xem server\logs\latest.log; dung mo them server.'}
    & $settings.nodePath (Join-Path $PSScriptRoot 'start-arena.mjs') ensure
    if($LASTEXITCODE -ne 0){throw 'Server da mo nhung chua mo duoc san dau.'}
    Write-Host 'De tat va luu: mo TAT-SERVER.cmd. De thu quan: mo BANG-THU-NHAN-VAT.cmd.' -ForegroundColor Green
} finally {if($held){$mutex.ReleaseMutex()};$mutex.Dispose()}
