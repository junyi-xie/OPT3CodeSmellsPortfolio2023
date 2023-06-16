package digimonbattlesimulator.utils.audio;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class KuruKuru {

    public static void play() {
        List<String> kuruFiles = getKuruAudioFiles("/digimonbattlesimulator/audio");
        String randomKuruAudioFile = getRandomKuruElement(kuruFiles);
        String audioPath = Objects.requireNonNull(KuruKuru.class.getResource("/digimonbattlesimulator/audio/" + randomKuruAudioFile)).toString();
        Media sound = new Media(audioPath);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
        mediaPlayer.setOnPlaying(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    private static String getRandomKuruElement(List<String> kuru) {
        Random random = new Random();
        int index = random.nextInt(kuru.size());
        return kuru.get(index);
    }

    private static List<String> getKuruAudioFiles(String folderPath) {
        List<String> audioFiles = new ArrayList<>();
        File folder = new File(Objects.requireNonNull(KuruKuru.class.getResource(folderPath)).getFile());
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    audioFiles.add(file.getName());
                }
            }
        }
        return audioFiles;
    }
}