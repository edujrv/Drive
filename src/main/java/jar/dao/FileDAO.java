package jar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jar.model.Account;
import jar.model.File;

public class FileDAO implements IDAO<File> {

    private List<File> fileList = new ArrayList<>();

    public FileDAO(Account account) {
        if (account.getId() == 1) {
            fileList.add(new File(1, "/", "txt", 10, "pepe"));
            fileList.add(new File(2, "/", "jpg", 100, "prueba"));
            fileList.add(new File(3, "/", "directory", 0, "test"));
            fileList.add(new File(4, "/test", "pdf", 0, "libro"));
            fileList.add(new File(5, "/test", "pdf", 0, "libro2"));
            fileList.add(new File(6, "/test", "directory", 0, "hola"));
        }
    }

    @Override
    public List<File> getAll() {
        return fileList;
    }

    @Override
    public Optional<File> get(int id) {
        return Optional.of((fileList.stream().filter(f -> f.getId() == id).collect(Collectors.toList())).get(0));
    }

    public Optional<List<File>> getFolderFiles(int id) {
        List<File> rFileList = new ArrayList<>();
        Optional<File> opDirectory = get(id);

        if (opDirectory.isPresent() && opDirectory.get().getMimeType().equals("directory")) {
            File directory = opDirectory.get();
            rFileList = fileList.stream().filter(f -> f.getLocation().equals(getFolderFullAddress(directory)))
                    .collect(Collectors.toList());
        }

        return Optional.of(rFileList);
    }

    private String getFolderFullAddress(File directory) {
        String r = directory.getLocation();
        r += r.endsWith("/") ? directory.getName() : "/" + directory.getName();
        return r;
    }
}