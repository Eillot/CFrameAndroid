package cyl.cframe.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * 图片获取处理 通用
 * 
 * @author Jerry
 *
 */
public class ImageUtils {

	/**
	 * 存放拍照图片的uri地址
	 */
	public static Uri imageUriFromCamera;
	public static final int	REQUEST_CODE_FROM_ALBUM		= 110;
	public static final int	REQUEST_CODE_FROM_CARMEA	= 111;


	/**
	 * 显示获取照片不同方式对话框
	 * 
	 * @param activity
	 */
	public static void showImagePickDialog ( final Activity activity ) {

		String title = "获取图片方式";
		String[] items = new String[] { "拍照", "相册" };

		new AlertDialog.Builder(activity).setTitle(title)
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick (DialogInterface dialog, int which ) {

						// TODO Auto-generated method stub
						dialog.dismiss();

						switch (which) {
							case 0:
								pickImageFromCamera(activity);
								break;
							case 1:
								pickImageFromAlbum(activity);
								break;

							default:
								break;
						}

					}
				}).show();

	}

	/**
	 * 打开相机拍照获取照片
	 * 
	 * @param activity
	 */
	public static void pickImageFromCamera ( final Activity activity ) {

		imageUriFromCamera = createImageUri(activity);

		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_CARMEA);

	}

	/**
	 * 打开本地相册选取图片
	 * 
	 * @param activity
	 */
	public static void pickImageFromAlbum ( final Activity activity ) {

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);

	}

	/**
	 * 打开本地相册选取图片2
	 * 
	 * @param activity
	 */
	public static void pickImageFromAlbum2 ( final Activity activity ) {

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);

	}

	public static Uri createImageUri (Context context ) {

		String name = "boreWbImg" + System.currentTimeMillis();
		ContentValues values = new ContentValues();

		values.put(MediaStore.Images.Media.TITLE, name);
		values.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

		Uri uri = context.getContentResolver().insert(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

		return uri;
	}

	public static void deleteImageUri (Context context, Uri uri ) {

		context.getContentResolver().delete(imageUriFromCamera, null, null);
	}
}
