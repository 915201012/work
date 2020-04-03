package com.demo.io;

import java.io.InputStream;

/**s
 * @author user
 */
public class Resources {

    public static InputStream getResourceAsStream(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }

}
