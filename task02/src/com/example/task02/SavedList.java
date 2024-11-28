package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    File file;
    List<E> list;

    public SavedList(File file) throws IOException {
        this.file = file;

        if (file.exists()) {
            try {
                ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file.toPath()));
                list = (ArrayList<E>) in.readObject();
            } catch (Exception e) { }
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = list.set(index, element);
        UpdateFile();
        return oldElement;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        UpdateFile();
    }

    @Override
    public E remove(int index) {
        list.remove(index);
        UpdateFile();
        return null;
    }

    public void UpdateFile()
    {
        try (ObjectOutputStream out = new ObjectOutputStream((Files.newOutputStream(file.toPath())))) {
            out.writeObject(list);
        } catch (Exception e) { }
    }
}
