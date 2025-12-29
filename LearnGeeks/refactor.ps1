$ErrorActionPreference = "Stop"
$root = "C:\Users\HP\Backend-Services\LearnGeeks\src\main\java\com\example\demo"

Write-Host "Starting Refactor in $root"

# 1. Identify Target Directories (Case-sensitive equivalent check via match)
# We look for any directory containing uppercase characters
$targetDirs = Get-ChildItem -Path $root -Recurse -Directory | Where-Object { $_.Name -cmatch '[A-Z]' } | Sort-Object -Property FullName -Descending

$targetNames = $targetDirs.Name | Select-Object -Unique
Write-Host "Found $($targetDirs.Count) directories to rename."
# Write-Host "Targets: $($targetNames -join ', ')"

# 2. Identify Class Names to avoid false positives in imports
$classFiles = Get-ChildItem -Path $root -Recurse -Filter *.java
$classNames = $classFiles | ForEach-Object { $_.BaseName } | Select-Object -Unique
$classNamesSet = [System.Collections.Generic.HashSet[string]]::new([string[]]$classNames)
$targetNamesSet = [System.Collections.Generic.HashSet[string]]::new([string[]]$targetNames)

# 3. Rename Directories
foreach ($dir in $targetDirs) {
    $parent = $dir.Parent.FullName
    $name = $dir.Name
    $lowerName = $name.ToLower()
    $tempName = "${name}_temp_refactor"
    
    if ($name -ceq $lowerName) { continue }
    
    Write-Host "Renaming Folder: $name -> $lowerName"
    
    $path = $dir.FullName
    $tempPath = Join-Path $parent $tempName
    $finalPath = Join-Path $parent $lowerName
    
    Move-Item -LiteralPath $path -Destination $tempPath -Force
    Move-Item -LiteralPath $tempPath -Destination $finalPath -Force
}

# 4. Update File Content
$filesToUpdate = Get-ChildItem -Path $root -Recurse -Filter *.java
# We also might need to update files that were IN the renamed directories, their paths changed? 
# Get-ChildItem -Recurse grabs them dynamically? 
# No, if we grabbed the list before renaming, the paths are stale.
# So we must grab the list AGAIN.
$filesToUpdate = Get-ChildItem -Path $root -Recurse -Filter *.java

foreach ($file in $filesToUpdate) {
    $content = Get-Content -LiteralPath $file.FullName -Raw
    $originalContent = $content
    
    # Regex to find package and import statements
    # We look for "package com.example.demo..." or "import com.example.demo..."
    $content = [Regex]::Replace($content, '((?:package|import)\s+static\s+|(?:\s*package|import)\s+)(com\.example\.demo[\w\.]*)', { 
        param($match)
        $prefix = $match.Groups[1].Value
        $pathStr = $match.Groups[2].Value
        
        $segments = $pathStr.Split('.')
        $newSegments = @()
        
        for ($i = 0; $i -lt $segments.Count; $i++) {
            $seg = $segments[$i]
            
            # Logic:
            # If segment is in targetNamesSet -> valid candidate for lowercasing.
            # But we must preserve Class Names.
            # If it's the LAST segment, check if it's a known class.
            
            $isLast = ($i -eq ($segments.Count - 1))
            $isTarget = $targetNamesSet.Contains($seg)
            $isClass = $classNamesSet.Contains($seg)
            
            if ($isTarget) {
                if ($isLast -and $isClass -and -not ($prefix.Trim().StartsWith("package"))) {
                    # It matches a directory name BUT it is also a class name, AND it is the last segment of an import.
                    # Assume it is the Class. Keep it.
                    # Exception: If package declaration, always lowercase (package names cannot be classes).
                    $newSegments += $seg
                }
                else {
                    # Rename to lower
                    $newSegments += $seg.ToLower()
                }
            }
            else {
                # Not a target dir, keep as is
                $newSegments += $seg
            }
        }
        
        return $prefix + ($newSegments -join '.')
    })
    
    if ($content -cne $originalContent) {
        Write-Host "Updating File: $($file.Name)"
        Set-Content -LiteralPath $file.FullName -Value $content -NoNewline -Encoding UTF8
    }
}

Write-Host "Refactor Complete."
