package org.newboo.mybatis.plugins;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }
        )
})
public class MonitorSqlIntercept implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        String sql = "unknown";
        if (invocation.getArgs() != null
                && invocation.getArgs().length > 0
                && invocation.getArgs()[0] instanceof MappedStatement) {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            sql = mappedStatement.getSqlSource().getBoundSql(invocation.getArgs().length > 1 ? invocation.getArgs()[1] : null).getSql();
        }
        long tsStart = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            System.out.println("run sql : [" + sql + "] use " + (System.currentTimeMillis() - tsStart) + " ms");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
