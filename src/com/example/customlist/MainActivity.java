package com.example.customlist;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends Activity {
	
	ListView lv;
    Context context;
    private String urlJsonArry = "http://bergsonlm.com/cliente";
    private ArrayList<Cliente> listaclients = new ArrayList<Cliente>();
	private static String TAG = MainActivity.class.getSimpleName();
	

    //ArrayList prgmName;
    
       
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        

        
//        makeJsonArrayRequest();
                 


        Cliente cliente = new Cliente("Berg", "");
        listaclients.add(cliente);
        
                                   	
        makeRequest();
        context=this;
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, listaclients));
        
    }
    
    


    public void makeRequest(){
    	RequestQueue rq = Volley.newRequestQueue(this);
        JsonArrayRequest jReq = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
         
                    @Override
                    public void onResponse(JSONArray response) {
                    	                    
                        for (int i = 0; i < response.length(); i++) {
                          try {
                            	JSONObject obj = (JSONObject) response.get(i);
                            	String nome = obj.getString("nome");
                            	String telefone = obj.getString("telefone");
        
                            	Cliente cliente = new Cliente(nome, "");
                                listaclients.add(cliente);
                            	
                                
                            } catch (JSONException e) {
                            }
                        }
                        //adpt.setItemList(result);
                        //adpt.notifyDataSetChanged();
                    }

					
                }, new Response.ErrorListener() {
         
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
         
                    }
                });
        rq.add(jReq);

    }

    private void makeJsonArrayRequest() {		

		JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
			
							for (int i = 0; i < response.length(); i++) {

								JSONObject person = (JSONObject) response
										.get(i);

								String nome = person.getString("nome");
								String telefone = person.getString("telefone");
								

								Cliente cliente = new Cliente(nome, telefone);
						        listaclients.add(cliente);
							

							}

		//					txtResponse.setText(jsonResponse);

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}

	//					hidepDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_SHORT).show();
//						hidepDialog();
					}
				});

		// Adding request to request queue
	AppController.getInstance().addToRequestQueue(req);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
