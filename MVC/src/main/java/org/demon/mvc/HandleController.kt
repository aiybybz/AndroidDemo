package org.demon.mvc

import org.demon.mvc.frame.IController
import org.demon.mvc.frame.IModel

class HandleController : IController {

    private var model: IModel? = null

    override fun onDataChanged(data: String) {
        model?.handleData(data)
    }

    override fun clearData() {
        model?.clearData()
    }

    override fun setModel(model: IModel): HandleController {
        this.model = model
        return this
    }
}