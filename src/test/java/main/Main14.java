package main;

import org.reflections.Reflections;
import wf.utils.java.file.utils.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main14 {


    public static void main(String[] args) throws IOException {
        System.out.println(ResourceUtils.getResourceFiles("test"));
        try {
            System.out.println(new File(Main14.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new File("").getAbsolutePath());

    }



}
