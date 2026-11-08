package file.pathing;

public record FileName(String name, String... extensions) {


    public static FileName nameOfFile(String name, String... extensions) {
        return new FileName(name, extensions);
    }

    public static FileName nf(String name, String... extensions) {
        return new FileName(name, extensions);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(name);
        for (String e : extensions) s.append(".").append(e);
        return s.toString();
    }
}
