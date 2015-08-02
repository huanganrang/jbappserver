 insert into mysql.user(Host,User,Password) values("localhost","sypro",password("123456"));
 flush privileges;
 create database syproDB;
 grant all privileges on syproDB.* to phplamp@localhost identified by '123456';
 flush privileges;