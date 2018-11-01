package nz.co.udenbrothers.yoobie.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Checkable;

import nz.co.udenbrothers.yoobie.R;
import nz.co.udenbrothers.yoobie.interfaces.CmdView;


public class Popup {

        private Dialog dialog;
        private Window window;

        public Popup(Context context, int id){
            dialog = new Dialog(context, R.style.MyDialog);
            setUp(id);
        }

        public Popup(Context context, int id, int sid){
            dialog = new Dialog(context, sid);
            setUp(id);
        }

        private void setUp(int id){
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(id);
            window = dialog.getWindow();
            assert window != null;
            window.setGravity(Gravity.CENTER);
        }

        public void clicked(int id, CmdView cd){
            dialog.findViewById(id).setOnClickListener(cd::exec);
        }

        public void clicked(View v, CmdView cd){
            v.setOnClickListener(cd::exec);
        }

        public void setDimension(double width, double height){
            window.setLayout((int)width, (int)height);
        }

        public void setGravity(int gravity){
            window.setGravity(gravity);
        }

        public void show(){
            dialog.show();
        }

        public <T extends View & Checkable> T findViewById(int id){
            return dialog.findViewById(id);
        }

        public void dismiss(){
            dialog.dismiss();
        }

        public boolean isShowing(){
            return dialog.isShowing();
        }

}
