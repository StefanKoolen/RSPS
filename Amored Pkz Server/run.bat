@echo off
color 0b
title Running ArmoredPkz
"A:\Program Files\Java\jre7\bin\java.exe" -Xmx1000m -cp bin;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/RuneTopListV2.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
pause