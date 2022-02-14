package com.guohaohome.moviedb.sqlApiTest;


import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.sqlEntity.Info;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@FixMethodOrder(MethodSorters.JVM)
public class InfoMapperTester {
    @Autowired
    InfoMapper infoMapper;

    @Test
    public void testGetInfoByID() {
        Info info = infoMapper.getInfoByID("12345");
        assertEquals("photo/12345", info.getPhoto());
        assertEquals("review/12345", info.getReview());
        assertEquals("OST/12345", info.getOST());
        assertEquals("clips/12345", info.getClips());
        assertEquals("details/12345", info.getDetails());
        assertEquals(9.0, info.getIMDb(), 0.1);
        assertEquals(90, info.getTomatoes());
    }

    @Test
    public void testUpdateIMDbByID() {
        double currentIMDb = infoMapper.getInfoByID("12345").getIMDb();
        infoMapper.updateIMDbByID("12345", 9.8);
        double updatedIMDbByID = infoMapper.getInfoByID("12345").getIMDb();
        assertEquals(9.8, updatedIMDbByID, 0.1);
    }

    @Test
    public void testUpdateTomatoesByID() {
        int currentTomatoes = infoMapper.getInfoByID("12345").getTomatoes();
        infoMapper.updateTomatoesByID("12345", 95);
        int updatedTomatoes = infoMapper.getInfoByID("12345").getTomatoes();
        assertEquals(95, updatedTomatoes);
    }

    @Test
    public void testInsertInfo(){
        String id = "123456";
        String photo = "photo/123456";
        String review = "review/123456";
        String OST =  "OST/123456";
        String clips = "clips/123456";
        String details = "details/12345";
        double IMDb = 9.2;
        int Tomatoes = 92;
        Info info = new Info(id,photo,review,OST,clips,details,IMDb,Tomatoes);
        infoMapper.insertInfo(info);
        Info testInfo = infoMapper.getInfoByID("123456");
        assertEquals(photo, testInfo.getPhoto());
        assertEquals(review, testInfo.getReview());
        assertEquals(OST, testInfo.getOST());
        assertEquals(clips, testInfo.getClips());
        assertEquals(details, testInfo.getDetails());
        assertEquals(IMDb, info.getIMDb(), 0.1);
        assertEquals(Tomatoes, info.getTomatoes());

    }
}

