package soundlly.slack;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by soundlly on 2017. 9. 22..
 */

public class SMSReceiver extends AppCompatActivity {

    private void SMSReceiver() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d("문자 수신 권한 ", "있음");
        } else {
            Log.d("문자 수신 권한 ", "없음");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                Log.d("문자 권한 설정 ", "필요");
            } else {
                // 권한이 할당되지 않았으면 해당 권한을 요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("문자 수신 권한", "승인");
                } else {
                    Log.d("문자 수신 권한", "거부");
                }
            }
        }
    }
}
