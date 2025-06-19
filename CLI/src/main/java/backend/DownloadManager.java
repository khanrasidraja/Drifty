package backend;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import support.Job;

public class DownloadManager {
    private final ExecutorService executor;

    public DownloadManager(int maxParallelDownloads) {
        // Max number of files to download in parallel
        this.executor = Executors.newFixedThreadPool(maxParallelDownloads);
    }

    public void downloadAll(List<Job> jobs) {
        for (Job job : jobs) {
            executor.submit(new FileDownloader(job)); // FileDownloader handles one file
        }
        executor.shutdown(); // Let all tasks finish before shutdown
    }
}
