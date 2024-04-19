package com.lo.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author RujiangLiu
 * @date 2024/4/2
 */
public class ZipFileTest {

    public static void main(String[] args) {

    }


    @Test
    public void zipFolderCopy() {
        // 源文件夹路径，确保这是文件夹本身的路径
        String sourceFolderPath = "D:\\TA-1文件\\文件夹";
        File sourceFolder = new File(sourceFolderPath);

        String targetPath = "D:\\TA-1文件\\文件夹2";

        // 目标压缩包路径（与源文件夹同级）
        String targetZipPath = "D:\\TA-1文件\\文件夹2.zip";
        File targetZipFile = new File(targetZipPath);

        System.out.println(targetZipFile.getPath());
        System.out.println(targetZipFile.getAbsolutePath());
        System.out.println(targetZipFile.getName());

        File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            if (targetFile.mkdirs()){
                System.out.println("新建临时目标目录");
            }
        }
        File[] files = sourceFolder.listFiles();
        assert files != null;
        for (File file : files) {
            FileUtil.copyFile(file, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        }
        // 压缩
        ZipUtil.zip(targetPath, targetZipPath, StandardCharsets.UTF_8, true);
        // 删除文件夹
        FileUtil.del(targetFile);
        System.out.println("文件夹已成功压缩为 ZIP 文件：" + targetZipPath);
    }

    @Test
    public void zipFileCopy() {
        // 源文件夹路径，确保这是文件夹本身的路径
        String sourceFilePath = "D:\\TA-1文件\\文件夹\\TA-1文本.txt";
        File sourceFile = new File(sourceFilePath);

        String targetPath = "D:\\TA-1文件\\文件夹2";

        // 目标压缩包路径（与源文件夹同级）
        String targetZipPath = "D:\\TA-1文件\\文件夹2.zip";

        File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        ZipUtil.zip(sourceFilePath, targetPath + "\\TA-1文本.zip", true);

        System.out.println("文件夹已成功压缩为 ZIP 文件：" + targetZipPath);
    }

    @Test
    public void getName() {
        String targetZipPath = "D:\\TA-1文件\\文件夹2.zip";
        String mainName = FileUtil.mainName(targetZipPath);
        String name = FileUtil.getName(targetZipPath);
        String extName = FileUtil.extName(targetZipPath);
        // 文件夹2
        System.out.println(mainName);
        // 文件夹2.zip
        System.out.println(name);
        // zip
        System.out.println(extName);
        // 文件夹2
        System.out.println(FileUtil.getPrefix(targetZipPath));
        // zip
        System.out.println(FileUtil.getSuffix(targetZipPath));
        // D:/TA-1文件/文件夹2.zip
        System.out.println(FileUtil.getAbsolutePath(targetZipPath));
    }

    @Test
    @SneakyThrows
    public void zipFolderCopyEntry() {
        // 源文件夹路径，这个文件夹将被打包到 hello 文件夹内
        String sourceFolderPath = "D:\\TA-1文件\\文件夹";
        File sourceFolder = new File(sourceFolderPath);

        // 目标压缩包路径，例如 hello.zip
        String targetZipPath = "D:\\TA-1文件\\文件夹2.zip";
        String mainName = FileUtil.mainName(targetZipPath);
        OutputStream os = new FileOutputStream(targetZipPath);
        ZipOutputStream zos = new ZipOutputStream(os);

        // 创建一个 ZipEntry，代表压缩包内的 hello 文件夹
        ZipEntry helloFolderEntry = new ZipEntry(mainName + File.separator);
        zos.putNextEntry(helloFolderEntry);
        zos.closeEntry();

        // 遍历源文件夹内的所有文件和子文件夹，并将它们添加到压缩包中
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                // 构建文件在压缩包中的路径，相对于 hello 文件夹
                String entryPath = mainName + File.separator + file.getName();
                // 如果是目录，则创建一个目录条目
                if (file.isDirectory()) {
                    ZipEntry dirEntry = new ZipEntry(entryPath);
                    zos.putNextEntry(dirEntry);
                    zos.closeEntry();
                } else {
                    // 如果是文件，则创建一个文件条目并写入文件内容
                    ZipEntry fileEntry = new ZipEntry(entryPath);
                    zos.putNextEntry(fileEntry);
                    zos.write(FileUtil.readBytes(file));
                    zos.closeEntry();
                }
            }
        }
        System.out.println("文件夹已成功压缩为 ZIP 文件：" + targetZipPath);
    }

    @SneakyThrows
    @Test
    public void zipEntry(){
        // 压缩包内的文件夹名称
        String folderName = "D:\\TA-1文件\\文件夹";
        // 压缩文件路径
        String targetZipPath = "D:\\TA-1文件\\文件夹2.zip";
        String mainName = FileUtil.mainName(targetZipPath);
        System.out.println(mainName);
        String zipFilePath = FileUtil.getName(targetZipPath);
        System.out.println(zipFilePath);

        FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        // 假设你有一个文件列表，每个文件都用File对象表示
        File[] filesToZip = new File(folderName).listFiles();

        assert filesToZip != null;
        for (File file : filesToZip) {
            addToZip(file, mainName, zos);
        }

        zos.close();
        fos.close();
        System.out.println("Zip file created successfully!");
    }

    private static void addToZip(File file, String folderName, ZipOutputStream zos) throws IOException {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            assert children != null;
            for (File childFile : children) {
                addToZip(childFile, folderName + File.separator + file.getName(), zos);
            }
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(folderName + File.separator + file.getName());
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
