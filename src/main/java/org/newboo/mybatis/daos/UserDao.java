package org.newboo.mybatis.daos;

import org.newboo.mybatis.dos.UserDO;

public interface UserDao {

    UserDO selectByPrimaryKey(Long id);

}
