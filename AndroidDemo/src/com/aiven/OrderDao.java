package com.aiven;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.aiven.Order;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDER".
*/
public class OrderDao extends AbstractDao<Order, Long> {

    public static final String TABLENAME = "ORDER";

    /**
     * Properties of entity Order.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property OrderNum = new Property(1, String.class, "orderNum", false, "ORDER_NUM");
        public final static Property Blong = new Property(2, Long.class, "blong", false, "BLONG");
    };

    private DaoSession daoSession;

    private Query<Order> customer_OrderListQuery;

    public OrderDao(DaoConfig config) {
        super(config);
    }
    
    public OrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDER\" (" + //
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ORDER_NUM\" TEXT UNIQUE ," + // 1: orderNum
                "\"BLONG\" INTEGER);"); // 2: blong
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Order entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String orderNum = entity.getOrderNum();
        if (orderNum != null) {
            stmt.bindString(2, orderNum);
        }
 
        Long blong = entity.getBlong();
        if (blong != null) {
            stmt.bindLong(3, blong);
        }
    }

    @Override
    protected void attachEntity(Order entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Order readEntity(Cursor cursor, int offset) {
        Order entity = new Order( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // orderNum
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // blong
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Order entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrderNum(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBlong(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Order entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Order entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "OrderList" to-many relationship of Customer. */
    public List<Order> _queryCustomer_OrderList(Long blong) {
        synchronized (this) {
            if (customer_OrderListQuery == null) {
                QueryBuilder<Order> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Blong.eq(null));
                customer_OrderListQuery = queryBuilder.build();
            }
        }
        Query<Order> query = customer_OrderListQuery.forCurrentThread();
        query.setParameter(0, blong);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCustomerDao().getAllColumns());
            builder.append(" FROM ORDER T");
            builder.append(" LEFT JOIN CUSTOMER T0 ON T.\"BLONG\"=T0.\"CUST_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Order loadCurrentDeep(Cursor cursor, boolean lock) {
        Order entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Customer Customer = loadCurrentOther(daoSession.getCustomerDao(), cursor, offset);
        entity.setCustomer(Customer);

        return entity;    
    }

    public Order loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Order> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Order> list = new ArrayList<Order>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Order> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Order> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
