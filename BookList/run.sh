#!/bin/bash

mvn clean install

docker build -t booklist .

docker run -it -p 8080:8080 booklist
