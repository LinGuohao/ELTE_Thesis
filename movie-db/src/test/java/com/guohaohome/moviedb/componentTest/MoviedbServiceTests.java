package com.guohaohome.moviedb.componentTest;


import com.guohaohome.moviedb.dao.*;
import com.guohaohome.moviedb.grpc.MoviedbService;
import com.guohaohome.moviedb.grpc.Utils;
import com.guohaohome.moviedb.ossClient.OSSConfiguration;
import com.guohaohome.moviedb.proto.*;
import com.guohaohome.moviedb.sqlEntity.*;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
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
    @Autowired
    private UserMapper userMapper;

    @Test
    @DirtiesContext
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
    @DirtiesContext
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
    @DirtiesContext
    public void testUploadFileToOSSAndDeleteFileFromOSS() throws Exception {
        final ClassPathResource classPathResource = new ClassPathResource("testImage.txt");
        InputStream inputStream = classPathResource.getInputStream();
        String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        StreamRecorder<BooleanResponse> uploadBoolean = StreamRecorder.create();
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath("test/")
                .setObjectName("test").setType("jpg").build(), uploadBoolean);
        if (!uploadBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(uploadBoolean.getError());
        assertEquals(1, uploadBoolean.getValues().get(0).getIsTrue());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity(ossPrefix + "/test/test.jpg", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        StreamRecorder<BooleanResponse> deleteBoolean = StreamRecorder.create();
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/test.jpg").build(), deleteBoolean);
        if (!deleteBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(deleteBoolean.getError());
        assertEquals(1, deleteBoolean.getValues().get(0).getIsTrue());
        assertThrows(RestClientException.class, () -> {
            restTemplate.getForEntity(ossPrefix + "/test/test.jpg", String.class);
        });
    }

    @Test
    @DirtiesContext
    public void testUploadTextToOSS() throws Exception {
        TextUploadRequest textUploadRequest = TextUploadRequest.newBuilder().setContent("test:testUploadTextToOSS")
                .setObjectName("test/test.json").build();
        StreamRecorder<BooleanResponse> booleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.uploadTextToOSS(textUploadRequest, booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity(ossPrefix + "/test/test.json", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals("test:testUploadTextToOSS", response.getBody());
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath("test/test.json").build(), booleanResponseStreamRecorder);

    }

    @Test
    @DirtiesContext
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
    @DirtiesContext
    public void testGetLines() throws Exception {
        StreamRecorder<LineListResponse> lineListResponseStreamRecorder = StreamRecorder.create();
        InfoByIDRequest infoByIDRequest = InfoByIDRequest.newBuilder().setId("testMovieId").build();
        moviedbService.getLines(infoByIDRequest, lineListResponseStreamRecorder);
        if (!lineListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(lineListResponseStreamRecorder.getError());
        List<LineList> lineList = lineListResponseStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(2, lineList.size());
        assertEquals("testMovieId", lineList.get(0).getId());
        assertEquals("testSentence", lineList.get(0).getSentence());
        assertEquals("testAuthor", lineList.get(0).getAuthor());
        assertEquals("testLine_id", lineList.get(0).getLineID());
        assertEquals("testMovieId", lineList.get(0).getId());
        assertEquals("testSentence2", lineList.get(1).getSentence());
        assertEquals("testAuthor2", lineList.get(1).getAuthor());
        assertEquals("testLine_id2", lineList.get(1).getLineID());
    }

    @Test
    @DirtiesContext
    public void testInsertLineAndDeleteLine() throws Exception {
        //Insert
        //====================================
        StreamRecorder<BooleanResponse> insertBoolean = StreamRecorder.create();
        LineList lineList = LineList.newBuilder().setLineID("testInsertLineAndDeleteLineLineID")
                .setSentence("testInsertLineAndDeleteLineSentence").setAuthor("testInsertLineAndDeleteLineAuthor")
                .setId("testInsertLineAndDeleteLineID").build();
        moviedbService.insertLine(lineList, insertBoolean);
        if (!insertBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(insertBoolean.getError());
        assertEquals(1, insertBoolean.getValues().get(0).getIsTrue());
        List<Line> lines = lineMapper.getLines(lineList.getId());
        assertEquals(1, lines.size());
        assertEquals(lineList.getId(), lines.get(0).getId());
        assertEquals(lineList.getSentence(), lines.get(0).getSentence());
        assertEquals(lineList.getAuthor(), lines.get(0).getAuthor());
        assertEquals(lineList.getLineID(), lines.get(0).getLine_id());
        //============================
        //Delete
        deleteLineRequest deleteLineRequest = com.guohaohome.moviedb.proto.deleteLineRequest.newBuilder()
                .setId(lineList.getId()).setLineID(lineList.getLineID()).build();
        StreamRecorder<BooleanResponse> deleteBoolean = StreamRecorder.create();
        moviedbService.deleteLine(deleteLineRequest, deleteBoolean);
        if (!deleteBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(deleteBoolean.getError());
        assertEquals(1, deleteBoolean.getValues().get(0).getIsTrue());
        lines = lineMapper.getLines(lineList.getId());
        assertEquals(0, lines.size());


    }

    @Test
    @DirtiesContext
    public void testGetMusicsAndMusicUploadMusicToOSS() throws Exception {
        //Upload two musics to test folder
        String ObjectPath = "test/OST/";
        ClassPathResource classPathResource = new ClassPathResource("testMusic.txt");
        InputStream inputStream = classPathResource.getInputStream();
        String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        StreamRecorder<BooleanResponse> uploadBoolean = StreamRecorder.create();
        moviedbService.uploadMusicToOSS(MusicUploadRequest.newBuilder().setContent(content).setMusicFilePath(ObjectPath)
                .setMusicName("Respect").setType("mp3").setArtist("Aretha Franklin").build(), uploadBoolean);
        if (!uploadBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(uploadBoolean.getError());
        classPathResource = new ClassPathResource("testMusic2.txt");
        inputStream = classPathResource.getInputStream();
        content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();
        StreamRecorder<BooleanResponse> uploadBoolean2 = StreamRecorder.create();
        moviedbService.uploadMusicToOSS(MusicUploadRequest.newBuilder().setContent(content).setMusicFilePath(ObjectPath)
                .setMusicName("Raindrops Keep Falling On My Head").setType("mp3").setArtist("B.J. Thomas").build(), uploadBoolean2);
        if (!uploadBoolean2.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(uploadBoolean2.getError());

        //==========
        // Test method
        InfoByIDRequest infoByIDRequest = InfoByIDRequest.newBuilder().setId("test").build();
        StreamRecorder<MusicListResponse> musicListResponseStreamRecorder = StreamRecorder.create();
        moviedbService.getMusics(infoByIDRequest, musicListResponseStreamRecorder);
        if (!musicListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(musicListResponseStreamRecorder.getError());
        List<MusicInfo> musicInfo = musicListResponseStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(2, musicInfo.size());
        assertEquals("test/OST/Raindrops Keep Falling On My Head_B.J. Thomas.mp3", musicInfo.get(0).getAddress());
        assertEquals("Raindrops Keep Falling On My Head", musicInfo.get(0).getMusicName());
        assertEquals("test", musicInfo.get(0).getId());
        assertEquals("B.J. Thomas", musicInfo.get(0).getArtist());
        assertEquals("test/OST/Respect_Aretha Franklin.mp3", musicInfo.get(1).getAddress());
        assertEquals("Respect", musicInfo.get(1).getMusicName());
        assertEquals("test", musicInfo.get(1).getId());
        assertEquals("Aretha Franklin", musicInfo.get(1).getArtist());

        //Delete tmp test folder
        StreamRecorder<BooleanResponse> deleteBoolean = StreamRecorder.create();
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath(musicInfo.get(0).getAddress()).build()
                , deleteBoolean);
        moviedbService.deleteFileFromOSS(FileDeleteRequest.newBuilder().setFilePath(musicInfo.get(1).getAddress()).build()
                , deleteBoolean);

    }

    @Test
    @DirtiesContext
    public void testJudgeUsername() throws Exception {
        StreamRecorder<BooleanResponse>booleanResponseStreamRecorder = StreamRecorder.create();
        UsernameRequest usernameRequest = UsernameRequest.newBuilder().setUsername("testUserName@test.com").build();
        moviedbService.judgeUsername(usernameRequest,booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        usernameRequest = UsernameRequest.newBuilder().setUsername("testJudgeUsername@test.com").build();
        StreamRecorder<BooleanResponse>newBooleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.judgeUsername(usernameRequest,newBooleanResponseStreamRecorder);
        if (!newBooleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(newBooleanResponseStreamRecorder.getError());
        assertEquals(-1, newBooleanResponseStreamRecorder.getValues().get(0).getIsTrue());


    }

    @Test
    @DirtiesContext
    public void testInsertUser() throws Exception {
        UserInfo userInfo = UserInfo.newBuilder().setUsername("testInsertUser@test.com")
                .setPassword("testInsertUserPassword")
                .setFullName("testInsertUserFullName")
                .setRoles("user").build();
        StreamRecorder<BooleanResponse>booleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.insertUser(userInfo,booleanResponseStreamRecorder);
        if (!booleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(booleanResponseStreamRecorder.getError());
        assertEquals(1, booleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        User user = userMapper.getUserByUserName(userInfo.getUsername());
        assertEquals(userInfo.getUsername(),user.getUserName());
        assertEquals(Utils.SHA256Encryption(userInfo.getPassword()),user.getPassword());
        assertEquals(userInfo.getFullName(),user.getFullName());
        assertEquals(userInfo.getRoles(),user.getRoles());
        //Cannot be inserted when have the same username in database
        StreamRecorder<BooleanResponse>newBooleanResponseStreamRecorder = StreamRecorder.create();
        moviedbService.insertUser(userInfo,newBooleanResponseStreamRecorder);
        if (!newBooleanResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(newBooleanResponseStreamRecorder.getError());
        assertEquals(-1, newBooleanResponseStreamRecorder.getValues().get(0).getIsTrue());
        userMapper.deleteUserByUserName(userInfo.getUsername());
    }
    @Test
    @DirtiesContext
    public void testGetUserByUserName() throws Exception {
        UsernameRequest usernameRequest = UsernameRequest.newBuilder().setUsername("testUserName@test.com").build();
        StreamRecorder<UserInfo>userInfoStreamRecorder = StreamRecorder.create();
        moviedbService.getUserByUserName(usernameRequest,userInfoStreamRecorder);
        if (!userInfoStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(userInfoStreamRecorder.getError());
        UserInfo userInfo = userInfoStreamRecorder.getValues().get(0);
        assertEquals("testUserName@test.com",userInfo.getUsername());
        assertEquals("testPassword",userInfo.getPassword());
        assertEquals("testFullname",userInfo.getFullName());
        assertEquals("testRoles",userInfo.getRoles());
    }

    @Test
    @DirtiesContext
    public void testAuthenticateUser() throws Exception {
        //insert a user to test
        String password = "testPassword";
        User user = new User("testAuthenticateUserUsername",Utils.SHA256Encryption(password),"testAuthenticateUserFullName"
                            ,"testRules");
        userMapper.insertUser(user);
        StreamRecorder<UserInfo> successUserInfo = StreamRecorder.create();
        moviedbService.authenticateUser(VerificationRequest.newBuilder().setUsername(user.getUserName())
                    .setPassword(password).build(),successUserInfo);
        if (!successUserInfo.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(successUserInfo.getError());
        UserInfo successUser = successUserInfo.getValues().get(0);
        assertEquals(Utils.SHA256Encryption(password),successUser.getPassword());
        assertEquals(user.getUserName(),successUser.getUsername());
        assertEquals(user.getFullName(),successUser.getFullName());
        assertEquals(user.getRoles(),successUser.getRoles());
        //No such user
        StreamRecorder<UserInfo> noUser = StreamRecorder.create();
        moviedbService.authenticateUser(VerificationRequest.newBuilder().setUsername("NoUserUsername")
                .setPassword(user.getPassword()).build(),noUser);
        if (!noUser.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(noUser.getError());
        assertEquals("null",noUser.getValues().get(0).getUsername());
        //Wrong password
        StreamRecorder<UserInfo> wrongPassword = StreamRecorder.create();
        moviedbService.authenticateUser(VerificationRequest.newBuilder().setUsername(user.getUserName())
                .setPassword("WrongPassword").build(),wrongPassword);
        if (!wrongPassword.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(wrongPassword.getError());
        assertEquals("wrong",wrongPassword.getValues().get(0).getUsername());
        //Delete tmp user
        userMapper.deleteUserByUserName(user.getUserName());
    }

    @Test
    @DirtiesContext
    public void testGetCommentByMovieID() throws Exception {
        StreamRecorder<CommentListResponse> commentListResponseStreamRecorder = StreamRecorder.create();
        InfoByIDRequest infoByIDRequest = InfoByIDRequest.newBuilder().setId("testMovieId").build();
        moviedbService.getCommentByMovieID(infoByIDRequest,commentListResponseStreamRecorder);
        if (!commentListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(commentListResponseStreamRecorder.getError());
        List<CommentInfo> commentInfoList = commentListResponseStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(2,commentInfoList.size());
        assertEquals("testCommentID",commentInfoList.get(0).getCommentID());
        assertEquals("testUserName",commentInfoList.get(0).getUsername());
        assertEquals("testMovieId",commentInfoList.get(0).getMovieID());
        assertEquals("testContent",commentInfoList.get(0).getContent());
        assertEquals("testCommentID2",commentInfoList.get(1).getCommentID());
        assertEquals("testUserName2",commentInfoList.get(1).getUsername());
        assertEquals("testMovieId",commentInfoList.get(1).getMovieID());
        assertEquals("testContent2",commentInfoList.get(1).getContent());
    }

    @Test
    @DirtiesContext
    public void testGetCommentByUserName() throws Exception {
        InfoByIDRequest infoByIDRequest = InfoByIDRequest.newBuilder().setId("testUserName2").build();
        StreamRecorder<CommentListResponse> commentListResponseStreamRecorder = StreamRecorder.create();
        moviedbService.getCommentByUserName(infoByIDRequest,commentListResponseStreamRecorder);
        if (!commentListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(commentListResponseStreamRecorder.getError());
        List<CommentInfo> commentInfoList = commentListResponseStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(2,commentInfoList.size());
        assertEquals("testCommentID2",commentInfoList.get(0).getCommentID());
        assertEquals("testUserName2",commentInfoList.get(0).getUsername());
        assertEquals("testMovieId",commentInfoList.get(0).getMovieID());
        assertEquals("testContent2",commentInfoList.get(0).getContent());
        assertEquals("testCommentID3",commentInfoList.get(1).getCommentID());
        assertEquals("testUserName2",commentInfoList.get(1).getUsername());
        assertEquals("testMovieId2",commentInfoList.get(1).getMovieID());
        assertEquals("testContent3",commentInfoList.get(1).getContent());
    }

    @Test
    @DirtiesContext
    public void testInsertCommentAndDeleteComment() throws Exception {
        //Insert
        CommentInfo commentInfo = CommentInfo.newBuilder().setCommentID("testInsertCommentAndDeleteCommentCommentID")
                .setUsername("testInsertCommentAndDeleteCommentUsername")
                .setMovieID("testInsertCommentAndDeleteCommentMovieID")
                .setContent("testInsertCommentAndDeleteCommentContent").build();
        StreamRecorder<BooleanResponse>insertBoolean = StreamRecorder.create();
        moviedbService.insertComment(commentInfo,insertBoolean);
        if (!insertBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(insertBoolean.getError());
        assertEquals(1, insertBoolean.getValues().get(0).getIsTrue());
        Comment comment = commentMapper.getCommentByCommentID(commentInfo.getCommentID());
        assertEquals(commentInfo.getCommentID(),comment.getCommentID());
        assertEquals(commentInfo.getContent(),comment.getContent());
        assertEquals(commentInfo.getUsername(),comment.getUsername());
        assertEquals(commentInfo.getMovieID(),comment.getMovieID());
        //===================================
        //Test delete
        InfoByIDRequest infoByIDRequest = InfoByIDRequest.newBuilder().setId(commentInfo.getCommentID()).build();
        StreamRecorder<BooleanResponse> deleteBoolean = StreamRecorder.create();
        moviedbService.deleteComment(infoByIDRequest,deleteBoolean);
        if (!deleteBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(deleteBoolean.getError());
        assertEquals(1, deleteBoolean.getValues().get(0).getIsTrue());
        assertNull(commentMapper.getCommentByCommentID(commentInfo.getCommentID()));
    }

    @Test
    @DirtiesContext
    public void testInsertUserLikeAndDeleteUserLike() throws Exception {
        //Test Insert
        UserLikeInfo userLikeInfo = UserLikeInfo.newBuilder().setUsername("testInsertUserLikeAndDeleteUserLikeUserName")
                .setId("testInsertUserLikeAndDeleteUserLikeID").build();
        StreamRecorder<BooleanResponse>insertBoolean = StreamRecorder.create();
        moviedbService.insertUserLike(userLikeInfo,insertBoolean);
        if (!insertBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(insertBoolean.getError());
        assertEquals(1, insertBoolean.getValues().get(0).getIsTrue());
        UserLike userLike =  userLikeMapper.getUserLikes(userLikeInfo.getUsername()).get(0);
        assertEquals(userLikeInfo.getUsername(),userLike.getUsername());
        assertEquals(userLikeInfo.getId(),userLikeInfo.getId());
        //Test Delete
        //Can Delete
        StreamRecorder<BooleanResponse>successBoolean = StreamRecorder.create();
        moviedbService.deleteUserLike(userLikeInfo,successBoolean);
        if (!successBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(successBoolean.getError());
        assertEquals(1, successBoolean.getValues().get(0).getIsTrue());
        List<UserLike>userLikeList = userLikeMapper.getUserLikes(userLikeInfo.getUsername());
        assertEquals(0,userLikeList.size());
        //No such UserLike record, can not be deleted
        StreamRecorder<BooleanResponse>failBoolean = StreamRecorder.create();
        moviedbService.deleteUserLike(userLikeInfo,failBoolean);
        if (!failBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(failBoolean.getError());
        assertEquals(-1, failBoolean.getValues().get(0).getIsTrue());

    }

    @Test
    @DirtiesContext
    public void testGetUserLikes() throws Exception {
        UsernameRequest usernameRequest = UsernameRequest.newBuilder().setUsername("testUserName").build();
        StreamRecorder<UserLikeListResponse>userLikeListResponseStreamRecorder = StreamRecorder.create();
        moviedbService.getUserLikes(usernameRequest,userLikeListResponseStreamRecorder);
        if (!userLikeListResponseStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(userLikeListResponseStreamRecorder.getError());
        List<UserLikeInfo>userLikeInfoList = userLikeListResponseStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(2,userLikeInfoList.size());
        assertEquals("testUserName",userLikeInfoList.get(0).getUsername());
        assertEquals("testId",userLikeInfoList.get(0).getId());
        assertEquals("testUserName",userLikeInfoList.get(1).getUsername());
        assertEquals("testId2",userLikeInfoList.get(1).getId());
    }


    @Test
    @DirtiesContext
    public void testGetFavoriteMovieList() throws Exception {
        UsernameRequest usernameRequest = UsernameRequest.newBuilder().setUsername("favoriteMovieUserName").build();
        StreamRecorder<InfoList>infoListStreamRecorder = StreamRecorder.create();
        moviedbService.getFavoriteMovieList(usernameRequest,infoListStreamRecorder);
        if (!infoListStreamRecorder.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(infoListStreamRecorder.getError());
        List<InfoResponse> infoResponseList = infoListStreamRecorder.getValues().get(0).getReplyList();
        assertEquals(1,infoResponseList.size());
        InfoResponse infoResponse = infoResponseList.get(0);
        assertEquals("testMovieId",infoResponse.getId());
        assertEquals("testMovieName",infoResponse.getName());
        assertEquals(9.0,infoResponse.getIMDb(),0.1);
        assertEquals(90,infoResponse.getTomatoes());

    }

    @Test
    @DirtiesContext
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
        StreamRecorder<BooleanResponse> uploadBoolean = StreamRecorder.create();
        moviedbService.uploadFileToOSS(FileUploadRequest.newBuilder().setContent(content).setObjectPath("RPC_testInsertMovieAndDeleteMovieByIDID/")
                .setObjectName("test").setType("jpg").build(), uploadBoolean);
        if (!uploadBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(uploadBoolean.getError());
        assertEquals(1, uploadBoolean.getValues().get(0).getIsTrue());
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

        StreamRecorder<BooleanResponse> deleteBoolean = StreamRecorder.create();
        moviedbService.deleteMovieByID(MovieID.newBuilder().setId(info.getId()).build(), deleteBoolean);
        if (!deleteBoolean.awaitCompletion(10, TimeUnit.SECONDS)) {
            throw new TimeoutException();
        }
        assertNull(deleteBoolean.getError());
        assertEquals(1, deleteBoolean.getValues().get(0).getIsTrue());
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
