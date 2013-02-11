#!/bin/sh

ipfile="ipnotify.tmp"
service="ifconfig.me"
user=""
pass=""
message="Heart-of-Gold:"

ip=`curl -s $service`
touch $ipfile
lastip=`cat $ipfile`

if [ "$ip" != "$lastip" ]; then
    if [ "$ip" != "" ]; then
        echo "New public IP. Sending notification!"
        curl -s "https://www.ultimatenotifier.com/items/User/send/${user}/message=${message}%20${ip}/password=${pass}"
        cp /dev/null $ipfile
        echo $ip > $ipfile
    fi
fi
