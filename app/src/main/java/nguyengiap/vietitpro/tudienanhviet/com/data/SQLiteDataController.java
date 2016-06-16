package nguyengiap.vietitpro.tudienanhviet.com.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

import nguyengiap.vietitpro.tudienanhviet.com.R;


public class SQLiteDataController extends SQLiteOpenHelper {

    public static String PackageName = "nguyengiap.vietitpro.tudienanhviet.com";
    private static String DB_NAME = "data.sqlite";

    public static String DB_PATH = "//data//data//" + PackageName + "//databases//";
    protected SQLiteDatabase database;
    private final Context mContext;

    public SQLiteDataController(Context con) {
        super(con, DB_NAME, null, 1);
        this.mContext = con;
    }

    /**
     * copy database from assets to the device if not existed
     *
     * @return true if not exist and create database success
     * @throws IOException
     */
    public boolean isCreatedDatabase() throws IOException {
        // Default là đã có DB
        boolean result = true;
        // Nếu chưa tồn tại DB thì copy từ Asses vào Data
        if (!checkExistDataBase()) {
            this.getReadableDatabase();
            try {
                unpackZip();
                result = false;
            } catch (Exception e) {
                throw new Error("Error copying database");
            }
        }

        return result;
    }

    private boolean unpackZip() {
        // InputStream is;
        // ZipInputStream zis;
        try {
            ZipInputStream zipIs = new ZipInputStream(mContext.getResources()
                    .openRawResource(R.raw.data));

            while (zipIs.getNextEntry() != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;
                FileOutputStream fout = new FileOutputStream(DB_PATH + DB_NAME);
                // reading and writing
                while ((count = zipIs.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zipIs.closeEntry();
            }

            zipIs.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * check whether database exist on the device?
     *
     * @return true if existed
     */
    private boolean checkExistDataBase() {

        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * copy database from assets folder to the device
     *
     * @throws IOException
     */
    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * delete database file
     *
     * @return
     */
    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        return file.delete();
    }

    /**
     * open database
     *
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
    }



}
