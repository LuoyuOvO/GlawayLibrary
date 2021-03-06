package www.glaway.baselibrary.utils;

import android.os.Environment;

import com.blankj.utilcode.util.FileIOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 文件解压，压缩
 */
public class ZipUtil {

    //压缩缓存路径
    public static final String EXPORTFILEPATH = Environment.getExternalStorageDirectory() + "/AMS/EXPORT/CACHE/";
    //解压缓存路径
    public static final String zippath = Environment.getExternalStorageDirectory() + "/AMS/IMPORT/CACHE";
    //导出文件路径和名称
    public static final String EXPORTPATH = Environment.getExternalStorageDirectory().getPath() + "/AMS/EXPORT/data.zip";


    /**
     * 解压文件，需要选择一个zip文件
     *
     * @param file
     * @throws Exception
     */
    public static HashMap<String, String> readFile(String file) throws Exception {
        HashMap<String, String> data = new HashMap<>();
        //截取路径的文件名 res
        String fileName = file.substring(file.length() - 9, file.length() - 4);
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
                //Do nothing
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(zf.getInputStream(ze)));
                String line;
                while ((line = br.readLine()) != null) {
//                    Logger.d(line);
                    stringBuilder.append(line);
                }
                br.close();
                data.put(ze.getName(), stringBuilder.toString());
            }
        }
        zin.closeEntry();
        return data;
    }

    /**
     * 写入文件，文件名称和数据
     * @param filename
     * @param data
     */
    public static void writeFile(String filename,String data){
        String filePath = EXPORTFILEPATH+filename+".json";
        FileUtil.fileDelete(filePath);
        File file = new File(filePath);
        FileIOUtils.writeFileFromString(file,data);
    }


    /**
     * 压缩文件，根据上面的路径压缩
     * @throws Exception
     */
    public static void zip() throws Exception {
        //提供了一个数据项压缩成一个ZIP归档输出流
        ZipOutputStream out = null;
        try {
            File outFile = new File(EXPORTPATH);//源文件或者目录
            File fileOrDirectory = new File(EXPORTFILEPATH);//压缩文件路径
            out = new ZipOutputStream(new FileOutputStream(outFile));
            //如果此文件是一个文件，否则为false。
            if (fileOrDirectory.isFile()) {
                zipFileOrDirectory(out, fileOrDirectory, "");
            } else {
                //返回一个文件或空阵列。
                File[] entries = fileOrDirectory.listFiles();
                for (int i = 0; i < entries.length; i++) {
                    // 递归压缩，更新curPaths
                    zipFileOrDirectory(out, entries[i], "");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //关闭输出流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            }
        }
    }

    private static void zipFileOrDirectory(ZipOutputStream out, File fileOrDirectory, String curPath) throws IOException {
        //从文件中读取字节的输入流
        FileInputStream in = null;
        try {
            //如果此文件是一个目录，否则返回false。
            if (!fileOrDirectory.isDirectory()) {
                // 压缩文件
                byte[] buffer = new byte[4096];
                int bytes_read;
                in = new FileInputStream(fileOrDirectory);
                //实例代表一个条目内的ZIP归档
                ZipEntry entry = new ZipEntry(curPath + fileOrDirectory.getName());
                //条目的信息写入底层流
                out.putNextEntry(entry);
                while ((bytes_read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytes_read);
                }
                out.closeEntry();
            } else {
                // 压缩目录
                File[] entries = fileOrDirectory.listFiles();
                for (int i = 0; i < entries.length; i++) {
                    // 递归压缩，更新curPaths
                    zipFileOrDirectory(out, entries[i], curPath + fileOrDirectory.getName() + "/");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
