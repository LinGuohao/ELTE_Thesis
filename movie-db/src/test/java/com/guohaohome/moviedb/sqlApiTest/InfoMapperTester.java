package com.guohaohome.moviedb.sqlApiTest;


import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.dao.MovieMapper;
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
        assertEquals("testGetInfoByID",info.getName());
        assertEquals(9.0, info.getIMDb(), 0.1);
        assertEquals(90, info.getTomatoes());
    }

    @Test
    public void testUpdateIMDbByID() {
        double currentIMDb = infoMapper.getInfoByID("12345678").getIMDb();
        infoMapper.updateIMDbByID("12345678", 9.8);
        double updatedIMDbByID = infoMapper.getInfoByID("12345678").getIMDb();
        assertEquals(9.8, updatedIMDbByID, 0.1);
    }

    @Test
    public void testUpdateTomatoesByID() {
        int currentTomatoes = infoMapper.getInfoByID("12345678").getTomatoes();
        infoMapper.updateTomatoesByID("12345678", 95);
        int updatedTomatoes = infoMapper.getInfoByID("12345678").getTomatoes();
        assertEquals(95, updatedTomatoes);
    }

    @Test
    public void testInsertInfo(){
        String id = "123456";
        String name = "infoMapper_testInsertInfo";
        double IMDb = 9.5;
        int Tomatoes = 93;
        Info info = new Info(id,name,IMDb,Tomatoes);
        infoMapper.insertInfo(info);
        Info testInfo = infoMapper.getInfoByID("123456");
        assertEquals(name,testInfo.getName());
        assertEquals(IMDb, info.getIMDb(), 0.1);
        assertEquals(Tomatoes, info.getTomatoes());
    }
    @Test
    public void testUpdateInfo()
    {
        String id = "1234567";
        String name = "testUpdateInfo";
        double IMDb = 9.3;
        int Tomatoes = 93;
        Info info = new Info(id,name,IMDb,Tomatoes);
        infoMapper.updateInfoByID(info,id);
        Info testInfo = infoMapper.getInfoByID("1234567");
        assertEquals(name,testInfo.getName());
        assertEquals(IMDb,testInfo.getIMDb(),0.1);
        assertEquals(Tomatoes,testInfo.getTomatoes());
    }
}

