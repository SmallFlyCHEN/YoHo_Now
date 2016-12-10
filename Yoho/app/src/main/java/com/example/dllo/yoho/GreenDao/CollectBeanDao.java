package com.example.dllo.yoho.GreenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COLLECT_BEAN".
*/
public class CollectBeanDao extends AbstractDao<CollectBean, Long> {

    public static final String TABLENAME = "COLLECT_BEAN";

    /**
     * Properties of entity CollectBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Img = new Property(1, String.class, "img", false, "IMG");
        public final static Property Boty = new Property(2, String.class, "boty", false, "BOTY");
    }


    public CollectBeanDao(DaoConfig config) {
        super(config);
    }
    
    public CollectBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COLLECT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"IMG\" TEXT," + // 1: img
                "\"BOTY\" TEXT);"); // 2: boty
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COLLECT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CollectBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(2, img);
        }
 
        String boty = entity.getBoty();
        if (boty != null) {
            stmt.bindString(3, boty);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CollectBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(2, img);
        }
 
        String boty = entity.getBoty();
        if (boty != null) {
            stmt.bindString(3, boty);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CollectBean readEntity(Cursor cursor, int offset) {
        CollectBean entity = new CollectBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // img
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // boty
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CollectBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImg(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBoty(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CollectBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CollectBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CollectBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
