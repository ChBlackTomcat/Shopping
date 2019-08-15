package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.first.myapplication.R;

/**
 * Created by 小黑 on 2019/8/14.
 */

public class ShopCratDataOpenHelper extends SQLiteOpenHelper {
    private Context context;
    public ShopCratDataOpenHelper(Context context) {
        super(context, "ShopCartData", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(context.getResources().getString(R.string.createShopData));
    }
    /**
     * 这个方法适合做表结构的更新
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
