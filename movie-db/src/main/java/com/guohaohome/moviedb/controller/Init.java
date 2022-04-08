package com.guohaohome.moviedb.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.guohaohome.moviedb.dao.CommentMapper;
import com.guohaohome.moviedb.dao.InfoMapper;
import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.dao.UserLikeMapper;
import com.guohaohome.moviedb.ossClient.OSSConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConditionalOnExpression("'${lifeCycle.stage}'.equals('dev')")
public class Init {
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    UserLikeMapper userLikeMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    OSSConfiguration ossConfiguration;
    @Autowired
    OSS ossClient;
    @Autowired
    DataSource dataSource;
    @PostConstruct
    public void initOSSandDatabase() throws Exception {
        try {
            System.out.println(dataSource.getConnection());
            List<String> movieList = movieMapper.getAllID();
            HashMap<String, Boolean> movieJudgeMap = new HashMap<String, Boolean>();
            for (String id : movieList) {
                movieJudgeMap.put(id, false);
            }
            ObjectListing objectListing = ossClient.listObjects(ossConfiguration.getBucketName());
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                String link = s.getKey();
                String ossId = s.getKey().split("/")[0];
                if (movieJudgeMap.containsKey(ossId)) {
                    movieJudgeMap.put(ossId, true);
                } else {
                    ossClient.deleteObject(ossConfiguration.getBucketName(), s.getKey());
                    System.out.println("OSSFile: "+s.getKey()+ " has deleted");
                }
            }
            for (Map.Entry<String, Boolean> entry : movieJudgeMap.entrySet()) {
                if (!entry.getValue()) {
                    movieMapper.deleteMovieByID(entry.getKey());
                    infoMapper.deleteInfoByID(entry.getKey());
                    userLikeMapper.deleteUserLikeByID(entry.getKey());
                    commentMapper.deleteCommentByMovieId(entry.getKey());
                    System.out.println("DataBase: Movie:" + entry.getKey()+" has deleted");
                }
            }
            System.out.println("OSS and database cleanup succeeded");
        } catch (SQLException exception) {
            System.out.println("Database initialization failed, unable to connect to database");
            System.exit(1);
        }
    }
}
