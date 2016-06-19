package nguyengiap.vietitpro.tudienanhviet.com.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.app.Application;
import nguyengiap.vietitpro.tudienanhviet.com.common.Common;

public class DatabaseController extends SQLiteOpenHelper {

    private static String DB_NAME = "data.sqlite";
    private static String DB_PATH;

    protected SQLiteDatabase database;

    public DatabaseController(Context con) {
        super(con, DB_NAME, null, 1);
        DB_PATH = Common.getDatabaseDefaultPath();
    }

    /**
     * copy database from assets to the device if not existed
     *
     * @return true if not exist and create database success
     * @throws IOException
     */
    public boolean createdDatabaseIfNotExits(){
        boolean result = true;
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
        try {
            ZipInputStream zipIs = new ZipInputStream(Application.getHKApplicationContext().getResources()
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
            return (new File(DB_PATH + DB_NAME)).exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * delete database file
     *
     * @return true if database was deleted
     */
    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        return !file.exists() || file.delete();
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
