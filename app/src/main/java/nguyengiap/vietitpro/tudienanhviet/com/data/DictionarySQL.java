package nguyengiap.vietitpro.tudienanhviet.com.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.app.Application;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;
import nguyengiap.vietitpro.tudienanhviet.com.model.VerbEntity;

/**
 * Created by Nguyen Giap on 9/5/2015.
 */
public class DictionarySQL extends SQLiteDataController {
    public DictionarySQL(Context con) {
        super(con);
    }

    private static DictionarySQL INSTANCE;

    public static synchronized DictionarySQL getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DictionarySQL(Application.getHKApplicationContext());
        }
        return INSTANCE;
    }

    public ArrayList listAutoCompleteWindown(String str) {
        ArrayList list = new ArrayList();
        try {
            openDataBase();
            Cursor c = database.rawQuery("SELECT K FROM E where K like '" + str + "%' limit 10", null);
            while (c.moveToNext()) {
                list.add(c.getString(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            close();
        }
        return list;
    }

    public ArrayList listHistoryWindown() {
        ArrayList list = new ArrayList();
        try {
            openDataBase();
            Cursor c = database.rawQuery("SELECT  word FROM H ", null);
            while (c.moveToNext()) {
                list.add(c.getString(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            close();
        }
        return list;
    }

    public ArrayList listAutoComplete(String str) {
        ArrayList list = new ArrayList();
        openDataBase();
        Cursor c = null;
        try {
            if (c != null) {
                c.close();
            }
            c = database.rawQuery("SELECT _id,l_from,l_to FROM word where phrase like '" + str + "%' limit 20", null);
            if (c.getCount() >= 1) {
                while (c.moveToNext()) {
                    list.add(new DictEntity(c.getInt(0), c.getString(1), c.getString(2),""));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;

        } finally {
            close();
        }
        return list;
    }
//
//    public ArrayList listAutoCompleteVI(String str) {
//        ArrayList list = new ArrayList();
//        openDataBase();
//        Cursor c = null;
//        try {
//            if (c != null) {
//                c.close();
//            }
//            c = database.rawQuery("SELECT K,C,ID,W FROM V where KW like '" + str + "%' limit 20", null);
//            while (c.moveToNext()) {
//                list.add(new EngVietDict(c.getInt(2), c.getString(0), c.getString(1), c.getString(3), 0));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            list = null;
//        } finally {
//            close();
//        }
//        return list;
//    }
//
//    public ArrayList getAllMore() {
//        ArrayList list = new ArrayList();
//        openDataBase();
//        Cursor c = null;
//        try {
//            if (c != null) {
//                c.close();
//            }
//            c = database.rawQuery("SELECT icon,package,des,title,id from MA", null);
//            while (c.moveToNext()) {
//                list.add(new MoreAppEntity(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            list = null;
//        } finally {
//            close();
//        }
//
//        return list;
//    }
//
//    public int deleteData_From_Table(String tbName) {
//
//        int result = 0;
//        try {
//            openDataBase();
//            database.beginTransaction();
//            result = database.delete(tbName, null, null);
//            if (result >= 0) {
//                database.setTransactionSuccessful();
//            }
//        } catch (Exception e) {
//            database.endTransaction();
//            close();
//        } finally {
//            database.endTransaction();
//            close();
//        }
//
//        return result;
//    }
//
//    public boolean insertContact(MoreAppEntity item) {
//        boolean result = false;
//        try {
//            openDataBase();
//            ContentValues values = new ContentValues();
//            values.put("icon", item.getIcon());
//            values.put("package", item.getPkage());
//            values.put("des", item.getDes());
//            values.put("title", item.getTitle());
//            long rs = database.insert("MA", null, values);
//            if (rs > 0) {
//                result = true;
//            }
//
//        } catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        } finally {
//            close();
//        }
//
//        return result;
//    }
//
//    public EngVietDict EngVietDict(String str) {
//        EngVietDict item = null;
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("SELECT ID,K,C,W FROM E where K='" + str + "'", null);
//            while (c.moveToNext()) {
//                item = new EngVietDict(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), 0);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            item = null;
//        } finally {
//            close();
//        }
//
//
//        return item;
//    }
//
//
//    public ArrayList<EngVietDict> listHistory() {
//        ArrayList list = new ArrayList();
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("SELECT _id,sword,sphonetic,smeanings,ssummary  FROM history ", null);
//
//            while (c.moveToNext()) {
//                list.add(new EngVietDict(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            list = null;
//
//        } finally {
//            close();
//        }
//        return list;
//    }
//
//
//    public boolean inserthistoryEN(EngVietDict item) {
//        Boolean kt = false;
//        try {
//            ArrayList<EngVietDict> list = listHistory();
//            if (list.size() == 0) {
//                openDataBase();
//                ContentValues cv = new ContentValues();
//                cv.put("word", item.getKeyword());
//                cv.put("contentword", item.getWordcontent());
//                cv.put("c", item.getSubcontent());
//                long rs = database.insert("H", null, cv);
//                if (rs > 0) {
//                    kt = true;
//                }
//            } else {
//                int checkkeyword = 0;
//                for (int i = 0; i < list.size(); i++) {
//                    EngVietDict vietDict = list.get(i);
//                    if (item.getKeyword().equals(vietDict.getKeyword())) {
//                        checkkeyword++;
//                    }
//                }
//                if (checkkeyword == 0) {
//                    openDataBase();
//                    ContentValues cv = new ContentValues();
//                    cv.put("word", item.getKeyword());
//                    cv.put("contentword", item.getWordcontent());
//                    cv.put("c", item.getSubcontent());
//                    long rs = database.insert("H", null, cv);
//                    if (rs > 0) {
//                        kt = true;
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean inserthistoryVI(EngVietDict item) {
//        Boolean kt = false;
//
//        try {
//
//            ArrayList<EngVietDict> list = listHistoryVI();
//            if (list.size() == 0) {
//                openDataBase();
//                ContentValues cv = new ContentValues();
//                cv.put("word", item.getKeyword());
//                cv.put("contentword", item.getWordcontent());
//                cv.put("c", item.getSubcontent());
//                long rs = database.insert("H_VI", null, cv);
//                if (rs > 0) {
//                    kt = true;
//                }
//            } else {
//                int checkkeyword = 0;
//                for (int i = 0; i < list.size(); i++) {
//                    EngVietDict vietDict = list.get(i);
//                    if (item.getKeyword().equals(vietDict.getKeyword())) {
//                        checkkeyword++;
//                    }
//                }
//                if (checkkeyword == 0) {
//                    openDataBase();
//                    ContentValues cv = new ContentValues();
//                    cv.put("word", item.getKeyword());
//                    cv.put("contentword", item.getWordcontent());
//                    cv.put("c", item.getSubcontent());
//                    long rs = database.insert("H_VI", null, cv);
//                    if (rs > 0) {
//                        kt = true;
//                    }
//                }
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public ArrayList<EngVietDict> listHistoryVI() {
//        ArrayList list = new ArrayList();
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("SELECT id, word,contentword,id_search,c FROM H_VI ", null);
//            while (c.moveToNext()) {
////                list.add(new EngVietDict(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4)));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            list = null;
//        } finally {
//            close();
//        }
//        return list;
//    }
//
//    public boolean deleteHistory(int id) {
//        Boolean kt = false;
//        try {
//            openDataBase();
//
//            long rs = database.delete("H", " id = " + id, null);
//            if (rs > 0) {
//                kt = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean deleteHistoryVI(int id) {
//        Boolean kt = false;
//        try {
//            openDataBase();
//
//            long rs = database.delete("H_VI", " id = " + id, null);
//            if (rs > 0) {
//                kt = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public EngVietDict getEngVietDict(String key) {
//        EngVietDict engVietDict = null;
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("select  ID,K,W from E where K='" + key + "'", null);
//
//            while (c.moveToNext()) {
//                engVietDict = new EngVietDict(c.getInt(0), c.getString(1), c.getString(2));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            engVietDict = null;
//        } finally {
//            close();
//        }
//        return engVietDict;
//    }
//
//    public boolean insertFavorite(EngVietDict item, String langauge, int check) {
//        Boolean kt = false;
//
//        try {
//            openDataBase();
//            ContentValues cv = new ContentValues();
//            cv.put("word", item.getKeyword());
//            cv.put("contentword", Common.encode(item.getWordcontent()));
//            cv.put("c", item.getSubcontent());
//            cv.put("checkfav", check);
//            cv.put("langauge", langauge);
//            long rs = database.insert("L", null, cv);
//            if (rs > 0) {
//                kt = true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public int checkFravorite(String keyword) {
//        int fav = 0;
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("SELECT checkfav  FROM L where word='" + keyword + "'", null);
//            while (c.moveToNext()) {
//                fav = c.getInt(0);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            fav = 0;
//        } finally {
//            close();
//        }
//        return fav;
//
//    }
//
//    public boolean updateFavorite(String key, int a, String table) {
//        Boolean kt = false;
//
//        try {
//            openDataBase();
//            ContentValues cv = new ContentValues();
//            cv.put("id_search", a);
//
//            long rs = database.update(table, cv, "word='" + key + "'", null);
//            if (rs > 0) {
//                kt = true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean updateAllFavorite(int a, String table) {
//        Boolean kt = false;
//
//        try {
//            openDataBase();
//            ContentValues cv = new ContentValues();
//            cv.put("id_search", a);
//
//            long rs = database.update(table, cv, "id_search=" + 1, null);
//            if (rs > 0) {
//                kt = true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean deleteFavorite(EngVietDict item) {
//        Boolean kt = false;
//        try {
//            openDataBase();
//
//            long rs = database.delete("L", " word = '" + item.getKeyword() + "'", null);
//            if (rs > 0) {
//                kt = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean deleteAllHistory(String language) {
//        Boolean kt = false;
//        try {
//            openDataBase();
//
//            long rs = database.delete("L", " langauge = '" + language + "'", null);
//            if (rs > 0) {
//                kt = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//
//    }
//
//    public ArrayList<EngVietDict> listFavorite(String langauge) {
//        ArrayList list = new ArrayList();
//        openDataBase();
//        Cursor c = null;
//        try {
//            if (c != null) {
//                c.close();
//            }
//            c = database.rawQuery("SELECT id, word,contentword,c FROM L where langauge='" + langauge + "'", null);
//            while (c.moveToNext()) {
//                list.add(new EngVietDict(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            list = null;
//        } finally {
//            close();
//        }
//        return list;
//    }
//
//
    public ArrayList getListVerb() {
        close();
        ArrayList list = new ArrayList();
        openDataBase();
        Cursor c = null;
        try {
            if (c != null) {
                c.close();
            }
            c = database.rawQuery("select * from IV", null);
            while (c.moveToNext()) {
                list.add(new VerbEntity(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        } finally {
            close();
        }


        return list;
    }
    public ArrayList getListVerbSearch() {
        close();
        ArrayList list = new ArrayList();
        openDataBase();
        Cursor c = null;
        try {
            if (c != null) {
                c.close();
            }
            c = database.rawQuery("select KeyWord from IV", null);
            while (c.moveToNext()) {
                list.add(c.getString(0));
            }
        } catch (SQLException e) {
            list = null;
            e.printStackTrace();
        } finally {
            close();
        }


        return list;
    }
    public VerbEntity getVerbEntitySearch(String str) {
        close();
        openDataBase();
        Cursor c = null;
        try {
            if (c != null) {
                c.close();
            }
            c = database.rawQuery("select * from IV where KeyWord='"+str+"'", null);
            while (c.moveToNext()) {
               return new VerbEntity(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            close();
        }


        return null;
    }
//
//    public boolean insertNote(Notes item) {
//        Boolean kt = false;
//
//        try {
//            openDataBase();
//            ContentValues cv = new ContentValues();
//            cv.put("content", item.getContent());
//            cv.put("keyword", item.getKeyword());
//
//            long rs = database.insert("note", null, cv);
//            if (rs > 0) {
//                kt = true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean updateNote(Notes notes) {
//        Boolean kt = false;
//
//        try {
//
//            openDataBase();
//            ContentValues cv = new ContentValues();
//            cv.put("content", notes.getContent());
//
//            long rs = database.update("note", cv, "keyword='" + notes.getKeyword() + "'", null);
//            if (rs > 0) {
//                kt = true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public boolean deleteNote(Notes item) {
//        Boolean kt = false;
//        try {
//            openDataBase();
//
//            long rs = database.delete("note", " keyword = '" + item.getKeyword() + "'", null);
//            if (rs > 0) {
//                kt = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            kt = false;
//        } finally {
//            close();
//        }
//        return kt;
//    }
//
//    public Notes getNotes(String key) {
//        Notes notes = null;
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("select content,keyword from note where keyword='" + key + "'", null);
//            while (c.moveToNext()) {
//                notes = new Notes(c.getString(0), c.getString(1));
//            }
//        } catch (SQLException e) {
//            notes = null;
//            e.printStackTrace();
//        } finally {
//            close();
//        }
//
//
//        return notes;
//    }
//
//
//    /////////////////////////////
//
//
//    public EngVietDict EnVietHistoryEN(String key) {
//        EngVietDict item = null;
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("SELECT id, word,contentword,id_search,c FROM H where word='" + key + "'", null);
//
//            while (c.moveToNext()) {
////                item = new EngVietDict(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4));
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            item = null;
//        } finally {
//            close();
//        }
//        return item;
//    }
//
//    public EngVietDict EnVietHistoryVI(String key) {
//        EngVietDict item = null;
//        try {
//            openDataBase();
//            Cursor c = database.rawQuery("SELECT id, word,contentword,id_search,c FROM H_VI where word='" + key + "'", null);
//            while (c.moveToNext()) {
////                item = new EngVietDict(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            item = null;
//        } finally {
//            close();
//        }
//        return item;
//    }
//////////////////////////////////

}
