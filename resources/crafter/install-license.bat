@echo off
REM Script to install a license file.

SET CRAFTER_HOME=%~dp0

REM Execute Groovy script
%CRAFTER_HOME%\groovy\bin\groovy -cp %CRAFTER_HOME% -Dgrape.root=%CRAFTER_HOME% %CRAFTER_HOME%\install-license.groovy %*
