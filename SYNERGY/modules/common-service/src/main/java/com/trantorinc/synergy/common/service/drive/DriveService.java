package com.trantorinc.synergy.common.service.drive;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.BLANK;
import static com.trantorinc.synergy.common.service.constant.AppConstantService.JPG;

public class DriveService {
    private static final Log log = LogFactoryUtil.getLog(DriveService.class.getName());

    private DriveService() {
        // default constructor
    }

    private static Drive getService(){
        Drive service = null;
        try {
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault().createScoped(Collections.singletonList(DriveScopes.DRIVE_FILE));
            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
            service = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer).setApplicationName("trantor-intranet-synergy").build();
        }catch(IOException exception){
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return  service;
    }

    public static String createFolder(String parentFolderId, String folderName) {
        String folderId = null;
        File fileMetadata = new File();
        fileMetadata.setName(folderName);
        fileMetadata.setParents(Collections.singletonList(parentFolderId));
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        try {
            Drive service = getService();
            if(null != service) {
                File file = service.files().create(fileMetadata).setFields("id").execute();
                folderId = file.getId();
            }
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return folderId;
    }

    public static String uploadFile(String folderId, String filename, java.io.File file) {
        String fileId = null;
        File fileMetadata = new File();
        fileMetadata.setName(filename);
        fileMetadata.setParents(Collections.singletonList(folderId));
        try {
            FileContent mediaContent = new FileContent(Files.probeContentType(file.toPath()), file);
            Drive service = getService();
            if(null != service) {
                File files = service.files().create(fileMetadata, mediaContent).setSupportsTeamDrives(true).setFields("id,parents").execute();
                fileId = files.getId();
            }
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return  fileId;
    }

    public static java.io.File getFile(String fileId, String prefix, String suffix) {
        java.io.File file = null;
        try {
            Drive service = getService();
            if(null != service) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                service.files().get(fileId).executeMediaAndDownloadTo(stream);
                file = java.io.File.createTempFile(prefix,suffix);
                FileUtils.writeByteArrayToFile(file, stream.toByteArray());
            }
        }
        catch(IOException exception){
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return file;
    }

    public static boolean deleteFile(String fileId) {
        boolean deleted=false;
        try {
            Drive service=getService();
            if(null != service) {
                service.files().delete(fileId).execute();
                deleted = true;
            }
        } catch (IOException exception) {
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return deleted;
    }

    public static List<String> getFolderFiles(String folderId) {
        List<String> ids = new ArrayList<>();
        try {
            Drive service = getService();
            if(null != service) {
                String nextPageToken = null;
                do{
                    FileList result = service.files().list().setPageSize(250).setQ("trashed=false and ('" + folderId + "'" + " in parents)").setPageToken(nextPageToken).setFields("nextPageToken, files(id, name)").execute();
                    nextPageToken = result.getNextPageToken();
                    List<File> files = result.getFiles();
                    if (!files.isEmpty()) {
                        for (File file : files) {
                            ids.add(file.getId());
                        }
                    }
                } while(nextPageToken != null);
            }
        }catch(IOException exception){
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return  ids;
    }

}
