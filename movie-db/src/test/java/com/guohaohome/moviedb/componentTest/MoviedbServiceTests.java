package com.guohaohome.moviedb.componentTest;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.guohaohome.moviedb.dao.*;
import com.guohaohome.moviedb.grpc.MoviedbService;
import com.guohaohome.moviedb.ossClient.OSSConfiguration;
import com.guohaohome.moviedb.proto.*;
import com.guohaohome.moviedb.sqlEntity.Comment;
import com.guohaohome.moviedb.sqlEntity.Info;
import com.guohaohome.moviedb.sqlEntity.Line;
import com.guohaohome.moviedb.sqlEntity.UserLike;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@DirtiesContext
public class MoviedbServiceTests {
    private final String ossPrefix = "https://movie-db.oss-eu-west-1.aliyuncs.com/";

    @Autowired
    OSSConfiguration ossConfiguration;
    @Autowired
    private MoviedbService moviedbService;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserLikeMapper userLikeMapper;

    @Test
    public void testGetAllID() throws Exception {
        StreamRecorder<AllMovieIDListResponse> responseObserver = StreamRecorder.create();
        moviedbService.getAllID(null, responseObserver);
        if (!responseObserver.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(responseObserver.getError());
        List<AllMovieIDListResponse> result = responseObserver.getValues();
        assertEquals(1, result.size());
        assertEquals(AllMovieIDListResponse.newBuilder()
                        .addReply(0, MovieID.newBuilder().setId("testMovieId"))
                        .addReply(1, MovieID.newBuilder().setId("testMovieId2")).build()
                , result.get(0));
    }

    @Test
    public void testGetInfoByID() throws Exception {
        StreamRecorder<InfoResponse> responseObserver = StreamRecorder.create();
        moviedbService.getInfoByID(InfoByIDRequest.newBuilder().setId("testMovieId").build(), responseObserver);
        if (!responseObserver.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(responseObserver.getError());
        InfoResponse infoResponse = responseObserver.getValues().get(0);
        assertEquals("testMovieId", infoResponse.getId());
        assertEquals("testMovieName", infoResponse.getName());
        assertEquals(9.0, infoResponse.getIMDb(), 0.1);
        assertEquals(90, infoResponse.getTomatoes());

    }


    @Test
    public void testUploadFileToOSSAndDeleteFileFromOSS() throws Exception {
        final ClassPathResource classPathResource = new ClassPathResource("testImage.txt");
        InputStream inputStream = classPathResource.getInputStream();
        String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        StreamRecorder<BooleanResponse> booleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath("test/")
                .setObjectName("test").setType("jpg").build(), booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity(ossPrefix + "/test/test.jpg", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/test.jpg").build(), booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        assertThrows(RestClientException.class, () -> {
            restTemplate.getForEntity(ossPrefix + "/test/test.jpg", String.class);
        });
    }

    @Test
    public void testUploadTextToOSS() throws Exception {
        TextUploadRequest textUploadRequest = TextUploadRequest.newBuilder().setContent("test:testUploadTextToOSS")
                .setObjectName("test/test.json").build();
        StreamRecorder<BooleanResponse> booleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.uploadTextToOSS(textUploadRequest,booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity(ossPrefix + "/test/test.json", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals("test:testUploadTextToOSS",response.getBody());
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/test.json").build(),booleanResponseStreamRecorder);

    }

    @Test
    public void testGetOssObjectList() throws Exception {
        //Upload some files to the temporary test folder
        String ObjectPath = "test/";
        final ClassPathResource classPathResource = new ClassPathResource("testImage.txt");
        InputStream inputStream = classPathResource.getInputStream();
        String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        StreamRecorder<BooleanResponse> booleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath(ObjectPath)
                .setObjectName("test").setType("jpg").build(), booleanResponseStreamRecorder);
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath(ObjectPath + "OST/")
                .setObjectName("test").setType("jpg").build(), booleanResponseStreamRecorder);
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath(ObjectPath + "screenshot/")
                .setObjectName("test").setType("jpg").build(), booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        StreamRecorder<ObjectListResponse> objectListResponseStreamRecorder = StreamRecorder.create();
        ObjectListRequest objectListRequest = ObjectListRequest.newBuilder().setBucketName(ossConfiguration.getBucketName())
                .setKeyPrefix(ObjectPath).build();
        moviedbService.getOssObjectList(objectListRequest, objectListResponseStreamRecorder);
        if (!objectListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(objectListResponseStreamRecorder.getError());
        List<ObjectList> objectLists = objectListResponseStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(3, objectLists.size());
        List<String> objectPathList = new ArrayList<>();
        for (ObjectList objectList : objectLists) {
            objectPathList.add(objectList.getObjectName());
        }

        assertTrue(objectPathList.contains(ObjectPath + "test.jpg"));
        assertTrue(objectPathList.contains(ObjectPath + "OST/test.jpg"));
        assertTrue(objectPathList.contains(ObjectPath + "screenshot/test.jpg"));
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/test.jpg").build(), booleanResponseStreamRecorder);
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/OST/test.jpg").build(), booleanResponseStreamRecorder);
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/screenshot/test.jpg").build(), booleanResponseStreamRecorder);

    }

    @Test
    public void testInsertMovieAndDeleteMovieByID() throws Exception {
        //Test InsertMovie
        StreamRecorder<InfoResponse> responseObserver = StreamRecorder.create();
        Info info = new Info("RPC_testInsertMovieAndDeleteMovieByIDID", "RPC_testInsertMovieAndDeleteMovieByIDName"
                , 9.2, 92);
        StreamRecorder<MovieID> movieIDStreamRecorder = StreamRecorder.create();
        moviedbService.insertMovie(InfoRequest.newBuilder().setId(info.getId()).setName(info.getName())
                .setIMDb(info.getIMDb()).setTomatoes(info.getTomatoes()).build(), movieIDStreamRecorder);
        moviedbService.getInfoByID(InfoByIDRequest.newBuilder().setId(info.getId()).build(), responseObserver);
        if (!responseObserver.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(responseObserver.getError());
        InfoResponse infoResponse = responseObserver.getValues().get(0);
        assertEquals(info.getId(), infoResponse.getId());
        assertEquals(info.getName(), infoResponse.getName());
        assertEquals(info.getIMDb(), infoResponse.getIMDb(), 0.1);
        assertEquals(info.getTomatoes(), infoResponse.getTomatoes());
        //test movie table
        assertEquals("RPC_testInsertMovieAndDeleteMovieByIDID", movieMapper.getIDByName("RPC_testInsertMovieAndDeleteMovieByIDName"));
        assertEquals("RPC_testInsertMovieAndDeleteMovieByIDName", movieMapper.getNameByID("RPC_testInsertMovieAndDeleteMovieByIDID"));
        //========================================================
        //Test DeleteMovieByID
        //========================================================
        //Upload something to OSS
        final ClassPathResource classPathResource = new ClassPathResource("testImage.txt");
        InputStream inputStream = classPathResource.getInputStream();
        String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        StreamRecorder<BooleanResponse> booleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath("RPC_testInsertMovieAndDeleteMovieByIDID/")
                .setObjectName("test").setType("jpg").build(), booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        //Add some Lines to this movie;
        lineMapper.insertLine(new Line(info.getId(), "testInsertMovieAndDeleteMovieByIDSentence"
                , "testInsertMovieAndDeleteMovieByIDAuthor", "testInsertMovieAndDeleteMovieByIDLine_id"));
        //Add some comments to this movie;
        commentMapper.addComment(new Comment("testInsertMovieAndDeleteMovieByIDCommentID"
                , "testInsertMovieAndDeleteMovieByIDUsername"
                , info.getId(), "testInsertMovieAndDeleteMovieByIDContent"));
        //Add a userLike
        userLikeMapper.insertUserLike(new UserLike("testInsertMovieAndDeleteMovieByIDUsername"
                , info.getId()));

        moviedbService.deleteMovieByID(MovieID.newBuilder().setId(info.getId()).build(), booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        //Test no data in movie and info table
        assertNull(movieMapper.getNameByID(info.getId()));
        assertNull(infoMapper.getInfoByID(info.getId()));
        //Test no data in UserLike table
        assertEquals(0, userLikeMapper.getUserLikes("testInsertMovieAndDeleteMovieByIDUsername").size());
        //Test no data in comment table
        assertEquals(0, commentMapper.getCommentByMovieID(info.getId()).size());
        //Test no data in OSS
        StreamRecorder<ObjectListResponse> objectListResponseStreamRecorder = StreamRecorder.create();
        moviedbService.getOssObjectList(ObjectListRequest.newBuilder().setBucketName(ossConfiguration.getBucketName())
                .setKeyPrefix(info.getId() + "/").build(), objectListResponseStreamRecorder);

        if (!objectListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(objectListResponseStreamRecorder.getError());
        assertEquals(0, objectListResponseStreamRecorder.getValues().get(0).getReplyCount());
    }




}
