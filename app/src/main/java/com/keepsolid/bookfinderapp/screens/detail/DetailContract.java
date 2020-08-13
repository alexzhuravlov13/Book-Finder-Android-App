package com.keepsolid.bookfinderapp.screens.detail;

import com.keepsolid.bookfinderapp.base.BasePresenter;
import com.keepsolid.bookfinderapp.base.BaseView;
import com.keepsolid.bookfinderapp.model.VolumeItem;

public class DetailContract {

    interface View extends BaseView<Presenter> {
        void displayResource(VolumeItem volumeItem);

    }

    interface Presenter extends BasePresenter<View> {


    }

}
