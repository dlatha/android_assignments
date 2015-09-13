package course.lab.modernartui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import course.lab.modernartui.R;
import course.lab.modernartui.interfaces.FragmentAlertDialog;

/**
 * Builds the more information dialog.
 *
 * @author Latha Doddikadi
 * @version 0.0.1
 * @since 1.0.0
 */
public class MoreInformationDialog extends DialogFragment {

    /**
     This build the more informtaion dialog.
     * <p/>
     *Setting up newly created dialog message, handler for positve button click, two buttons.
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog ( Bundle savedInstanceState ) {

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage( R.string.dialog_text ).setPositiveButton( R.string.dialog_visit,
                new DialogInterface.OnClickListener() {

                    /**
                     on positive button click..
                     * <p/>
                     *
                     * @param dialog The dialog that received the click.
                     * @param id     The button that was clicked (the position
                     *               of the item clicked.)
                     */
                    public void onClick ( DialogInterface dialog, int id ) {

                        ((FragmentAlertDialog)getActivity()).doPositiveClick();
                    }
                } ).setNegativeButton( R.string.dialog_not_now,
                new DialogInterface.OnClickListener() {

                    /**
                     * on negative button click.
                     * <p/>
                     *
                     * @param dialog The dialog that received the click.
                     * @param id     The button that was clicked (the position
                     *               of the item clicked.)
                     */
                    public void onClick ( DialogInterface dialog, int id ) {

                        ((FragmentAlertDialog)getActivity()).doNegativeClick();
                    }
                }  );

        return builder.create();
    }
}
