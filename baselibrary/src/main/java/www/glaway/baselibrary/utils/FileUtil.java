package www.glaway.baselibrary.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;

            }
        }
        return true;
    }

    /**
     * 删除文件
     * @param path
     */
    public static void fileDelete(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 判定文件是否存在，存在则删除重建，不存在则新建
     * @param dirPath
     * @return
     */
    public static Boolean fileisExit(String dirPath) {
        File file = new File(dirPath);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
