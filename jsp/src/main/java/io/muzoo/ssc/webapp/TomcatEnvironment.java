package io.muzoo.ssc.webapp;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TomcatEnvironment {

    private static final boolean IS_RUNNING_FROM_JAR_FILE;

    private static final File WORK_DIR;

    private static final File DOC_BASE;

    static {
        IS_RUNNING_FROM_JAR_FILE = getJarFilePath() != null;

        if (IS_RUNNING_FROM_JAR_FILE) {
            String tempDir = System.getProperty("java.io.tmpdir");
            WORK_DIR = new File(tempDir, "tomcat.tmpdir");
            FileUtils.deleteQuietly(WORK_DIR);
            WORK_DIR.mkdirs();

            DOC_BASE = new File(WORK_DIR, "webapp");
            FileUtils.deleteQuietly(DOC_BASE);
            DOC_BASE.mkdirs();
        } else {
            WORK_DIR = new File("target/tomcat.tmpdir");
            FileUtils.deleteQuietly(WORK_DIR);
            WORK_DIR.mkdirs();

            DOC_BASE = new File("src/main/webapp/");
        }
    }

    private static void extract(String fileZip, String destDir) {
        File dstDir = new File(destDir);
        try (
                FileInputStream fin = new FileInputStream(fileZip);
                ZipInputStream zis = new ZipInputStream(fin);
        ) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = new File(dstDir, zipEntry.getName());
                if (zipEntry.getName().startsWith("webapp")) {
                    if (zipEntry.isDirectory()) {
                        newFile.mkdirs();
                    } else {
                        try (FileOutputStream fos = FileUtils.openOutputStream(newFile)) {
                            IOUtils.copyLarge(zis, fos);
                        } catch (IOException ex) {
                            System.out.println("ERROR: " + ex.getMessage());
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        } catch (IOException oex) {
            System.out.println("ERROR: " + oex.getMessage());
        }
    }

    private static String getJarFilePath() {
        String className = TomcatEnvironment.class.getName().replace('.', '/');
        String classJar = TomcatEnvironment.class.getResource("/" + className + ".class").toString();
        if (classJar.startsWith("jar:")) {
            String jarPath = classJar.substring(4, classJar.lastIndexOf("!"));
            if (jarPath.startsWith("file:")) {
                jarPath = jarPath.substring(5);
            }
            return jarPath;
        }
        return null;
    }

    public static void init() {
        if (IS_RUNNING_FROM_JAR_FILE) {
            String jarPath = getJarFilePath();
            extract(jarPath, DOC_BASE.getParentFile().getAbsolutePath());
            System.out.println("Extracted to: " + DOC_BASE.getAbsolutePath());
            System.out.println("Work dir: " + WORK_DIR.getAbsolutePath());
        }
    }

    public static File getDocBase() {
        return DOC_BASE;
    }

    public static File getWorkDir() {
        return WORK_DIR;
    }
}
