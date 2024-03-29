package com.guohaohome.moviedb.unitTest.sqlAPITest;


import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.sqlEntity.Info;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@FixMethodOrder(MethodSorters.JVM)
@DirtiesContext
public class InfoMapperTests {
    @Autowired
    InfoMapper infoMapper;


    @Test
    @DirtiesContext
    public void testGetInfoByID() {
        Info info = infoMapper.getInfoByID("testMovieId");
        assertEquals("testMovieId", info.getId());
        assertEquals("testMovieName", info.getName());
        assertEquals(9.0, info.getIMDb(), 0.1);
        assertEquals(90, info.getTomatoes());
    }

    @Test
    @DirtiesContext
    public void testInsertInfoAndDeleteInfoByID() {
        Info info = new Info("testInsertInfoAndDeleteInfoByIDID", "testInsertInfoAndDeleteInfoByIDName"
                , 9.2, 92);
        infoMapper.insertInfo(info);
        info = infoMapper.getInfoByID("testInsertInfoAndDeleteInfoByIDID");
        assertEquals("testInsertInfoAndDeleteInfoByIDID", info.getId());
        assertEquals("testInsertInfoAndDeleteInfoByIDName", info.getName());
        assertEquals(9.2, info.getIMDb(), 0.1);
        assertEquals(92, info.getTomatoes());
        infoMapper.deleteInfoByID("testInsertInfoAndDeleteInfoByIDID");
        info = infoMapper.getInfoByID("testInsertInfoAndDeleteInfoByIDID");
        assertNull(info);
    }

    @Test
    @DirtiesContext
    public void testUpdateIMDbByID() {
        double currentIMDb = infoMapper.getInfoByID("testMovieId").getIMDb();
        double updateIMDb = 9.1;
        assertNotEquals(updateIMDb, currentIMDb);
        infoMapper.updateIMDbByID("testMovieId", updateIMDb);
        assertEquals(updateIMDb, infoMapper.getInfoByID("testMovieId").getIMDb(), 0.1);
        infoMapper.updateIMDbByID("testMovieId", currentIMDb);
    }

    @Test
    @DirtiesContext
    public void testUpdateTomatoesByID() {
        int currentTomatoes = infoMapper.getInfoByID("testMovieId").getTomatoes();
        int updateTomatoes = 91;
        assertNotEquals(currentTomatoes, updateTomatoes);
        infoMapper.updateTomatoesByID("testMovieId", updateTomatoes);
        assertEquals(updateTomatoes, infoMapper.getInfoByID("testMovieId").getTomatoes());
        infoMapper.updateTomatoesByID("testMovieId", currentTomatoes);
    }

    @Test
    @DirtiesContext
    public void testUpdateInfoByID() {
        Info currentInfo = infoMapper.getInfoByID("testMovieId");
        Info newInfo = new Info(currentInfo.getId(), "testUpdateInfoByIDName3"
                , 9.4, 94);
        infoMapper.updateInfoByID(newInfo, "testMovieId");
        Info updateInfo = infoMapper.getInfoByID(currentInfo.getId());
        assertEquals(updateInfo.getId(), newInfo.getId());
        assertEquals(updateInfo.getName(), newInfo.getName());
        assertEquals(updateInfo.getTomatoes(), newInfo.getTomatoes());
        assertEquals(updateInfo.getIMDb(), newInfo.getIMDb(), 0.1);
        infoMapper.updateInfoByID(currentInfo, currentInfo.getId());

    }

}

