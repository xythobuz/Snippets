#!/bin/sh

lasttrack=`cat /Users/thomas/.lasttrack`
host=`hostname`
datum=`date`

if [ "$1" != "$lasttrack" ]; then
    if [ "$1" != "" ]; then
        echo "<html><head><meta charset=\"utf-8\" /><title>xythobuz listened to...</title></head><body><pre>" > /Users/thomas/.tmpfile
        echo "Last song played on <b>${host}</b>'s iTunes:" >> /Users/thomas/.tmpfile
        echo >> /Users/thomas/.tmpfile
        echo "  <b>$1</b>" >> /Users/thomas/.tmpfile
        echo "by" >> /Users/thomas/.tmpfile
        echo "  <b>$2</b>" >> /Users/thomas/.tmpfile
        echo "on the Album" >> /Users/thomas/.tmpfile
        echo "  <b>$3</b>" >> /Users/thomas/.tmpfile
        echo "at" >> /Users/thomas/.tmpfile
        echo "  <i>${datum}</i>" >> /Users/thomas/.tmpfile
        echo >> /Users/thomas/.tmpfile
        echo "Last songs played:" >> /Users/thomas/.tmpfile
        cat /Users/thomas/.trackhistory >> /Users/thomas/.tmpfile
        echo "</pre><p><a href="http://xythobuz.de">Return...</a></p></body></html>" >> /Users/thomas/.tmpfile

        scp /Users/thomas/.tmpfile zaphod:/var/www/lastmusic
        echo $1 > /Users/thomas/.lasttrack
        echo "<b>$1</b> by <b>$2</b> on <b>$3</b> at <i>${datum}</i>" >> /Users/thomas/.trackhistory
        tail -n 3 /Users/thomas/.trackhistory | tee /Users/thomas/.trackhistory
    fi
fi