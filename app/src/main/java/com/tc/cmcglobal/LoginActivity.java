package com.tc.cmcglobal;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tc.cmcglobal.Adapter.CardListAdapter;
import com.tc.cmcglobal.entities.Account;
import com.tc.cmcglobal.network.ApiManager;
import com.tc.cmcglobal.network.MyJson;
import com.tc.cmcglobal.network.MyRequest;
import com.tc.cmcglobal.readernfccard.lib.FeliCa;
import com.tc.cmcglobal.readernfccard.model.Card;

import java.util.HashMap;
//import com.sm.cmcglobal.entities.UserInfo;

/**
 * Login Activity class
 * @author gondzo
 */
public class LoginActivity extends AppCompatActivity {


    private CardListAdapter adapter;
    private String cardNumber ;

    /**
     * Handles the onCreate event
     * @param savedInstanceState state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView tvFailedLogin = (TextView)findViewById(R.id.incorrect);
        final EditText tbUsername = (EditText)findViewById(R.id.username);
        final EditText tbPassword = (EditText)findViewById(R.id.password);
        Button btnLogin  = (Button)findViewById(R.id.btnLogin);
        final String username = tbUsername.getText().toString();
        final String password = tbPassword.getText().toString();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username , password);
            }
        });

        Button btnLoginNFC = (Button) findViewById(R.id.btnLoginNFC);
        //testNFC();
        btnLoginNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  testNFC();
                loginNFC("card_id", "card_type");
            }
        });
//        ViewPager
    }


    public void login(String username , String pass){
        String url = ApiManager.getUrlLogin();
        HashMap<String , String> params = new HashMap<>();
//        params.put("user_cd" , username);
//        params.put("password" , pass);
        params.put("user_cd" , "hongha1");
        params.put("password" , "hongha1");
        Log.d("Quan", "login: begin");
        MyRequest request  = new MyRequest(url,params, new Response.Listener<MyJson>() {
            @Override
            public void onResponse(MyJson myJson) {
                Log.d("Quan", "login: success");
                Log.e("Json" , myJson.data);
                Account account =  myJson.to(Account.class);
                MainApplication.get().setAccount(account);
                Intent intent  = new Intent(LoginActivity.this , MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("accounttoken" , account.token);
//                intent.putExtras(bundle);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Quan", "login: eror");
            }
        });
//        request.setHeaderValueParams("ajfwjlwe");

//        Map<String , String> paramHeader = new HashMap<String, String>();
//        paramHeader.put("Content-Type" , "application/json");
//        request.setHeaderParams(paramHeader);
        MainApplication.get().getRequestQueue().add(request);

    }


    private void loginNFC(String cardID, String cardType){
        byte[] felicaIDm;
        Intent intent = getIntent();
        Tag nfcTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(nfcTag != null) {
            felicaIDm = nfcTag.getId();
            cardNumber = felicaIDm+ "";

            Toast.makeText(this, "ID Card " + felicaIDm, Toast.LENGTH_LONG).show();


            String url = ApiManager.getUrlLogin();
            HashMap<String, String> params = new HashMap<>();
//            params.put("card_id", cardNumber);
//            params.put("card_type", cardNumber);
            params.put("card_id", "1111");
            params.put("card_type", "1");
            MyRequest request  = new MyRequest(url,params, new Response.Listener<MyJson>() {
                @Override
                public void onResponse(MyJson myJson) {
                    Log.e("Json" , myJson.data);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
//        request.setHeaderValueParams("ajfwjlwe");
/*
            Map<String , String> paramHeader = new HashMap<String, String>();
            paramHeader.put("Author..." , "abc1234");
            MainApplication.get().getRequestQueue().add(request);*/


        }else {
            Toast.makeText(this, "カードを後ろにかざしてください ID đặt Card gần hơn ", Toast.LENGTH_LONG).show();
            return;
        }

    }

    private void testNFC() {
        adapter = new CardListAdapter(getApplicationContext());

        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        ListView listView = (ListView) findViewById(R.id.card_list);
        listView.setPadding(padding, 0, padding, 0);
        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        listView.setDivider(null);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        /*View header = inflater.inflate(R.layout.list_header_footer, listView, false);
        View footer = inflater.inflate(R.layout.list_header_footer, listView, false);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);*/
        listView.setAdapter(adapter);

        // NFC(FeliCa) ID を取得
        byte[] felicaIDm;
        Intent intent = getIntent();
        Tag nfcTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(nfcTag != null) {
            felicaIDm = nfcTag.getId();
            // cardNumber = felicaIDm+ "";
            Toast.makeText(this, "ID Card " + felicaIDm, Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "カードを後ろにかざしてください", Toast.LENGTH_LONG).show();
            return;
        }

        NfcF felica = NfcF.get(nfcTag);

        try {
            felica.connect();
            byte[] req = FeliCa.readWithoutEncryption(felicaIDm, 10);
            byte[] res = felica.transceive(req);
            felica.close();
            parsePasmoHistory(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parsePasmoHistory(byte[] res) throws Exception {
        // res[0] = データ長
        // res[1] = 0x07
        // res[2〜9] = カードID
        // res[10,11] = エラーコード。0=正常。
        if (res[10] != 0x00) {
            throw new RuntimeException("Felica error.");
        }

        // res[12] = 応答ブロック数
        // res[13+n*16] = 履歴データ。16byte/ブロックの繰り返し。
        int size = res[12];
        int payment = 0;
        for (int i = 0; i < size; i++) {
            FeliCa felica = FeliCa.parse(res, 13 + i * 16);
            Card card = Card.getCard(getBaseContext(), felica);
            if (i < size-1) {
                FeliCa nextFelica = FeliCa.parse(res, 13 + (i+1) * 16);
                Card nextCard = Card.getCard(getBaseContext(), nextFelica);
                payment = Integer.parseInt(card.getBalance()) - Integer.parseInt(nextCard.getBalance());
            }
            card.setPayment(String.valueOf(payment));
            adapter.add(card);
        }
    }

    public void getClassByAccount(){
        String url = ApiManager.getUrlGetClass();
    }
    /**
     * Handles the back event
     */
    @Override
    public void onBackPressed() {

    }
}
