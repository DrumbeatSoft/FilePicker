package com.drumbeat.filepicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;
import com.ess.filepicker.util.LogUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdf = findViewById(R.id.pdf);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FilePicker
//                        .from(MainActivity.this)
//                        .chooseForMimeType()
//                        .setMaxCount(3)
//                        .setFileTypes("pdf")
//                        .requestCode(1)
//                        .start();
//                FindAllFiles.folderMethod1(Environment.getExternalStorageDirectory().getPath());
//                FindAllFiles.folder(MainActivity.this);
//                String path = "/storage/emulated/0/Android/data/";
//                String path = "/storage/emulated/0/Android/data/com.tencent.mobileqq/Tencent";
//                FileUriUtils.startFor(path, MainActivity.this, 2);
//                if (haspermission) {
//                    DocumentFile documentFile = DocumentFile.fromTreeUri(MainActivity.this, Uri.parse(FileUriUtils.changeToUri3(path)));
//                    getFiles(documentFile);
//                }
                FilePicker
                        .from(MainActivity.this)
                        .chooseForMimeType()
                        .setMaxCount(10)
                        .setFileTypes("png", "doc","apk", "mp3", "gif", "txt", "mp4", "pdf")
                        .setTheme(R.style.AppTheme)
                        .requestCode(1)
                        .start();

            }
        });

    }

    boolean haspermission;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
            for (int i = 0; i < essFileList.size(); i++) {
                stringBuilder.append(essFileList.get(i).getName())
                        .append(",");
            }
            pdf.setText(stringBuilder.toString());
        }
        if (requestCode == 2) {
            Uri uri;
            if (data == null) {
                return;
            }
            if (requestCode == 2 && (uri = data.getData()) != null) {
                getContentResolver().takePersistableUriPermission(uri, data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));//关键是这里，这个就是保存这个目录的访问权限

                haspermission = true;

            }
        }
    }

}
