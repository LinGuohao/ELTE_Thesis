package com.guohaohome.moviedb.grpc;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.guohaohome.moviedb.dao.*;
import com.guohaohome.moviedb.ossClient.OSSConfiguration;
import com.guohaohome.moviedb.proto.*;
import com.guohaohome.moviedb.sqlEntity.*;
import com.guohaohome.moviedb.sqlEntity.UserLike;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


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
    CommentMapper commentMapper;
    @Autowired
    UserLikeMapper userLikeMapper;
    @Autowired
    OSS ossClient;
    @Autowired
    OSSConfiguration ossConfiguration;
    @Autowired
    Utils utils;


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
    public void insertMovie(InfoRequest request, StreamObserver<MovieID> responseObserver) {
        String id;
        MovieID.Builder builder = MovieID.newBuilder();
        if(request.getId().equals("-1")){
            id = utils.generateId();
        }else{
            id = request.getId();
        }
        Info info = new Info(id, request.getName(), request.getIMDb(), request.getTomatoes());
        infoMapper.insertInfo(info);
        movieMapper.insertMovie(new Movie(info.getId(), info.getName()));
        builder.setId(id);
        MovieID movieID = builder.build();
        responseObserver.onNext(movieID);
        responseObserver.onCompleted();
    }
    @Override
    public void deleteMovieByID(MovieID request,StreamObserver<BooleanResponse> streamObserver){
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        try {
            String nextMarker = null;
            ObjectListing objectListing = null;
            do {
                ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossConfiguration.getBucketName())
                        .withPrefix(request.getId() + "/")
                        .withMarker(nextMarker);

                objectListing = ossClient.listObjects(listObjectsRequest);
                if (objectListing.getObjectSummaries().size() > 0) {
                    List<String> keys = new ArrayList<String>();
                    for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                        System.out.println("key name: " + s.getKey());
                        keys.add(s.getKey());
                    }
                    DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(ossConfiguration.getBucketName()).withKeys(keys).withEncodingType("url");
                    DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
                    List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
                    try {
                        for (String obj : deletedObjects) {
                            String deleteObj = URLDecoder.decode(obj, "UTF-8");
                            System.out.println(deleteObj);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                nextMarker = objectListing.getNextMarker();
            } while (objectListing.isTruncated());
        }catch(Exception e){
            e.printStackTrace();
            builder.setIsTrue(-1);
        }
        movieMapper.deleteMovieByID(request.getId());
        infoMapper.deleteInfoByID(request.getId());
        if(movieMapper.getNameByID(request.getId()) == null && infoMapper.getInfoByID(request.getId())==null){
            builder.setIsTrue(1);
        }else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
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
    public void insertLine(LineList request, StreamObserver<BooleanResponse> responseStreamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = lineMapper.getLines(request.getId()).size();
        Line line = new Line(request.getId(), request.getSentence(), request.getAuthor(), utils.generateId());
        lineMapper.insertLine(line);
        int Length = lineMapper.getLines(request.getId()).size();
        if (oldLength + 1 == Length) {
            builder.setIsTrue(1);
        } else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void deleteLine(deleteLineRequest request, StreamObserver<BooleanResponse> streamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = lineMapper.getLines(request.getId()).size();
        System.out.println(oldLength);
        lineMapper.deleteLine(request.getLineID());
        int Length = lineMapper.getLines(request.getId()).size();
        System.out.println(Length);
        if (oldLength - 1 == Length) {
            builder.setIsTrue(1);
        } else {
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
                String[] result = utils.generateMusicInformation(fullName);
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

    @Override
    public void uploadTextToOSS(TextUploadRequest request, StreamObserver<BooleanResponse> streamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfiguration.getBucketName(),
                    request.getObjectName(), new ByteArrayInputStream(request.getContent().getBytes()));
            ossClient.putObject(putObjectRequest);
            builder.setIsTrue(1);
        } catch (OSSException | ClientException exception) {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }

    @Override
    public void uploadFileToOSS(FileUploadRequest request, StreamObserver<BooleanResponse> streamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        String filename;
        if(request.getObjectName().equals("-1")){
            filename =  utils.generateFileName(request.getType());
        }else{
            filename = request.getObjectName()+"."+request.getType();
            //System.out.println(filename);
        }
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfiguration.getBucketName()
                    , request.getObjectPath() + filename, Utils.base64ToFile(request.getContent()));
            ossClient.putObject(putObjectRequest);
            builder.setIsTrue(1);
        } catch (OSSException | ClientException exception) {
            builder.setIsTrue(-1);
        } catch (Exception e) {
            builder.setIsTrue(-1);
            e.printStackTrace();
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }

    @Override
    public void deleteFileFromOSS(FileDeleteRequest request, StreamObserver<BooleanResponse> streamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        try {
            ossClient.deleteObject(ossConfiguration.getBucketName(), request.getFilePath());
            builder.setIsTrue(1);
        } catch (OSSException | ClientException exception) {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }

    @Override
    public void uploadMusicToOSS(MusicUploadRequest request, StreamObserver<BooleanResponse> streamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfiguration.getBucketName(),
                    request.getMusicFilePath() + utils.generateMusicFileName(request.getMusicName(), request.getArtist(), request.getType())
                    , Utils.base64ToFile(request.getContent()));
            ossClient.putObject(putObjectRequest);
            builder.setIsTrue(1);
        } catch (Exception e) {
            builder.setIsTrue(-1);
            e.printStackTrace();
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
    @Override
    public void getCommentByMovieID(InfoByIDRequest request,StreamObserver<CommentListResponse> responseObserver){
        CommentListResponse.Builder builder = CommentListResponse.newBuilder();
        List<Comment> comments = commentMapper.getCommentByMovieID(request.getId());
        for (Comment comment : comments) {
            builder.addReply(CommentInfo.newBuilder().setCommentID(comment.getCommentID()).setUsername(comment.getUsername())
                    .setMovieID(comment.getMovieID()).setContent(comment.getContent()));
        }
        CommentListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void getCommentByUserName (InfoByIDRequest request,StreamObserver<CommentListResponse> responseObserver){
        CommentListResponse.Builder builder = CommentListResponse.newBuilder();
        List<Comment> comments = commentMapper.getCommentByUserName(request.getId());
        for (Comment comment : comments) {
            builder.addReply(CommentInfo.newBuilder().setCommentID(comment.getCommentID()).setUsername(comment.getUsername())
                    .setMovieID(comment.getMovieID()).setContent(comment.getContent()));
        }
        CommentListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void insertComment(CommentInfo request,StreamObserver<BooleanResponse> responseStreamObserver){
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = commentMapper.getCommentByUserName(request.getUsername()).size();
        String commentID;
        if(request.getCommentID().equals("-1")){
            commentID = utils.generateId();
        }else{
            commentID = request.getCommentID();
        }
        Comment comment = new Comment(commentID,request.getUsername(),request.getMovieID(),request.getContent());
        commentMapper.addComment(comment);
        int Length = commentMapper.getCommentByUserName(request.getUsername()).size();
        if (oldLength + 1 == Length) {
            builder.setIsTrue(1);
        } else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
    @Override
    public void deleteComment(InfoByIDRequest request,StreamObserver<BooleanResponse> streamObserver){
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        commentMapper.deleteComment(request.getId());
        if (commentMapper.getCommentByCommentID(request.getId())==null) {
            builder.setIsTrue(1);
        } else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
    @Override
    public void getUserLikes(UsernameRequest request, StreamObserver<UserLikeListResponse> responseObserver){
        UserLikeListResponse.Builder builder = UserLikeListResponse.newBuilder();
        List<UserLike> userLikeList = userLikeMapper.getUserLikes(request.getUsername());
        for (UserLike userLike : userLikeList) {
            builder.addReply(UserLikeInfo.newBuilder().setUsername(userLike.getUsername()).setId(userLike.getId()));
        }
        UserLikeListResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void insertUserLike(UserLikeInfo request, StreamObserver<BooleanResponse> responseStreamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = userLikeMapper.getUserLikes(request.getUsername()).size();
        UserLike userLike = new UserLike(request.getUsername(),request.getId());
        userLikeMapper.insertUserLike(userLike);
        int Length = userLikeMapper.getUserLikes(request.getUsername()).size();
        if (oldLength + 1 == Length) {
            builder.setIsTrue(1);
        } else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
    @Override
    public void deleteUserLike(UserLikeInfo request, StreamObserver<BooleanResponse> streamObserver) {
        BooleanResponse.Builder builder = BooleanResponse.newBuilder();
        int oldLength = userLikeMapper.getUserLikes(request.getUsername()).size();
        userLikeMapper.deleteUserLike(new UserLike(request.getUsername(),request.getId()));
        int Length = userLikeMapper.getUserLikes(request.getUsername()).size();
        if (oldLength - 1 == Length) {
            builder.setIsTrue(1);
        } else {
            builder.setIsTrue(-1);
        }
        BooleanResponse response = builder.build();
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
    @Override
    public void getFavoriteMovieList(UsernameRequest request, StreamObserver<InfoList> responseObserver){
        InfoList.Builder builder = InfoList.newBuilder();
        List<UserLike> userLikeList = userLikeMapper.getUserLikes(request.getUsername());
        List<String> movieID =  movieMapper.getAllID();
        for (UserLike userLike : userLikeList) {
            if(movieID.contains(userLike.getId())){
                Info info = infoMapper.getInfoByID(userLike.getId());
                builder.addReply(InfoResponse.newBuilder().setId(info.getId()).setName(info.getName())
                        .setIMDb(info.getIMDb()).setTomatoes(info.getTomatoes()));
            }
        }
        InfoList response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }




}
