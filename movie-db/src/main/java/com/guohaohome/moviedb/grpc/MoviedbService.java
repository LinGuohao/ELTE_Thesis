package com.guohaohome.moviedb.grpc;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.OSSObjectSummary;
import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.ossClient.OSSConfiguration;
import com.guohaohome.moviedb.proto.*;
import com.guohaohome.moviedb.sqlEntity.Info;
import com.guohaohome.moviedb.sqlEntity.Movie;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@GrpcService
@Transactional
public class MoviedbService extends MoviedbServiceGrpc.MoviedbServiceImplBase {

        @Autowired
        MovieMapper movieMapper;
        @Autowired
        InfoMapper infoMapper;
        @Autowired
        OSS ossClient;
        @Autowired
        OSSConfiguration ossConfiguration;
        @Override
        public void getAllID (com.google.protobuf.Empty request,
                              StreamObserver<AllMovieIDListResponse> responseObserver){
                List<String> allID = movieMapper.getAllID();
                AllMovieIDListResponse.Builder builder = AllMovieIDListResponse.newBuilder();
                for (String ID: allID) {
                        builder.addReply(MovieID.newBuilder().setId(ID));
                }
                AllMovieIDListResponse response = builder.build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();

        }
        @Override
        public void getInfoByID(InfoByIDRequest request, StreamObserver<InfoResponse> responseObserver){
                Info info =  infoMapper.getInfoByID(request.getId());
                InfoResponse.Builder builder = InfoResponse.newBuilder();
                builder.setId(info.getId()).setName(info.getName()).
                        setIMDb(info.getIMDb()).setTomatoes(info.getTomatoes());
                InfoResponse infoResponse = builder.build();
                responseObserver.onNext(infoResponse);
                responseObserver.onCompleted();
        }

        @Override
        public void updateByID(InfoRequest request, StreamObserver<com.google.protobuf.Empty> responseObserver){
                Info info = new Info(request.getId(),request.getName(),request.getIMDb(),request.getTomatoes());
                infoMapper.updateInfoByID(info,info.getId());
                movieMapper.updateNameByID(info.getId(),info.getName());
        }
        @Override
        public void insertMovie(InfoRequest request ,StreamObserver<com.google.protobuf.Empty> responseObserver){
                Info info = new Info(request.getId(),request.getName(),request.getIMDb(),request.getTomatoes());
                infoMapper.insertInfo(info);
                movieMapper.insertMovie(new Movie(info.getId(),info.getName()));

        }
        @Override
        public void getOssObjectList(ObjectListRequest request,StreamObserver<ObjectListResponse> responseObserver){
                ListObjectsV2Result result = ossClient.listObjectsV2(request.getBucketName(),request.getKeyPrefix());
                List<OSSObjectSummary> ossObjectSummaries = result.getObjectSummaries();
                ObjectListResponse.Builder builder = ObjectListResponse.newBuilder();
                for (OSSObjectSummary s : ossObjectSummaries) {
                        builder.addReply(ObjectList.newBuilder().setObjectName(s.getKey()));

                }
                ObjectListResponse response = builder.build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();

        }
}
