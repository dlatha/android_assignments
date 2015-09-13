package course.lab.modernartui.interfaces;

import android.view.MenuItem;

/**
 * Simple Alert Dialog fragment.
 *
 * @author Latha Doddikadi
 * @version 0.0.1
 * @since 1.0.0
 */
public interface FragmentAlertDialog {
    void showDialog ( MenuItem item );
    void doPositiveClick();
    void doNegativeClick();
}
