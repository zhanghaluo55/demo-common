package com.hongpro.demo.common.spi.dubbospi;

import org.apache.dubbo.common.extension.ExtensionLoader;

import java.security.cert.Extension;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/27
 * @Version:
 */
public class SPIMain {
    public static void main(String[] args) {
        ExtensionLoader<Command> loader = ExtensionLoader.getExtensionLoader(Command.class);
        Set<String> supportedExtensions = loader.getSupportedExtensions();
        for (String extension : supportedExtensions) {
            loader.getExtension(extension).execute();
        }
    }
}
