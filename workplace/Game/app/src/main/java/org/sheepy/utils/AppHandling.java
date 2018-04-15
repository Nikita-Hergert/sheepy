package org.sheepy.utils;

/**
 * Created by Vitali Dettling on 27.02.2016.
 */
public class AppHandling {

    private static final int SUCCESSFUL = 0;
    private static final int pid;

    static{
        pid = android.os.Process.myPid();
    }

    /**
     * Destroys the current instance of the app, with all activities.
     * */
    public static void destroy(){
        android.os.Process.killProcess(pid);
        System.exit(SUCCESSFUL);
    }
}
