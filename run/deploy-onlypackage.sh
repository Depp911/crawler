#!/usr/bin/env bash
#该脚本接受参数为部署使用的environment，若不传参数则使用如下默认值
environment="develop"

if [ x$1 != x ];then
    environment=$1
fi

rootDir=$(cd "$(dirname "$0")"/; pwd)/../
echo "project dir: "${rootDir}
echo "packaging ..."
chmod 755 ${rootDir}/run/*
mvn clean package -P${environment}

if [ $? -ne 0 ];then
    echo "package fail!"
    exit 1
fi

echo "package success!"
