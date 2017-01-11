/*
 * *
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *
 *  * @File: IFragmentInteractionListener.java
 *  * @Project: Devote
 *  * @Abstract:
 *  * @Copyright: Copyright © 2015, Saregama India Ltd. All Rights Reserved
 *  * Written under contract by Robosoft Technologies Pvt. Ltd.
 *  * <p/>
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 */

package com.example.shreesha.basecode.Ui.Common;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public interface IFragmentInteractionListener {

    public void loadFragment(int fragmentContainerId, BaseFragment fragment,
                             @Nullable String tag, int enterAnimId, int exitAnimId,
                             BaseFragment.FragmentTransactionType fragmentTransactionType);

    public void loadDialogFragment(DialogFragment fragment,
                                   @Nullable String tag);

    public void logout();

}
