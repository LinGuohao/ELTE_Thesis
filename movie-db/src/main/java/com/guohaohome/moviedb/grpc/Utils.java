package com.guohaohome.moviedb.grpc;

import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

@Component
public class Utils {
    public static File base64ToFile(String base64) throws Exception {
        if (base64.contains("data:image")||base64.contains("data:audio")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
        base64 = base64.replace("\r\n", "");
        //创建文件目录
        String prefix = ".tmp";
        File file = File.createTempFile(UUID.randomUUID().toString(), prefix);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {

            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(base64);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
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

    public String generateId() {
        long time = Calendar.getInstance().getTimeInMillis();
        Random r = new Random();
        r.setSeed(time);
        int randInt = r.nextInt(100000);
        return String.valueOf(time) + randInt;
    }

    public String generateFileName(String FileSuffix){
        return generateId() + "." + FileSuffix;
    }

    public String generateMusicFileName(String musicName, String artist, String type){
        return musicName + "_" + artist + "." + type;

    }
}
