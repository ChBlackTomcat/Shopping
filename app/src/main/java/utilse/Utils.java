package utilse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bean.Commodity;
import bean.UserMessage;
import database.ShopCratDataOpenHelper;
import database.UserMessageOpenHelper;

/**
 * Created by 小黑 on 2019/8/7.
 */

public class Utils {
    public static void insert(Context context, UserMessage userMessage){
        UserMessageOpenHelper userMessageOpenHelper = new UserMessageOpenHelper(context);
        SQLiteDatabase db = userMessageOpenHelper.getWritableDatabase();
        db.execSQL("insert into user(username,account,password,province) values(?,?,?,?)", new Object[]{userMessage.getUsername(),userMessage.getAccount(),userMessage.getPassword(),userMessage.getProvince()});
        db.close();
    }
    public static Cursor serach(Context context){
        UserMessageOpenHelper userMessageOpenHelper = new UserMessageOpenHelper(context);
        SQLiteDatabase db = userMessageOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("user",null,null,null,null,null,null,null);
//        ShopCratDataOpenHelper shopCratDataOpenHelper = new ShopCratDataOpenHelper(context);
//        SQLiteDatabase db = shopCratDataOpenHelper.getWritableDatabase();
//        Cursor cursor = db.query("shopcart",null,null,null,null,null,null,null);
        return cursor;
    }
    public static void delet(Context context,UserMessage userMessage){
        UserMessageOpenHelper userMessageOpenHelper = new UserMessageOpenHelper(context);
        SQLiteDatabase db = userMessageOpenHelper.getWritableDatabase();
        db.execSQL("delete from user ");
        db.close();
        ShopCratDataOpenHelper shopCratDataOpenHelper = new ShopCratDataOpenHelper(context);
        db = shopCratDataOpenHelper.getWritableDatabase();
        db.execSQL("delete from shopcart");
        db.close();
    }

    public static List<Commodity> chooseShow(String type,List<Commodity> resultBean){
        List<Commodity> result = new ArrayList<>();
        for(int i = 0;i<resultBean.size();i++){
            if(resultBean.get(i).getType().equals(type)){
                result.add(resultBean.get(i));
            }
        }
        return result;
    }

    public static void insertForShopCart(Context context,Commodity commodity,int number){
        ShopCratDataOpenHelper shopCratDataOpenHelper = new ShopCratDataOpenHelper(context);
        SQLiteDatabase db = shopCratDataOpenHelper.getWritableDatabase();
        db.execSQL("insert into shopcart(image,price,commodityName,number) values(?,?,?,?)",new Object[]{commodity.getImage(),commodity.getPrice()+"",commodity.getCommodityName(),number});
//       Cursor cursor =  serach(context,null);
//        Log.e("TAG",cursor.getCount()+"");
        db.close();
    }

    public static Cursor serachShopCartData(Context context){

        ShopCratDataOpenHelper shopCratDataOpenHelper = new ShopCratDataOpenHelper(context);
        SQLiteDatabase db = shopCratDataOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("shopcart",null,null,null,null,null,null,null);
        return cursor;
    }
}
