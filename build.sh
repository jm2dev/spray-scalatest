#!/bin/sh

if [ -d tools ]
then
    echo 'tools directory exists'
else
    mkdir tools
    cd tools
    echo 'downloading sbt'
    wget http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch//0.12.4/sbt-launch.jar
    cd ..
    echo 'java -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar `dirname $0`/tools/sbt-launch.jar "$@"' >> sbt
    chmod +x sbt
    echo 'sbt installed'
fi

./sbt
