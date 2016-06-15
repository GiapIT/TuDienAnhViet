package nguyengiap.vietitpro.tudienanhviet.com.app;

import android.content.Context;

public class Application extends android.app.Application {

    private static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
       _context = getApplicationContext();

    }

    public static Context getHKApplicationContext() {
        return _context;
    }
}
