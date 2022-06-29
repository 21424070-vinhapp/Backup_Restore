package com.example.backup_restore.presentation.view.backup_call_logs;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;

import com.example.backup_restore.R;
import com.example.backup_restore.databinding.ActivityBackupCallLogBinding;
import com.example.backup_restore.databinding.ActivityBackupSmsBinding;
import com.example.backup_restore.model.CallLogs;
import com.example.backup_restore.presentation.adapter.CallLogsAdapter;

import java.util.ArrayList;
import java.util.List;

public class BackupCallLogActivity extends AppCompatActivity {
    private static final String GET_NAME_NULL = "Số Điện Thoại Này Chưa Được Lưu";
    private static final String STRING_OUTGOING = "Cuộc Gọi Đi";
    private static final String STRING_INCOMING = "Cuộc Gọi Đến";
    private static final String STRING_MISSING = "Cuộc Gọi Đi";
    ActivityBackupCallLogBinding mBinding;
    List<CallLogs> mLstCallLogs, current;
    CallLogsAdapter mCallLogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBackupCallLogBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        addView();
    }

    private void addView() {
        mLstCallLogs = getCallLogsFromDevice();
        mCallLogsAdapter = new CallLogsAdapter(mLstCallLogs);
        mBinding.rcvBackupCallLogs.setAdapter(mCallLogsAdapter);
    }

    private List<CallLogs> getCallLogsFromDevice() {
        current = new ArrayList<>();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String idNumber = CallLog.Calls.NUMBER;
            int number = cursor.getColumnIndex(idNumber);
            String getNumBer = cursor.getString(number);

            String idName = CallLog.Calls.CACHED_NAME;
            int name = cursor.getColumnIndex(idName);
            String getName = cursor.getString(name);
            if (getName == null) {
                getName = GET_NAME_NULL;
            }

            String idCall = CallLog.Calls.TYPE;
            int call = cursor.getColumnIndex(idCall);
            String getType = getTypeCall(Integer.parseInt(cursor.getString(call)));

            String idDuration = CallLog.Calls.DURATION;
            int duration = cursor.getColumnIndex(idDuration);
            String getDuration = cursor.getString(duration) + "s";

            current.add(0,new CallLogs(getNumBer, getName, getDuration, getType));
        }
        cursor.close();
        return current;
    }

    private String getTypeCall(int type) {
        String typeCall = null;
        switch (type) {
            case CallLog.Calls.OUTGOING_TYPE:
                typeCall = STRING_OUTGOING;
                break;
            case CallLog.Calls.INCOMING_TYPE:
                typeCall = STRING_INCOMING;
                break;
            case CallLog.Calls.MISSED_TYPE:
                typeCall = STRING_MISSING;
                break;
        }
        return typeCall;
    }
}