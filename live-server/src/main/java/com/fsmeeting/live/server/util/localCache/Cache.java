package com.fsmeeting.live.server.util.localCache;


public interface Cache {
    
    int getSize();

    void putObject(Object key, Object value);
    
    void putObject(Object key, Object value, int seconds);

    Object getObject(Object key);

    Object removeObject(Object key);

    void clear(String id);

}
