package org.newboo.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.newboo.mybatis.daos.UserDao;
import org.newboo.mybatis.dos.UserDO;

import java.io.IOException;
import java.io.InputStream;

public class FirstDemo {

    /**
     * CREATE TABLE `user` (
     *   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
     *   `user_name` varchar(32) NOT NULL COMMENT '用户名',
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserDao userDao = session.getMapper(UserDao.class);
            UserDO userDO = userDao.selectByPrimaryKey(1L);
            System.out.println("find user id = " + userDO.getId() + " name = " + userDO.getUserName());
        }
    }
}
