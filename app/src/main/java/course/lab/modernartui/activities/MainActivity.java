package course.lab.modernartui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import course.lab.modernartui.R;
import course.lab.modernartui.dialogs.MoreInformationDialog;
import course.lab.modernartui.interfaces.FragmentAlertDialog;

/**
 *
 * This is the main activity that is called from the application, containing all the palettes. Each palette has its own bg color and seek bar.
 *
 * @author Latha Doddikadi
 * @version 0.0.1
 * @since 1.0.0
 */
public class MainActivity extends Activity implements FragmentAlertDialog {

    private static final String TAG = MainActivity.class.getName();

    private RelativeLayout colorPalette;

    /**
     * This method contains all the data required for setting up the instace.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        colorPalette = ( RelativeLayout ) findViewById( R.id.palette );
        SeekBar seek = ( SeekBar ) findViewById( R.id.seekBar );

        seek.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            /**
             The palette view of the child is changed based on what the percentage of the seek bar is at.
             *
             * @param seekBar  The SeekBar the progress that is changed is kept track of .
             * @param progress The current percentage of the progress.
             * @param fromUser Boolean value that represents if the progress change has been initiated by the user.(True)
             */
            @Override
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {

                for ( int i = 0; i < colorPalette.getChildCount(); i++ ) {
                    View child = colorPalette.getChildAt( i );

                    int originalColor = Color.parseColor( ( String ) child.getTag() );
                    int invertedColor = ( 0x00FFFFFF - ( originalColor | 0xFF000000 ) ) |
                                        ( originalColor & 0xFF000000 );

                    if ( getResources().getColor( R.color.dull_blue ) != originalColor &&
                         getResources().getColor( R.color.dull_dark_blue ) != originalColor ) {

                        int origR = ( originalColor >> 16 ) & 0x000000FF;
                        int origG = ( originalColor >> 8 ) & 0x000000FF;
                        int origB = originalColor & 0x000000FF;

                        int invR = ( invertedColor >> 16 ) & 0x000000FF;
                        int invG = ( invertedColor >> 8 ) & 0x000000FF;
                        int invB = invertedColor & 0x000000FF;

                        child.setBackgroundColor( Color.rgb(
                                ( int ) ( origR + ( invR - origR ) * ( progress / 100f ) ),
                                ( int ) ( origG + ( invG - origG ) * ( progress / 100f ) ),
                                ( int ) ( origB + ( invB - origB ) * ( progress / 100f ) ) ) );
                        child.invalidate();
                    }
                }
            }

            /**
             * Currently used for nothing.
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) {

            }

            /**
             * Currently used for nothing.
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        } );
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  Menu items are placed
     * in to <var>menu</var>.
     * <p/>
     * <p>This is only called once, the first time the options menu is
     * displayed.</p>
     *
     * @param menu The options menu in which the items are placed.
     * @return Returns true for the menu to be displayed;
     * if it returns false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Evaluates when the user clicks the more information options menu.
     * <p/>
     * Shows the more information dialog fragment.
     *
     * @param item The menu item that was clicked
     */
    public void showDialog ( MenuItem item ) {

        new MoreInformationDialog().show(getFragmentManager(), TAG);
    }

    public void doPositiveClick() {
        /**
         * An intent to open the website is created.
         */
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.moma.org") );
        Intent chooser = Intent.createChooser( intent, getResources().getString(R.string.open_with) );
        startActivity(chooser);
    }

    public void doNegativeClick() {
        // Do nothing.
    }
}
