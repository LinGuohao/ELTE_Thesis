package com.guohaohome.moviedb.unitTest;

import com.guohaohome.moviedb.grpc.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class utilsTest {
    @Autowired
    Utils utils;

    @Test
    public void SHA256EncryptionTest() {
        String plain = "SHA256EncryptionTestPlain";
        String cipher = Utils.SHA256Encryption(plain);
        String cipher2 = Utils.SHA256Encryption(plain);
        assertEquals(cipher, cipher2);
    }

    @Test
    public void generateMusicInformationTest() {
        String musicPath = "12345/OST/Raindrops Keep Falling On My Head_B.J. Thomas.mp3";
        String[] musicInfo = utils.generateMusicInformation(musicPath);
        assertEquals(2, musicInfo.length);
        assertEquals("12345/OST/Raindrops Keep Falling On My Head", musicInfo[0]);
        assertEquals("B.J. Thomas", musicInfo[1]);

    }

    @Test
    public void generateIdTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String id = utils.generateId();
            assertFalse(hashMap.containsKey(id));
            hashMap.put(id, 1);
        }
    }

    @Test
    public void generateMusicFileNameTest() {
        String musicName = "Raindrops Keep Falling On My Head";
        String artist = "B.J. Thomas";
        String type = "mp3";
        String musicFileName = utils.generateMusicFileName(musicName, artist, type);
        assertEquals("Raindrops Keep Falling On My Head_B.J. Thomas.mp3", musicFileName);
    }
}
