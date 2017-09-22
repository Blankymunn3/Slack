package soundlly.slack;

/**
 * Created by soundlly on 2017. 9. 6..
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class Broadcastreceiver extends BroadcastReceiver {
    private Bundle bundle;
    private SmsMessage currentSMS;
    public String message;
    public String senderNo;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdu_Objects = (Object[]) bundle.get("pdus");
                if (pdu_Objects != null) {
                    for (Object aObject : pdu_Objects) {
                        currentSMS = getIncomingMessage(aObject, bundle);
                        senderNo = currentSMS.getDisplayOriginatingAddress();
                        message = currentSMS.getDisplayMessageBody();
                    }
                    this.abortBroadcast();
                    // End of loop
                }


                Post.post(senderNo,message,context);
            }
        } // bundle null

    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
}
