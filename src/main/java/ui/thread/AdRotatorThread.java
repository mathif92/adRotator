package ui.thread;

import constants.FileConstants;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by mathias on 04/03/17.
 */
public class AdRotatorThread implements Runnable {

    JPanel adPanel;
    ImageIcon currentImage;
    String applicationPath;
    long currentDelay;
    File[] companies;


    public AdRotatorThread(JPanel jPanel, String appPath) {
        adPanel = jPanel;
        applicationPath = appPath;
        File appDirectory = new File(applicationPath);
        if(appDirectory.isDirectory()) {
            companies = appDirectory.listFiles();
        }
    }

    public void run() {
        if(companies.length > 0) {
            while(true) {
                for (File companyDir : companies) {
                    if(companyDir.isDirectory()) {
                        currentDelay = readDelay(companyDir);
                        for (String companyFile : companyDir.list()) {
                            if(!companyFile.contains(FileConstants.DELAY)) {
                                String fileExtension = FilenameUtils.getExtension(companyFile);
                                if(isAudioFile(fileExtension)) {
                                    File audioFile = new File(companyDir.getAbsolutePath().concat("/" + companyFile));
                                    Media audio = new Media(audioFile.toURI().toString());
                                    final MediaPlayer mediaPlayer = new MediaPlayer(audio);
                                    mediaPlayer.setOnEndOfMedia(new Runnable() {
                                        public void run() {
                                            mediaPlayer.seek(Duration.ZERO);
                                        }
                                    });
                                    mediaPlayer.play();
                                } else {
                                    String imagePath = companyDir.getAbsolutePath().concat("/" + companyFile);
                                    ImageIcon icon = new ImageIcon(imagePath);
                                    JLabel label = new JLabel();
                                    label.setIcon(icon);
                                    label.setVisible(true);
                                    adPanel.add(label);
                                    try {
                                        Thread.sleep(currentDelay);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isAudioFile(String fileExtension) {
        return (fileExtension.equals(FileConstants.MP3_EXTENSION)) || (fileExtension.equals(FileConstants.WAV_EXTENSION));
    }

    private long readDelay(File companyDir) {
        File delayFile = new File(companyDir.getAbsolutePath() + FileConstants.DELAY);
        try {
            InputStream inputStream = new FileInputStream(delayFile);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            long delay = FileConstants.DEFAULT_DELAY;
            while((line = bufferedReader.readLine()) != null) {
                String quantity = line.split(" ")[0];
                String unit = line.split(" ")[1];
                delay = Long.parseLong(quantity);
                if(unit.equals(FileConstants.UNIT_SECONDS)) {
                    delay *= 1000;
                } else if(unit.equals(FileConstants.UNIT_MINUTES)) {
                    delay *= 1000 * 60;
                }
            }
            return delay;
        } catch (Exception e) {
            return FileConstants.DEFAULT_DELAY;
        }
    }

}
