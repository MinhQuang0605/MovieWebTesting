package utils;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ScreenshotCleaner {
    public static void deleteEmptyScreenShotFolder() {
        String rootPath = System.getProperty("user.dir") + "/test-output/screenshots/";
        File rootDir = new File(rootPath);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            return;
        }

        // 2 ngay truoc
//        long now = System.currentTimeMillis();
//        long twoDaysAgo = now - TimeUnit.DAYS.toMillis(2);

        File[] directories = rootDir.listFiles(File::isDirectory);

        if (directories == null) {
            return;
        }

        for (File dir : directories) {
            // Kiem tra thu muc rong
            File[] filesInside = dir.listFiles();

            boolean isEmpty = filesInside == null || filesInside.length == 0;
           // boolean isOlderThan2Days = dir.lastModified() < twoDaysAgo;

            //if (isEmpty && isOlderThan2Days)
            if(isEmpty){
                boolean deleted = dir.delete();
                if (deleted) {
                    System.out.println("Deleted empty folder: " + dir.getAbsolutePath());
                } else {
                    System.out.println("Failed to delete folder: " + dir.getAbsolutePath());
                }
            }
        }
    }

    public static void deleteOldScreenshotFolders() {
        String rootPath = System.getProperty("user.dir") + "/test-output/screenshots/";
        File rootDir = new File(rootPath);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            return;
        }

        long now = System.currentTimeMillis();
        long sevenDaysAgo = now - TimeUnit.DAYS.toMillis(7);

        File[] directories = rootDir.listFiles(File::isDirectory);
        if (directories == null) {
            return;
        }

        for (File dir : directories) {
            if (isAllFilesOlderThan(dir, sevenDaysAgo)) {
                deleteFile(dir);
                System.out.println("Deleted old folder: " + dir.getAbsolutePath());
            }
        }
    }

    // Kiem tra toan bo file
    private static boolean isAllFilesOlderThan(File dir, long timeLimit) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false; // khong xu ly thu muc rong
        }

        for (File file : files) {
            if (file.isDirectory()) {
                if (!isAllFilesOlderThan(file, timeLimit)) {
                    return false;
                }
            } else {
                if (file.lastModified() >= timeLimit) {
                    return false;
                }
            }
        }
        return true;
    }

    // Xoa thu muc
    private static void deleteFile(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                deleteFile(f);
            }
        }
        file.delete();
    }
}
