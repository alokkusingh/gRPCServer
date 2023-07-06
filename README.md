## gRPC Server


## Generate Stub 
Generate Stub from protobuf using `protoc-jar-maven-plugin` Maven plugin

```shell
mvn clean generate-sources
```

## Access gRPC API
You may use BloomGRPC toll to invoke the gRPC APPIs...
```shell
brew install --cask bloomrpc
```
Give App permission on Mac
```shell
sudo xattr -rd com.apple.quarantine /Applications/BloomRPC.app
```

## Putting behind Nginx
### Compatible version
NGINX Open Source 1.13.10 or higher
### Required Module
--with-http_v2_module
```shell
nginx -V
```
### Nginx Config
````
    server {
        listen  8088 http2;

        access_log  /Users/aloksingh/logs/nginx-access.log  main;
        error_log  /Users/aloksingh/logs/nginx-error.log  warn;

        location / {
            grpc_pass grpc://localhost:9090;
        }

    }
````

