package com.example.currencyconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Formatter;

import static com.example.currencyconverterapp.R.string.Url;

public class MainActivity extends AppCompatActivity {
    Button dolar,euro,pound,yen,dinar,rubel,cand,ausd,bitcoin;
    EditText editText;
    TextView textView;
    String z;
    double n,k;

    //USE YOUR API KEY FROM currencylayer.com
    String Url="http://api.currencylayer.com/live?access_key=USE YOUR API KEY FROM currencylayer.com" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dolar=findViewById(R.id.dolar);
        euro=findViewById(R.id.euro);
        pound=findViewById(R.id.pound);
        yen=findViewById(R.id.yen);
        dinar=findViewById(R.id.dinar);
        rubel=findViewById(R.id.rubel);
        cand=findViewById(R.id.cand);
        ausd=findViewById(R.id.ausd);
        bitcoin=findViewById(R.id.bitcoin);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);

        euro.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    euro.setBackgroundColor(Color.parseColor("#019031"));
                    textView.setText("Calculating......");
                    n=Double.parseDouble(z);
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDEUR = ob1.getDouble("USDEUR");
                                        k=(n*(1/USDINR))*USDEUR;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" EURO");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);

                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    euro.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });

        dolar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    dolar.setBackgroundColor(Color.parseColor("#019031"));
                    n=Double.parseDouble(z);
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        k=n*(1/USDINR);
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" US DOLLAR");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    dolar.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });
        pound.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    pound.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDGBP = ob1.getDouble("USDGBP");
                                        k=(n*(1/USDINR))*USDGBP;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" BRITISH POUND");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    pound.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });

        yen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    yen.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDJPY = ob1.getDouble("USDJPY");
                                        k=(n*(1/USDINR))*USDJPY;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" JAPANESE YEN");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    yen.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });

        dinar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    dinar.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDIQD = ob1.getDouble("USDIQD");
                                        k=(n*(1/USDINR))*USDIQD;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" IRAQI DINAR");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    dinar.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });

        bitcoin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    bitcoin.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDBTC = ob1.getDouble("USDBTC");
                                        k=(n*(1/USDINR))*USDBTC;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" BITCOIN");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    bitcoin.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });
        ausd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    ausd.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDAUD = ob1.getDouble("USDAUD");
                                        k=(n*(1/USDINR))*USDAUD;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" AUSTRALIAN DOLLAR");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    ausd.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });

        cand.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    cand.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDCAD = ob1.getDouble("USDCAD");
                                        k=(n*(1/USDINR))*USDCAD;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" CANADIAN DOLLAR");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    cand.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });
        rubel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                z=editText.getText().toString();
                if(hasFocus && !TextUtils.isEmpty(z)){
                    textView.setText("Calculating......");
                    rubel.setBackgroundColor(Color.parseColor("#019031"));
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String quotes = jsonObject.getString("quotes");
                                        JSONObject ob1 = new JSONObject(quotes);
                                        double USDINR = ob1.getDouble("USDINR");
                                        double USDRUB = ob1.getDouble("USDRUB");
                                        k=(n*(1/USDINR))*USDRUB;
                                        DecimalFormat numberFormat=new DecimalFormat("#.00");
                                        textView.setText(""+numberFormat.format(k));
                                        textView.append(" RUSSIAN RUBLE");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"No Currency data available!\nCheck your internet connection",Toast.LENGTH_SHORT).show();
                            textView.setText("");
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                }
                else if(hasFocus && TextUtils.isEmpty(z)){
                    editText.setError("Empty user value!");
                }
                else{
                    rubel.setBackgroundColor(Color.parseColor("#2F363F"));
                }
            }
        });
    }
}
