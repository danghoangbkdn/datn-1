package com.hoangdang.BookStore.services;

import java.io.File;
import java.io.IOException;

public interface FileService {
    void saveGoogleFile(File file) throws IOException;
}
