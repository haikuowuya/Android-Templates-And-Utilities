package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.R;
import com.example.database.DatabaseCallListener;
import com.example.database.DatabaseCallManager;
import com.example.database.DatabaseCallTask;
import com.example.database.data.Data;
import com.example.database.model.ProductModel;
import com.example.database.query.ProductCreateQuery;
import com.example.database.query.ProductDeleteAllQuery;
import com.example.database.query.ProductDeleteQuery;
import com.example.database.query.ProductReadAllQuery;
import com.example.database.query.ProductReadQuery;
import com.example.database.query.ProductUpdateQuery;
import com.example.task.TaskFragment;
import com.example.utility.Logcat;

import java.util.List;


public class ExampleFragment extends TaskFragment implements DatabaseCallListener
{
	private View mRootView;
	private DatabaseCallManager mDatabaseCallManager = new DatabaseCallManager();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mRootView = inflater.inflate(R.layout.fragment_example, container, false);
		return mRootView;
	}
	
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		
		// cancel async tasks
		mDatabaseCallManager.cancelAllTasks();
	}
	
	
	@Override
	public void onDatabaseCallRespond(final DatabaseCallTask task, final Data<?> data)
	{
		runTaskCallback(new Runnable()
		{
			public void run()
			{
				if(mRootView==null) return; // view was destroyed
				
				if(task.getQuery().getClass().equals(ProductCreateQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallRespond(ProductCreateQuery)");
					
					// data
					Data<Integer> productCreateData = (Data<Integer>) data;
					int rows = productCreateData.getDataObject();

					// TODO
				}
				else if(task.getQuery().getClass().equals(ProductReadQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallRespond(ProductReadQuery)");

					// data
					Data<ProductModel> productReadData = (Data<ProductModel>) data;
					ProductModel product = productReadData.getDataObject();

					// TODO
				}
				else if(task.getQuery().getClass().equals(ProductReadAllQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallRespond(ProductReadAllQuery)");
					
					// data
					Data<List<ProductModel>> productReadAllData = (Data<List<ProductModel>>) data;
					List<ProductModel> productList = productReadAllData.getDataObject();
					
					// TODO
				}
				else if(task.getQuery().getClass().equals(ProductUpdateQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallRespond(ProductUpdateQuery)");
					
					// data
					Data<Integer> productUpdateData = (Data<Integer>) data;
					int rows = productUpdateData.getDataObject();
					
					// TODO
				}
				else if(task.getQuery().getClass().equals(ProductDeleteQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallRespond(ProductDeleteQuery)");

					// data
					Data<Integer> productDeleteData = (Data<Integer>) data;
					int rows = productDeleteData.getDataObject();

					// TODO
				}
				else if(task.getQuery().getClass().equals(ProductDeleteAllQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallRespond(ProductDeleteAllQuery)");
					
					// data
					Data<Integer> productDeleteAllData = (Data<Integer>) data;
					int rows = productDeleteAllData.getDataObject();

					// TODO
				}
				
				// finish query
				mDatabaseCallManager.finishTask(task);
				
				// hide progress in action bar
				if(mDatabaseCallManager.getTasksCount()==0) showActionBarProgress(false);
			}
		});
	}


	@Override
	public void onDatabaseCallFail(final DatabaseCallTask task, final Exception exception)
	{
		runTaskCallback(new Runnable()
		{
			public void run()
			{
				if(mRootView==null) return; // view was destroyed
				
				if(task.getQuery().getClass().equals(ProductCreateQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallFail(ProductCreateQuery): " + exception.getClass().getSimpleName() + " / " + exception.getMessage());
				}
				else if(task.getQuery().getClass().equals(ProductReadQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallFail(ProductReadQuery): " + exception.getClass().getSimpleName() + " / " + exception.getMessage());
				}
				else if(task.getQuery().getClass().equals(ProductReadAllQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallFail(ProductReadAllQuery): " + exception.getClass().getSimpleName() + " / " + exception.getMessage());
				}
				else if(task.getQuery().getClass().equals(ProductUpdateQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallFail(ProductUpdateQuery): " + exception.getClass().getSimpleName() + " / " + exception.getMessage());
				}
				else if(task.getQuery().getClass().equals(ProductDeleteQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallFail(ProductDeleteQuery): " + exception.getClass().getSimpleName() + " / " + exception.getMessage());
				}
				else if(task.getQuery().getClass().equals(ProductDeleteAllQuery.class))
				{
					Logcat.d("Fragment.onDatabaseCallFail(ProductDeleteAllQuery): " + exception.getClass().getSimpleName() + " / " + exception.getMessage());
				}
				
				// finish query
				mDatabaseCallManager.finishTask(task);
				
				// hide progress in action bar
				if(mDatabaseCallManager.getTasksCount()==0) showActionBarProgress(false);
			}
		});
	}
	
	
	private void createProduct(ProductModel product)
	{
		// show progress in action bar
		showActionBarProgress(true);
		
		// run async task
		ProductCreateQuery query = new ProductCreateQuery(product);
		mDatabaseCallManager.executeTask(query, this);
	}


	private void readProduct(long id)
	{
		// show progress in action bar
		showActionBarProgress(true);

		// run async task
		ProductReadQuery query = new ProductReadQuery(id);
		mDatabaseCallManager.executeTask(query, this);
	}
	
	
	private void readAllProducts()
	{
		// show progress in action bar
		showActionBarProgress(true);
		
		// run async task
		ProductReadAllQuery query = new ProductReadAllQuery();
		mDatabaseCallManager.executeTask(query, this);
	}
	
	
	private void updateProduct(ProductModel product)
	{
		// show progress in action bar
		showActionBarProgress(true);
		
		// run async task
		ProductUpdateQuery query = new ProductUpdateQuery(product);
		mDatabaseCallManager.executeTask(query, this);
	}


	private void deleteProduct(long id)
	{
		// show progress in action bar
		showActionBarProgress(true);

		// run async task
		ProductDeleteQuery query = new ProductDeleteQuery(id);
		mDatabaseCallManager.executeTask(query, this);
	}
	
	
	private void deleteAllProducts()
	{
		// show progress in action bar
		showActionBarProgress(true);
		
		// run async task
		ProductDeleteAllQuery query = new ProductDeleteAllQuery();
		mDatabaseCallManager.executeTask(query, this);
	}
}
