package org.newboo.queue;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class LogEventHandler implements EventHandler<LogEvent>, WorkHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent logEvent, long l, boolean b) throws Exception {

    }

    @Override
    public void onEvent(LogEvent logEvent) throws Exception {

    }
}
