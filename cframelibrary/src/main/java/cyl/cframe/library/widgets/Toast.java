
package cyl.cframe.library.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cyl.cframe.library.R;

public class Toast extends android.widget.Toast {

    public Toast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        Toast result = new Toast(context);

        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.base_toast_background, null);
        TextView tv = (TextView) v.findViewById(R.id.base_toast_message);

        tv.setText(text);

        result.setView(v);
        result.setDuration(duration);
        return result;
    }
}
