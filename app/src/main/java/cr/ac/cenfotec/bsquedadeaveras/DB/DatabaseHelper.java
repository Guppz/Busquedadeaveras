package cr.ac.cenfotec.bsquedadeaveras.DB;

import android.content.Context;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;

public class DatabaseHelper  extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "usarios.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Usuario, Integer> ContactoDAO = null;
    private RuntimeExceptionDao<Usuario, Integer> RuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,R.raw.ormlite_config);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Usuario.class);
        } catch (java.sql.SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int i, int i1) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (java.sql.SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Usuario,Integer>getContactoDAO() throws SQLException, java.sql.SQLException {
        if (ContactoDAO == null) ContactoDAO = getDao(Usuario.class);
        return ContactoDAO;
    }

    public RuntimeExceptionDao<Usuario, Integer> getRuntimeDao() {
        if (RuntimeDao == null) RuntimeDao = getRuntimeExceptionDao(Usuario.class);
        return RuntimeDao;
    }

    public void close(){
        super.close();
        ContactoDAO = null;
        RuntimeDao = null;
    }
}