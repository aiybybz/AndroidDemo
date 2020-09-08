package org.demon.mvc

import org.demon.mvc.farme.IController
import org.demon.mvc.farme.IModel

class HandleController : IController {

    private var model: IModel? = null

    override fun onDataChanged(data: String) {
        model?.handleData(data)
    }

    override fun clearData() {
        model?.clearData()
    }

    override fun setModel(model: IModel) {
        this.model = model
    }
}