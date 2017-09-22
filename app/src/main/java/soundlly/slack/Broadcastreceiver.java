package soundlly.slack;

/**
 * Created by soundlly on 2017. 9. 6..
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class Broadcastreceiver extends BroadcastReceiver {
    protected String message;
    protected String senderNUM;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Object[] pdus = (Object[])bundle.get("pdus");

            SmsMessage[] msgs = new SmsMessage[pdus.length];
            for(int i = 0 ; i < msgs.length ; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                senderNUM = msgs[i].getOriginatingAddress();
                message = msgs[i].getMessageBody();
            }

            Post.post(senderNUM,message,context);
        }
    }

}
