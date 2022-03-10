$p=Split-Path (Split-Path $PSScriptRoot -Parent) -Parent
echo $p
CMD /C "protoc -I=./ moviedb.proto --js_out=import_style=commonjs:$p\movie-web\src\proto --grpc-web_out=import_style=commonjs,mode=grpcweb:$p\movie-web\src\proto"
COPY ./moviedb.proto /Y $p\movie-web\src\proto

