#!/bin/sh
#	***************************************************
#		^> File Name: server.sh
#		^> Author: AoEiuV020
#		^> Mail: 490674483@qq.com
#		^> Created Time: 2016/03/01 - 11:55:13
#	***************************************************
cd $(dirname $0)
java "-Dfile.encoding=utf8" "-Duser.country=US" chatroom.server.Server
