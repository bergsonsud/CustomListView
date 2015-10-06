package com.example.customlist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
public class CustomAdapter extends BaseAdapter{   
    ArrayList<Cliente> result;    
    Context context;
    
    

    
 int [] imageId;
private CustomAdapter adapter;
      private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, ArrayList<Cliente> listaclients) {
        // TODO Auto-generated constructor stub
        result=listaclients;
        context=mainActivity;
        adapter = this;
        
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
         notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView tv2;
        Button delete;   
        String id;
        
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;       
             rowView = inflater.inflate(R.layout.program_list, null);
             holder.tv=(TextView) rowView.findViewById(R.id.textView1);
             holder.tv2=(TextView) rowView.findViewById(R.id.textView2);
             holder.delete = (Button) rowView.findViewById(R.id.delete);
             
               
         holder.tv.setText(result.get(position).getNome());
         holder.tv2 .setText(result.get(position).getTelefone());
         holder.id = result.get(position).getId();
                 
         rowView.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result.get(position).getNome(), Toast.LENGTH_LONG).show();
            }
        });  
         
         holder.delete.setOnClickListener(new View.OnClickListener() {

 			@Override
 			public void onClick(View v) {
 				RequestQueue queue = Volley.newRequestQueue(context);
 				
 								
 				Toast.makeText(context, "DELETAR "+holder.id+" "+result.get(position).getNome(), Toast.LENGTH_LONG).show();
 				
 				String url ="http://www.bergsonlm.com/cliente/update/"+holder.id;

		 			// Request a string response from the provided URL.
		 			StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
		 			            new Response.Listener<String>() {
		 			    @Override
		 			    public void onResponse(String response) {
		 			        // Display the first 500 characters of the response string.
		 			        //mTextView.setText("Response is: "+ response.substring(0,500));
		 			    	//Toast.makeText(context,"Response is: "+ response.substring(0,500), Toast.LENGTH_LONG).show();
		 			    	
		 			    	
		 			    }
		 			}, new Response.ErrorListener() {
		 			    @Override
		 			    public void onErrorResponse(VolleyError error) {
		 			        //mTextView.setText("That didn't work!");
		 			    	//Toast.makeText(context,"That didn't work!", Toast.LENGTH_LONG).show();
		 			    }
		 			});
		 			// Add the request to the RequestQueue.
		 			queue.add(stringRequest);
		 			notifyDataSetChanged();
 				
 			}
 		});
        return rowView;
    }

}