#!/bin/bash
make
while IFS='' read -r line || [[ -n "$line" ]]; do
    urlPath=$(echo $line | cut -d'=' -f 2)
    urlPath="ids="$urlPath"&xhr=1"
    echo $urlPath
    curl --data $urlPath "https://play.google.com/store/xhr/getdoc?authuser=0" --silent > 0.txt
    java comparePermission 0.txt
    read -r line
    python androguard/binaryTraverse.py $line
done < "$1"