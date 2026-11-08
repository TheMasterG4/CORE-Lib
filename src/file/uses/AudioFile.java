package file.uses;

import file.BinaryFile;
import file.pathing.FilePathBuilder;
import org.jetbrains.annotations.NotNull;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.Map;

public class AudioFile extends BinaryFile {
    private static final Map<String, String> codec = Map.ofEntries(
            Map.entry("mp3", "libmp3lame"),
            Map.entry("ogg", "libvorbis"),
            Map.entry("oga", "libvorbis"),
            Map.entry("opus", "libopus"),
            Map.entry("wav", "pcm_s16le"),
            Map.entry("flac", "flac"),
            Map.entry("aac", "aac"),
            Map.entry("m4a", "aac"),
            Map.entry("wma", "wmav2"),
            Map.entry("aiff", "pcm_s16be"),
            Map.entry("aif", "pcm_s16be"),
            Map.entry("ac3", "ac3"),
            Map.entry("amr", "libopencore_amrnb"),
            Map.entry("au", "pcm_s16be"),
            Map.entry("caf", "pcm_s16le"),
            Map.entry("alac", "alac"),
            Map.entry("ape", "ape"),
            Map.entry("wv", "wavpack"),
            Map.entry("tta", "tta")
    );
    public final MultimediaObject multimedia;


    public AudioFile(@NotNull String pathname) {
        super(pathname);
        multimedia = new MultimediaObject(this);
    }

    public AudioFile(String parent, @NotNull String child) {
        super(parent, child);
        multimedia = new MultimediaObject(this);
    }

    public AudioFile(File parent, @NotNull String child) {
        super(parent, child);
        multimedia = new MultimediaObject(this);
    }

    public AudioFile(@NotNull URI uri) {
        super(uri);
        multimedia = new MultimediaObject(this);
    }
    public AudioFile(@NotNull String... path){
        super(FilePathBuilder.bp(path));
        multimedia = new MultimediaObject(this);
    }
    public AudioFile (@NotNull StringBuilder filePathBuilderStringBuilder){
        super(FilePathBuilder.bp(filePathBuilderStringBuilder.toString()));
        multimedia = new MultimediaObject(this);
    }

    public AudioFile(@NotNull File f){
        super(f.getAbsolutePath());
        multimedia = new MultimediaObject(this);
    }



    public boolean isMono(){
        try {
            return multimedia.getInfo().getAudio().getChannels() == 1;
        }catch (Exception ignored){
            return false;
        }
    }
    public boolean isStereo(){
        try {
            return multimedia.getInfo().getAudio().getChannels() == 2;
        }catch (Exception ignored){
            return false;
        }
    }
    public boolean is51Surround(){
        try {
            return multimedia.getInfo().getAudio().getChannels() == 6;
        }catch (Exception ignored){
            return false;
        }
    }
    public boolean is71Surround(){
        try {
            return multimedia.getInfo().getAudio().getChannels() == 8;
        }catch (Exception ignored){
            return false;
        }
    }
    public boolean is714Surround(){
        try {
            return multimedia.getInfo().getAudio().getChannels() == 12;
        }catch (Exception ignored){
            return false;
        }
    }

    public int bitrate(){
        try {
            return multimedia.getInfo().getAudio().getBitRate();
        } catch (Exception e) {
            return -1;
        }
    }
    public long duration(){
        try {
            return multimedia.getInfo().getDuration();
        } catch (Exception e) {
            return -1;
        }
    }

    public void setNumberOfChannels(int x){
        if(!isMono()) {
            AudioFile output = null;

            try {
                output = new AudioFile(File.createTempFile("output", "."+extension()));
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec(multimedia.getInfo().getAudio().getDecoder());
                audio.setChannels(x);
                EncodingAttributes attributes = new EncodingAttributes();
                attributes.setOutputFormat(extension());
                attributes.setAudioAttributes(audio);
                new Encoder().encode(multimedia, output, attributes);

                content =  Files.readAllBytes(output.toPath());

            }catch (Exception ignored){}
            if(output!=null){
                output.delete();
            }
        }
    }

    public void toMono(){
        if(!isMono()) {
            AudioFile output = null;

            try {
                output = new AudioFile(File.createTempFile("output", "."+extension()));
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec(multimedia.getInfo().getAudio().getDecoder());
                audio.setChannels(1);
                EncodingAttributes attributes = new EncodingAttributes();
                attributes.setOutputFormat(extension());
                attributes.setAudioAttributes(audio);
                new Encoder().encode(multimedia, output, attributes);

                content =  Files.readAllBytes(output.toPath());

            }catch (Exception ignored){}
            if(output!=null){
                output.delete();
            }
        }
    }

    public void toStereo(){
        if(!isStereo()) {
            AudioFile output = null;

            try {
                output = new AudioFile(File.createTempFile("output", "."+extension()));
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec(multimedia.getInfo().getAudio().getDecoder());
                audio.setChannels(2);
                EncodingAttributes attributes = new EncodingAttributes();
                attributes.setOutputFormat(extension());
                attributes.setAudioAttributes(audio);
                new Encoder().encode(multimedia, output, attributes);

                content =  Files.readAllBytes(output.toPath());

            }catch (Exception ignored){}
            if(output!=null){
                output.delete();
            }
        }
    }

    public void convert(AudioFile output){
        try {
            Encoder encoder = new Encoder();

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(codec.get(output.extension()));

            EncodingAttributes attributes = new EncodingAttributes();
            attributes.setOutputFormat(output.extension());
            attributes.setAudioAttributes(audio);

            encoder.encode(
                    multimedia,
                    output,
                    attributes
            );
        }catch (Exception ignored){}
    }
    public void convert(String extension){
        AudioFile output = new AudioFile(this.getNameFull()+"."+extension);
        try {
            Encoder encoder = new Encoder();

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(codec.get(output.extension()));

            EncodingAttributes attributes = new EncodingAttributes();
            attributes.setOutputFormat(output.extension());
            attributes.setAudioAttributes(audio);

            encoder.encode(
                    multimedia,
                    output,
                    attributes
            );
        }catch (Exception ignored){}
    }



}
