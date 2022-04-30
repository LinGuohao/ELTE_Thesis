insert into Info(id,name,IMDb,Tomatoes) values('12345','testGetInfoByID',9.0,90);
insert into Info(id,name,IMDb,Tomatoes) values('12345678','testUpdateIMDbAndTomatoes',9.1,91);
insert into Info(id,name,IMDb,Tomatoes) values ('1234567','testUpdateInfo',9.1,92);
insert into Info(id,name,IMDb,Tomatoes) values ('2345678','grpc_upgradeByID',9.2,98);
insert into Info(id,name,IMDb,Tomatoes) values ('23456789','grpc_getInfoByID',9.1,97);
insert into Info(id,name,IMDb,Tomatoes) values ('testMovieId','testMovieName',9.1,97);
insert into Movie(id,name) values('testMovieId','testMovieName');
insert into Movie(id,name) values('1234567','testGetNameByID');
insert into Movie(id,name) values('12345','testGetIDByName');
insert into Movie(id,name) values('123456','testUpdateIMDbAndTomatoes');
insert into Movie(id,name) values('2345678','grpc_upgradeByID');
insert into Movie(id,name) values('testUpdateNameByIDID','testUpdateNameByIDName');
insert into Movie_lines(id,sentence,author,line_id) values ('12345','testSentence','testAuthor','testLine_id');
insert into Movie_lines(id,sentence,author,line_id) values ('12345','testSentence2','testAuthor2','testLine_id2');
insert into Movie_user(username,password,fullname,roles)values('testUserName@test.com','testPassword','testFullname'
                        ,'testRoles');
insert into user_comment(commentID,username,movieID,content) values('testCommentID','testUserName','12345','testContent');
insert into user_comment(commentID,username,movieID,content) values('testCommentID2','testUserName2','12345','testContent2');
insert into user_comment(commentID,username,movieID,content) values('testCommentID3','testUserName2','123456','testContent3');



