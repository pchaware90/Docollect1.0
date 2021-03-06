package com.example.webmail;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class UploadFile extends AsyncTask<Void, Long, Boolean> {

	private DropboxAPI<?> dropboxApi;
	private String path;
	private Context context;

	public UploadFile(Context context, DropboxAPI<?> dropboxApi,
					  String path) {
		this.context = context.getApplicationContext();
		this.dropboxApi = dropboxApi;
		this.path = path;
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		final File tempDropboxDirectory = context.getCacheDir();
		File tempFileToUploadToDropbox;
		FileWriter fileWriter = null;
		
		try {
			// Creating a temporal file.
			File dir = new File(Environment.getDataDirectory() + "/WebmailData/");
			File file = new File("/WebmailData/Alert.txt");
			tempFileToUploadToDropbox = File.createTempFile("file", "aaa.txt", tempDropboxDirectory);
			fileWriter = new FileWriter(tempFileToUploadToDropbox);
			fileWriter.write("Hello World drom Android Dropbox Implementation Example!");
			fileWriter.close();

			// Uploading the newly created file to Dropbox.
			FileInputStream fileInputStream = new FileInputStream(tempFileToUploadToDropbox);
			dropboxApi.putFile(path + "Alert.txt", fileInputStream,
					tempFileToUploadToDropbox.length(), null, null);
			//tempFileToUploadToDropbox.delete();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DropboxException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if (result) {
			Toast.makeText(context, "File has been successfully uploaded!",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, "An error occured while processing the upload request.",
					Toast.LENGTH_LONG).show();
		}
	}
}