package com.hoangdang.BookStore.utils;

import java.io.IOException;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;

public class ShareGoogleFile {

    // Public a Google File/Folder.
    public static Permission createPublicPermission(String googleFileId) throws IOException {
        // All values: user - group - domain - anyone
        String permissionType = "anyone";
        // All values: organizer - owner - writer - commenter - reader
        String permissionRole = "reader";

        Permission newPermission = new Permission();
        newPermission.setType(permissionType);
        newPermission.setRole(permissionRole);

        Drive driveService = GoogleDriveUtils.getDriveService();
        return driveService.permissions().create(googleFileId, newPermission).execute();
    }
}
