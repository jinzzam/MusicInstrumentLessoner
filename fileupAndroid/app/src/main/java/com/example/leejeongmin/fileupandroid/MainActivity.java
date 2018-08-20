package com.example.leejeongmin.fileupandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView tvFile;
    Button btnFile;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;
    String upLoadServerUri = null;

    //file path
    final String uploadFilePath = "/mnt/sdcard/Music/";
    final String uploadFileName = "test11.m4a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFile = (Button)findViewById(R.id.btnFile);
        tvFile = (TextView)findViewById(R.id.tvFile);

        tvFile.setText("Uploading file path : -'/mnt/sdcard/"+uploadFileName+"'");

        upLoadServerUri="http://192.168.43.43:3000/fileUploadServer";

        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = ProgressDialog.show(MainActivity.this,"","Uploading file...",true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvFile.setText("uploading started...");
                            }
                        });
                        uploadFile(uploadFilePath+""+uploadFileName);
                    }
                }).start();
            }
        });
    }



    public int uploadFile(String sourceFileUri){
        String fileName = sourceFileUri;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String attachmentName = "userfile";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1*1024*1024;
        File sourceFile = new File(sourceFileUri);

        if(!sourceFile.isFile()){
            dialog.dismiss();
            Log.e("uploadFile","Source File not exist:"
            +uploadFilePath+""+uploadFileName);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFile.setText("Source File not exist:"+
                    uploadFilePath+""+uploadFileName);
                }
            });
            return 0;
        }else{
            try{
                //open a URL connection to the Servlet

                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                //open a HTTP connection to the URL
                conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);  //allow inputs
                conn.setDoOutput(true); //allow outputs
                conn.setUseCaches(false);   //dont use a cached copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection","Keep-Alive");
                conn.setRequestProperty("Cache-Control", "no-cache");
                //conn.setRequestProperty("ENCTYPE","multipart/form-data");
                conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
                //conn.setRequestProperty("uploaded_file",fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens+boundary+lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\""+attachmentName+"\";filename=\""+fileName+"\""+lineEnd);
                dos.writeBytes(lineEnd);

                //create a buffer of maximum size
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                buffer = new byte[bufferSize];

                //read file and write it into form
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                while(bytesRead>0){
                    dos.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                //send multipart form data necessary after file data
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens+boundary+twoHyphens+lineEnd);

                //responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile","HTTP Response is : "+serverResponseMessage+":"+serverResponseCode);

                if(serverResponseCode==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String msg = "File Upload Completed.\n\n See uploaded file here: \n\n"+uploadFileName;

                            tvFile.setText(msg);
                            Toast.makeText(MainActivity.this, "File Upload Complete", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams
                fileInputStream.close();
                dos.flush();
                dos.close();

            }catch (MalformedURLException ex){
                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvFile.setText("MalformedURLException Exception: check script url.");
                        Toast.makeText(MainActivity.this, "MalfromedURLException", Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server","error: "+ex.getMessage(),ex);
            }catch (Exception e){
                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvFile.setText("Got Exception : see logcat");
                        Toast.makeText(MainActivity.this, "Got Exception : see logcat", Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file exception","Exception : "+e.getMessage(),e);
            }
            dialog.dismiss();
            return serverResponseCode;
        }//End else block
    }
}


