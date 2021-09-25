package chouakira.cc.takeumbrella.common;

import android.app.Application;

/**
 * Created by rsa on 2017/8/19.
 */

public class App extends Application {

    /**
     * #refer http://www.jianshu.com/p/808b9d92d6cd
     */
    @Override
    public void onCreate() {
        ContextHolder.initial(this);
        super.onCreate();
    }
}


