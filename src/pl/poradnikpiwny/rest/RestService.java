package pl.poradnikpiwny.rest;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;

public class RestService
{
	private ArrayList <ParcelableNameValuePair> params;
	private ArrayList <ParcelableNameValuePair> headers;

	private final AbstractHandler mHandler;
	private Context mContext;
	private String entity;

	public final static int GET = 1;
	public final static int POST = 2;
	public final static int PUT = 3;
	public final static int DELETE = 4;
	
	public RestService(AbstractHandler mHandler, Context mContext)
	{
		this.mHandler = mHandler;
		this.mContext = mContext;

		params = new ArrayList<ParcelableNameValuePair>();
		headers = new ArrayList<ParcelableNameValuePair>();		
		headers.add(new ParcelableNameValuePair("Accept", "application/json"));
	}

	public void addParam(String name, String value)
	{
		params.add(new ParcelableNameValuePair(name, value));
	}

	public void addHeader(String name, String value)
	{
		headers.add(new ParcelableNameValuePair(name,value));
	}

	public void setEntity(String entity)
	{
		this.entity = entity;
	}

	public void execute() 
	{
	     ResultReceiver receiver;
	     receiver = new ResultReceiver(mHandler) {
	    	 @Override
	    	 protected void onReceiveResult(int resultCode, Bundle resultData) {
	    		 mHandler.obtainMessage(resultCode,0,0,resultData.getString("result")).sendToTarget();
	    	 }
	     };
	     final Intent intent = new Intent(mContext, ExecuteRequest.class);
	     intent.putParcelableArrayListExtra("headers", (ArrayList<? extends Parcelable>) headers);
	     intent.putExtra("params", params);
	     intent.putExtra("url", mHandler.getServiceUrl());
	     intent.putExtra("receiver", receiver);
	     intent.putExtra("method", mHandler.getRequestType());
	     intent.putExtra("entity", entity);
	     mContext.startService(intent);
	}
}
