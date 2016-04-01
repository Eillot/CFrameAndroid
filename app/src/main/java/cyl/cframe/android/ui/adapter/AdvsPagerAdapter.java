package cyl.cframe.android.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

import cyl.cframe.android.R;
import cyl.cframe.android.constant.Constant;
import cyl.cframe.android.data.table.AdvertisementInfo;

/**
 * 轮播广告
 */
public class AdvsPagerAdapter extends PagerAdapter {
	private Context mContext;
	private List<AdvertisementInfo> mAdvsList;
	private DisplayImageOptions mOptions;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader = ImageLoader.getInstance();

	public AdvsPagerAdapter(Context context, List<AdvertisementInfo> advsList) {
		mOptions = new DisplayImageOptions.Builder()
				.bitmapConfig(Bitmap.Config.RGB_565)
				.showStubImage(R.drawable.no_img_big)
				.showImageForEmptyUri(R.drawable.no_img_big)
				.cacheOnDisc(true).cacheInMemory(true).build();
		mContext = context;
		mAdvsList = advsList;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAdvsList.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		
		View guide = mInflater.inflate(R.layout.advs_item, null);
		ImageView imageView = (ImageView) guide
				.findViewById(R.id.advs_image);
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				Intent intent = new Intent();
//				intent.putExtra("advsInfo", mAdvsList.get(position));
//				intent.setClass(mContext, AdvertisementActivity.class);
//				mContext.startActivity(intent);
				
			}
		});
		
		String imageUrl = Constant.convertImageUrl(mAdvsList.get(position)
				.getImageUrl());
		mImageLoader.displayImage(imageUrl, imageView, mOptions);
		
		container.addView(guide);
		return guide;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
}
