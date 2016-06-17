package nguyengiap.vietitpro.tudienanhviet.com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.common.StringConvert;
import nguyengiap.vietitpro.tudienanhviet.com.model.EVEntity;

public class EngVietController extends DatabaseController {

    private static String ROW_LIMIT = null;

    public EngVietController(Context con) {
        super(con);
        createdDatabaseIfNotExits();
    }

//    public ArrayList<EVEntity> getAllDict() {
//        ArrayList<EVEntity> allEntity = new ArrayList<>();
//        if (getAllEV() != null && getAllEV().isEmpty()) {
//            allEntity.addAll(getAllEV());
//        }
//        if (getAllVE() != null && getAllVE().isEmpty()) {
//            allEntity.addAll(getAllVE());
//        }
//        return allEntity;
//    }

    //ANH - VIET
    public ArrayList<EVEntity> getAllEV() {
        return getEVByKeyword(null);
    }

    public ArrayList<EVEntity> getEVByKeyword(String keyword) {
        ArrayList<EVEntity> listEVEntity = new ArrayList<>();
        String selection = null;
        String[] selectionArgs = null;
        if (!TextUtils.isEmpty(keyword)) {
            selection = IEngViet.WORD + "=?";
            selectionArgs = new String[]{String.valueOf(keyword)};
        }
        try {
            openDataBase();
            Cursor cursor = database.query(IEngViet.ENG_VIET_TABLE,
                    new String[]{IEngViet.WORD, IEngViet.MEAN},
                    selection, //selection
                    selectionArgs, //selectionArgs
                    null, //groupBy
                    null, //having
                    null, //orderBy
                    ROW_LIMIT);
            listEVEntity = getAllEVFromCursor(cursor);
        } catch (SQLException e) {
            e.printStackTrace();
            listEVEntity = null;
        } finally {
            close();
        }
        return listEVEntity;
    }

    private ArrayList<EVEntity> getAllEVFromCursor(Cursor cursor) {
        ArrayList<EVEntity> listEVEntity = new ArrayList<>();
        if (cursor != null) {
            listEVEntity = new ArrayList<>();
            boolean canMoveToFirst = cursor.moveToFirst();
            if (canMoveToFirst) {
                String[] columnNames = cursor.getColumnNames();
                do {
                    EVEntity engVietEntity = new EVEntity();
                    for (int i = 0; i < columnNames.length; i++) {
                        String columnName = columnNames[i];
                        if (IEngViet.WORD.equals(columnName)) {
                            engVietEntity.setWord(cursor.getString(i));
                        } else if (IEngViet.MEAN.equals(columnName)) {
                            engVietEntity.setMeans(cursor.getString(i));
                            engVietEntity.setPhonic(StringConvert.getPhonicFromMeans(cursor.getString(i)));
                            engVietEntity.setSimpleMeans(
                                    StringConvert.getSimpleMeans(engVietEntity.getWord(), cursor.getString(i)));
                        }
                    }
                    listEVEntity.add(engVietEntity);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return listEVEntity;
    }

    //VIET - ANH
    public ArrayList<EVEntity> getAllVE() {
        return getVEByKeyword(null);
    }

    public ArrayList<EVEntity> getVEByKeyword(String keyword) {
        ArrayList<EVEntity> listEVEntity = new ArrayList<>();
        String selection = null;
        String[] selectionArgs = null;
        if (!TextUtils.isEmpty(keyword)) {
            selection = IEngViet.WORD + "=?";
            selectionArgs = new String[]{String.valueOf(keyword)};
        }
        try {
            openDataBase();
//            String selectQuery = "SELECT  * FROM " + IEngViet.VIET_ENG_TABLE + " , "
//                    + IEngViet.ENG_VIET_TABLE;
//            Cursor cursor = database.rawQuery(selectQuery, null);
            Cursor cursor = database.query(IEngViet.VIET_ENG_TABLE,
                    new String[]{IEngViet.WORD, IEngViet.MEAN},
                    selection, //selection
                    selectionArgs, //selectionArgs
                    null, //groupBy
                    null, //having
                    null, //orderBy
                    ROW_LIMIT);
            listEVEntity = getAllEVFromCursor(cursor);
        } catch (SQLException e) {
            e.printStackTrace();
            listEVEntity = null;
        } finally {
            close();
        }
        return listEVEntity;
    }
}
