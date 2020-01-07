package org.firstinspires.ftc.teamcode.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class Song
{
	private MediaPlayer mediaPlayer;
	private Context context;
    private int song;
    
    public Song(Context context, int song)
    {
    	try
		{
    		mediaPlayer = MediaPlayer.create(context, song);
            this.context = context;
            this.song = song;
        }
        catch(Exception e)
	    {
	    
		}
    }
    
    public void play()
    {
        if(mediaPlayer == null || mediaPlayer.isPlaying())
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {
            	try
				{
					mediaPlayer.start();
				}
                catch(Exception e)
				{
				
				}
            }
        }).run();
    }

    public void stop()
    {
        if(mediaPlayer == null || !mediaPlayer.isPlaying())
            return;
        try
		{
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = MediaPlayer.create(context, song);
		}
        catch(Exception e)
		{
		
		}
    }
}

