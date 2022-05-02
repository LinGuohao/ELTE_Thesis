insert into Info(id,name,IMDb,Tomatoes) values('testMovieId','testMovieName',9.0,90);
insert into Info(id,name,IMDb,Tomatoes) values('testMovieId2','testMovieName2',9.1,91);
insert into Movie(id,name) values('testMovieId','testMovieName');
insert into Movie(id,name) values('testMovieId2','testMovieName2');
insert into Movie_lines(id,sentence,author,line_id) values ('testMovieId','testSentence','testAuthor','testLine_id');
insert into Movie_lines(id,sentence,author,line_id) values ('testMovieId','testSentence2','testAuthor2','testLine_id2');
insert into Movie_user(username,password,fullname,roles)values('testUserName@test.com','testPassword','testFullname'
                        ,'testRoles');
insert into user_comment(commentID,username,movieID,content) values('testCommentID','testUserName','12345','testContent');
insert into user_comment(commentID,username,movieID,content) values('testCommentID2','testUserName2','12345','testContent2');
insert into user_comment(commentID,username,movieID,content) values('testCommentID3','testUserName2','123456','testContent3');
insert into user_like(username,id) values ('testUserName','testId');
insert into user_like(username,id) values ('testUserName','testId2');



