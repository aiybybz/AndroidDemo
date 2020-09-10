##### MVVM

- 解决的问题
  控制逻辑，数据处理逻辑和界面交互耦合，进一步把 MVP 中 Presenter 和 View 解耦
  
- 角色划分
  • Model 模型：负责数据的加载和存储
  • View 视图：负责界面的展示
  • ViewModel 控制器：负责逻辑控制
  
- 数据流向
  • `MVVM` 中 `ViewModel` 不持有 `View`。 `ViewModel` 与 `View` 通过 **databinding** 双向反馈，`ViewModel`中改动会自动反馈给 `View` 界面更新，`View` 中事件，也会自动反馈给 `ViewModel`
  • `View` 产生事件，自动通知 `ViewMode`，`VM` 进行逻辑处理后，通知 `Model` 更新数据，更新后通知数据结构给 `ViewModel`，`ViewModel` 自动通知 `View` 更新UI
  
- 关系图
  ![MVVM](https://raw.githubusercontent.com/aiybybz/ImageBad/master/MVVM_20200910170518.png)
  
  

