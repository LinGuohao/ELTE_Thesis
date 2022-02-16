package com.guohaohome.moviedb.grpcTest;



import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.grpc.MoviedbService;
import com.guohaohome.moviedb.proto.*;
import com.guohaohome.moviedb.sqlEntity.Info;
import io.grpc.internal.testing.StreamRecorder;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest

@MapperScan("com/guohaohome/moviedb/dao")
public class MoviedbServiceUnitTests {
    @Autowired
    private MoviedbService moviedbService;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Test
    public void testGetAllID() throws Exception{
        StreamRecorder<AllMovieIDListResponse> responseObserver = StreamRecorder.create();
        moviedbService.getAllID(null,responseObserver);
        if(!responseObserver.awaitCompletion(10, TimeUnit.SECONDS)){
            throw new TimeoutException();
        }
        assertNull(responseObserver.getError());
        List<AllMovieIDListResponse> result = responseObserver.getValues();
        assertEquals(1,result.size());
        assertEquals(AllMovieIDListResponse.newBuilder()
                        .addReply(0, MovieID.newBuilder().setId("1234567").build())
                        .addReply(1, MovieID.newBuilder().setId("12345"))
                        .addReply(2,MovieID.newBuilder().setId("123456"))
                        .addReply(3,MovieID.newBuilder().setId("2345678")).build()
                ,result.get(0));
    }
    @Test
    public void testGetInfoByID() throws Exception{
        StreamRecorder<InfoResponse> responseObserver = StreamRecorder.create();
        moviedbService.getInfoByID(InfoByIDRequest.newBuilder().setId("23456789").build(), responseObserver);
        if(!responseObserver.awaitCompletion(10, TimeUnit.SECONDS)){
            throw new TimeoutException();
        }
        assertNull(responseObserver.getError());
        InfoResponse infoResponse = responseObserver.getValues().get(0);
        assertEquals("23456789",infoResponse.getId());
        assertEquals("Grpc_getInfoByID", infoResponse.getName());
        assertEquals(9.1, infoResponse.getIMDb(),0.1);
        assertEquals(97,infoResponse.getTomatoes());

    }
    @Test
    public void testUpdateByID() throws Exception{
        Info info = new Info("2345678","Grpc_upgraded_ByID",9.5,100);
        moviedbService.updateByID(InfoRequest.newBuilder().setId(info.getId()).setName(info.getName())
                .setIMDb(info.getIMDb()).setTomatoes(info.getTomatoes()).build(),null);
        Info updatedInfo = infoMapper.getInfoByID("2345678");
        TimeUnit.SECONDS.sleep(1);
        assertEquals("Grpc_upgraded_ByID",updatedInfo.getName());
        assertEquals("Grpc_upgraded_ByID",movieMapper.getNameByID("2345678"));
        assertEquals(9.5, updatedInfo.getIMDb(),0.1);
        assertEquals(100, updatedInfo.getTomatoes());
    }

    @Test
    public void insertMovie() throws Exception{
        int num = movieMapper.getAllID().size();
        Info info = new Info("234567890_insert" , "Grpc_Insert", 9.8,100);
        moviedbService.insertMovie(InfoRequest.newBuilder().setId(info.getId()).setName(info.getName())
                .setIMDb(info.getIMDb()).setTomatoes(info.getTomatoes()).build(),null);
        TimeUnit.SECONDS.sleep(1);
        int newNum = movieMapper.getAllID().size();
        assertEquals(num+1,newNum);
        assertEquals("Grpc_Insert",infoMapper.getInfoByID("234567890_insert").getName());
        assertEquals("Grpc_Insert" , movieMapper.getNameByID("234567890_insert"));
    }
}
