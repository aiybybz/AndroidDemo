##### MVP

- 解决的问题
  控制逻辑，数据处理逻辑和界面交互耦合，同时能将 MVC 中的 View 和 Model 解耦
  
- 角色划分
  • Model 模型：负责数据的加载和存储
  • View 视图：负责界面的展示
  • Presenter 控制器：负责逻辑控制
  
- 数据流向
  • `MVP` 和 `MVC` 区别在**通信**上，`View` 和 `Model` 不相互持有，都通过 `Presenter` 中转
  • `View` 产生**事件**，通知 `Presenter` 进行逻辑处理，处理后再通知 `Model` 更新数据，更新后通知**数据结构**给 `Presenter`，`Presenter` 再通知 `View` 更新界面
  
- 关系图
  ![MVP](https://raw.githubusercontent.com/aiybybz/ImageBad/master/MVP_20200910105655.png)
  
  

