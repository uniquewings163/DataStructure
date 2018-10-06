package utils;

import org.slf4j.Logger;

public class LoggerFactory {

    //do a wrap for divide project interface for a impl
    public static Logger getLogger(Class<?> clazz)
    {
        return org.slf4j.LoggerFactory.getLogger(clazz);
    }
}
