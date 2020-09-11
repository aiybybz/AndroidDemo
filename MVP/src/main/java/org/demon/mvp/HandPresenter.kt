package org.demon.mvp

import org.demon.mvp.frame.IModel
import org.demon.mvp.frame.IPresenter
import org.demon.mvp.frame.IView

class HandPresenter : IPresenter {

    private var model: IModel? = null
    private var view: IView? = null

    override fun setModel(model: IModel): IPresenter {
        this.model = model
        return this
    }

    override fun setView(view: IView): IPresenter {
        this.view = view
        return this
    }

    override fun dataHandled(data: String) {
        view?.showData(data)
    }

    override fun dataCleared() {
        view?.showData("")
    }

    override fun onTextChanged(text: String) {
        view?.loading()
        model?.handleData(text)
    }

    override fun onClearBtnClicked() {
        model?.clearData()
    }
}