package com.righty.sanketpatel.righthandman;

/**
 * Created by Sanket Patel on 11-06-2018.
 */
public class Movie {
    private String name,msg,time;

    public Movie() {
    }

    public Movie(String name, String msg, String time) {
        this.name = name;
        this.msg = msg;
        this.time = time;
    }
    public String getName()
    {
        return  name;
    }
    public void setName()
    {
        this.name=name;
    }

    public String getmsg()
    {
        return  msg;
    }
    public void setMsg()
    {
        this.msg=msg;
    }

    public String getTime()
    {
        return  time;
    }
    public void setTime()
    {
        this.time=time;
    }

}
