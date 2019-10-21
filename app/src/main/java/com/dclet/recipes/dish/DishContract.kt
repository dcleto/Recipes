package com.dclet.recipes.dish

import com.dclet.recipes.BasePresenter
import com.dclet.recipes.BaseView
import com.dclet.recipes.model.DishDetail

class DishContract{

    interface Presenter : BasePresenter<View>{
        fun loadDetails()
    }

    interface View: BaseView<Presenter>{
        fun setDetails(detail : DishDetail)
    }

}