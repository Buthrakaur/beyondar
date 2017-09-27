/*
 * Copyright (C) 2014 BeyondAR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beyondar.example;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.beyondar.android.util.Logger.TAG;

public class BeyondarExamples extends Activity implements OnItemClickListener {

	private ListView mLisViewt;
	private String[] values = new String[] { "Simple AR camera",
			"Simple camera with a max/min distance far for rendering", "BeyondAR World in Google maps",
			"AR camera with Gooogle maps", "Camera with touch events", "Camera with screenshot",
			"Change GeoObject images on touch", "Attach view to GeoObject", "Set static view to geoObject",
			"Customize sensor filter", "Simple AR camera with a radar view",
			"Using BeyondarLocationManager" };

	private static final int REQUEST_PERMISSIONS = 1;
	private static String[] PERMISSIONS = {
			Manifest.permission.CAMERA,
			//Manifest.permission.READ_PHONE_STATE,
			Manifest.permission.INTERNET,
			Manifest.permission.ACCESS_NETWORK_STATE,
			//Manifest.permission.ACCESS_WIFI_STATE,
			Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.ACCESS_FINE_LOCATION,
			//Manifest.permission.WAKE_LOCK,
			//Manifest.permission.NFC,
			//Manifest.permission.VIBRATE,
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE
	};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mLisViewt = (ListView) findViewById(R.id.examplesList);

		fillList();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
		}

	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_PERMISSIONS:
				if (hasAllPermissions(PERMISSIONS)) {
					Log.d(TAG, "All permissions granted!");
				} else {
					Toast.makeText(
							this,
							"Cannot continue running without all required permissions.",
							Toast.LENGTH_SHORT
					).show();

					finish();
				}
				break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	boolean hasAllPermissions(String[] permissions) {
		// Only Marshmallow and higher needs to check permissions
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			return true;
		}

		for (String permission : permissions) {
			final int result = checkSelfPermission(permission);
			if (result != PackageManager.PERMISSION_GRANTED) {
				return false;
			}
		}

		return true;
	}

	private void fillList() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				values);
		mLisViewt.setAdapter(adapter);
		mLisViewt.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		switch (pos) {
		case 0:
			openActivity(SimpleCameraActivity.class);
			break;
		case 1:
			openActivity(SimpleCameraWithMaxFarMinAwayActivity.class);
			break;
		case 2:
			openActivity(GoogleMapActivity.class);
			break;
		case 3:
			openActivity(CameraWithGoogleMapsActivity.class);
			break;
		case 4:
			openActivity(CameraWithTouchEventsActivity.class);
			break;
		case 5:
			openActivity(CameraWithScreenShotActivity.class);
			break;
		case 6:
			openActivity(ChangeGeoObjectImagesOnTouchActivity.class);
			break;
		case 7:
			openActivity(AttachViewToGeoObjectActivity.class);
			break;
		case 8:
			openActivity(StaticViewGeoObjectActivity.class);
			break;
		case 9:
			openActivity(SimpleCameraWithCustomFilterActivity.class);
			break;
		case 10:
			openActivity(SimpleCameraWithRadarActivity.class);
			break;
		case 11:
			openActivity(BeyondarLocationManagerMapActivity.class);
			break;
		default:
			break;
		}
	}

	private void openActivity(Class<? extends Activity> ActivityClass) {
		Intent intent = new Intent(this, ActivityClass);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		CustomWorldHelper.sharedWorld = null;
	}

}
