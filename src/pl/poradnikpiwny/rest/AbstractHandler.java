package pl.poradnikpiwny.rest;

import android.os.Handler;
import android.os.Message;

public abstract class AbstractHandler extends Handler
{
	@Override
	public final void handleMessage(Message msg){
			switch(msg.what){
				case 200:
					success(msg);
					break;
				case 301:
					redirect(msg);
					break;
				case 304:
					notModified(msg);
					break;
				case 400:
					badRequest(msg);
					break;
				case 403:
					forbidden(msg);
					break;
				case 404:
					notFound(msg);
					break;
				case 444:
					noResponse(msg);
					break;
				case 500:
					internalServerError(msg);
					break;
				case 501:
					notImplemented(msg);
					break;
				default:
					error(msg);
			}
	}
	
	public void redirect(Message msg) 
	{
		error(msg);
	}
	
	public void notModified(Message msg) 
	{
		error(msg);
	}
	
	public void badRequest(Message msg) 
	{
		error(msg);
	}

	public void forbidden(Message msg) 
	{
		error(msg);
	}
	
	public void notFound(Message msg) 
	{
		error(msg);
	}
	
	public void noResponse(Message msg) 
	{
		error(msg);
	}
	
	public void internalServerError(Message msg) 
	{
		error(msg);
	}
	
	public void notImplemented(Message msg) 
	{
		error(msg);
	}
	
	public int getRequestType() 
	{
		return RestService.GET;
	}
	
	public abstract String getServiceUrl();
	public abstract void success(Message msg);
	public abstract void error(Message msg);
	
}
