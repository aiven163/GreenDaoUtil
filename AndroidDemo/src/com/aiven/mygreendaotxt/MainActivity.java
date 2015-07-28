package com.aiven.mygreendaotxt;

import com.aiven.Order;
import com.aiven.Customer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MyApplication.getDaoSession(getApplicationContext()).getCustomerDao().deleteAll();
			}
		});
	}

	@Override
	public void onClick(View v) {
		Customer cs=new Customer();
//		cs.setCustId(123l);
		cs.setNick("aiven");
		cs.setTelephoneNum("13813813813");
		
		long id=MyApplication.getDaoSession(getApplicationContext()).getCustomerDao().insert(cs);
		cs.setCustId(id);
		
		Customer cs2=new Customer();
//		cs2.setCustId(456l);
		cs2.setNick("aiven2");
		cs2.setTelephoneNum("13713713713");
		long id2=MyApplication.getDaoSession(getApplicationContext()).getCustomerDao().insert(cs2);
		cs2.setCustId(id2);
		
		Order order=new Order();
		order.setOrderNum("123456");
		order.setCustomer(cs);
		MyApplication.getDaoSession(getApplicationContext()).getOrderDao().insertOrReplace(order);
		
		Order order2=new Order();
		order2.setOrderNum("7369524");
		order2.setCustomer(cs);
		MyApplication.getDaoSession(getApplicationContext()).getOrderDao().insertOrReplace(order2);
//		
	}

}
