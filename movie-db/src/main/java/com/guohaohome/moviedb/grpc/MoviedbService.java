package com.guohaohome.moviedb.grpc;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.OSSObjectSummary;
import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.dao.LineMapper;
import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.dao.UserMapper;
import com.guohaohome.moviedb.ossClient.OSSConfiguration;
import com.guohaohome.moviedb.proto.*;
import com.guohaohome.moviedb.sqlEntity.Info;
import com.guohaohome.moviedb.sqlEntity.Line;
import com.guohaohome.moviedb.sqlEntity.Movie;
import com.guohaohome.moviedb.sqlEntity.User;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


@GrpcService
@Transactional
public class MoviedbService extends MoviedbServiceGrpc.MoviedbServiceImplBase {

    @Autowired
    MovieMapper movieMapper;
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    LineMapper lineMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OSS ossClient;
    @Autowired
    OSSConfiguration ossConfiguration;

    public static String SHA256Encryption(String plaintext) {
        MessageDigest messageDigest;
        String ciphertext = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(plaintext.getBytes(StandardCharsets.UTF_8));
            ciphertext = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    @Override
    public void getAllID(com.google.protobuf.Empty request,
                         StreamObserver<AllMovieIDListResponse> responseObserver) {
        List<String> allID = movieMapper.getAllID();
        AllMovieIDListResponse.Builder builder = AllMovieIDListResponse.newBuilder();
        for (String ID : allID) {
            builder.addReply(MovieID.newBuilder().setId(ID));
        }
        AllMovieIDListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getInfoByID(InfoByIDRequest request, StreamObserver<InfoResponse> responseObserver) {
        Info info = infoMapper.getInfoByID(request.getId());
        InfoResponse.Builder builder = InfoResponse.newBuilder();
        builder.setId(info.getId()).setName(info.getName()).
                setIMDb(info.getIMDb()).setTomatoes(info.getTomatoes());
        InfoResponse infoResponse = builder.build();
        responseObserver.onNext(infoResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void updateByID(InfoRequest request, StreamObserver<com.google.protobuf.Empty> responseObserver) {
        Info info = new Info(request.getId(), request.getName(), request.getIMDb(), request.getTomatoes());
        infoMapper.updateInfoByID(info, info.getId());
        movieMapper.updateNameByID(info.getId(), info.getName());
    }

    @Override
    public void insertMovie(InfoRequest request, StreamObserver<com.google.protobuf.Empty> responseObserver) {
        Info info = new Info(request.getId(), request.getName(), request.getIMDb(), request.getTomatoes());
        infoMapper.insertInfo(info);
        movieMapper.insertMovie(new Movie(info.getId(), info.getName()));

    }

    @Override
    public void getOssObjectList(ObjectListRequest request, StreamObserver<ObjectListResponse> responseObserver) {
        ListObjectsV2Result result = ossClient.listObjectsV2(request.getBucketName(), request.getKeyPrefix());
        List<OSSObjectSummary> ossObjectSummaries = result.getObjectSummaries();
        ObjectListResponse.Builder builder = ObjectListResponse.newBuilder();
        for (OSSObjectSummary s : ossObjectSummaries) {
            builder.addReply(ObjectList.newBuilder().setObjectName(s.getKey()));

        }
        ObjectListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getLines(InfoByIDRequest request, StreamObserver<LineListResponse> responseObserver) {
        LineListResponse.Builder builder = LineListResponse.newBuilder();
        List<Line> lines = lineMapper.getLines(request.getId());
        for (Line line : lines) {
            builder.addReply(LineList.newBuilder().setId(line.getId()).setSentence(line.getSentence())
                    .setAuthor(line.getAuthor()).setLineID(line.getLine_id()));
        }
        LineListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();


    }

    @Override
    public void insertLine(LineList request, StreamObserver<BooleanResponse> responseStreamObserver){
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = lineMapper.getLines(request.getId()).size();
        Line line = new Line(request.getId(),request.getSentence(),request.getAuthor(),generateId());
        lineMapper.insertLine(line);
        int Length = lineMapper.getLines(request.getId()).size();
        if(oldLength+1 == Length){
            builder.setIsTrue(1);
        }else{
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
    @Override
    public void deleteLine(deleteLineRequest request,StreamObserver<BooleanResponse> streamObserver){
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = lineMapper.getLines(request.getId()).size();
        System.out.println(oldLength);
        lineMapper.deleteLine(request.getLineID());
        int Length = lineMapper.getLines(request.getId()).size();
        System.out.println(Length);
        if(oldLength-1 == Length){
            builder.setIsTrue(1);
        }else{
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
    @Override
    public void getMusics(InfoByIDRequest request, StreamObserver<MusicListResponse> responseObserver) {
        MusicListResponse.Builder builder = MusicListResponse.newBuilder();
        ListObjectsV2Result res = ossClient.listObjectsV2("movie-db", request.getId() + "/OST");
        List<OSSObjectSummary> ossObjectSummaries = res.getObjectSummaries();
        for (OSSObjectSummary s : ossObjectSummaries) {
            String link = s.getKey();
            if (link.length() != 0) {
                String[] splitLink = link.split("/");
                String fullName = splitLink[splitLink.length - 1];
                String[] result = generateMusicInformation(fullName);
                builder.addReply(MusicInfo.newBuilder().setId(request.getId()).setAddress(link).setMusicName(result[0]).setArtist(result[1]));
            }
        }
        MusicListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void judgeUsername(UsernameRequest usernameRequest, StreamObserver<BooleanResponse> responseObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        User user = userMapper.getUserByUserName(usernameRequest.getUsername());
        builder.setIsTrue(user == null ? -1 : 1);
        BooleanResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void insertUser(UserInfo userInfo, StreamObserver<BooleanResponse> responseStreamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        User user = userMapper.getUserByUserName(userInfo.getUsername());
        if (user == null) {
            String password = SHA256Encryption(userInfo.getPassword());
            userMapper.insertUser(new User(userInfo.getUsername(), password, userInfo.getFullName(),
                    userInfo.getRoles()));
            builder.setIsTrue(userMapper.getUserByUserName(userInfo.getUsername()) != null ? 1 : -1);
        } else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();

    }

    @Override
    public void getUserByUserName(UsernameRequest usernameRequest, StreamObserver<UserInfo> streamObserver) {
        UserInfo.Builder builder = UserInfo.newBuilder();
        User user = userMapper.getUserByUserName(usernameRequest.getUsername());
        if (user != null) {
            builder.setUsername(user.getUserName()).setPassword(user.getPassword())
                    .setFullName(user.getFullName()).setRoles(user.getRoles());
        }
        UserInfo response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();

    }

    @Override
    public void authenticateUser(VerificationRequest verificationRequest, StreamObserver<UserInfo> streamObserver) {
        UserInfo.Builder builder = UserInfo.newBuilder();
        User user = userMapper.getUserByUserName(verificationRequest.getUsername());
        if (user == null) {
            builder.setUsername("null");
        } else {
            if (!SHA256Encryption(verificationRequest.getPassword()).equals(user.getPassword())) {
                builder.setUsername("wrong");
            } else {
                builder.setUsername(user.getUserName()).setFullName(user.getFullName())
                        .setPassword(user.getPassword()).setRoles(user.getRoles());
            }
        }
        UserInfo response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }


    public String[] generateMusicInformation(String fullName) {
        String[] temp = fullName.split("\\.");
        fullName = "";
        for (int i = 0; i < temp.length - 1; i++) {
            fullName = fullName.concat(temp[i]);
            if (i != temp.length - 2) {
                fullName = fullName.concat(".");
            }
        }
        return fullName.split("_");
    }

    public String generateId(){
        long time = Calendar.getInstance().getTimeInMillis();
        Random r = new Random();
        r.setSeed(time);
        int randInt = r.nextInt(100000);
        return String.valueOf(time) + String.valueOf(randInt);
    }
}
