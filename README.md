* `cd docker-images/OracleDatabase/SingleInstance/dockerfiles` 
* `./buildContainerImage.sh -v 18.4.0  -t oracle_xe:18.4.0 -x `
* ```
  mkdir $(pwd)/oradata
  sudo chown 54321  $(pwd)/oradata
  mkdir $(pwd)/ora_startup```
* ```
  docker run --name oracle_xe  -p 1521:1521 -p 5500:5500 -v $(pwd)/ora_startup:/opt/oracle/scripts/startup  -v $(pwd)/oradata:/opt/oracle/oradata -e ORACLE_PWD=password oracle_xe:18.4.0
  docker logs -f oracle_xe
  ```
*   
    

